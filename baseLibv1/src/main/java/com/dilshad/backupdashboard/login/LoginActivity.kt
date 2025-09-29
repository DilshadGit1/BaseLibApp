package com.dilshad.backupdashboard.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.dilshad.android.baselib.databinding.ActivityLoginBinding
import com.dilshad.backupdashboard.MainActivity
import com.dilshad.backupdashboard.utils.PreferenceManager
import com.dilshad.libconfig.data.local.ProductConfigDao
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
// This is the main activity for the login screen.
class LoginActivity : AppCompatActivity() {

    // View binding instance to access the UI components.
    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var productConfigDao: ProductConfigDao

    // Sealed class to represent the different states of the login process.
    sealed class LoginState {
        object Loading : LoginState()
        object Success : LoginState()
        data class Error(val message: String) : LoginState()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout using View Binding.
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the click listener for the login button.
        binding.loginButton.setOnClickListener {
            handleLogin()
        }
    }

    // Function to handle the login process.
    private fun handleLogin() {
        // Get the user input from the text fields.
        val email = binding.emailInputLayout.editText?.text.toString().trim()
        val password = binding.passwordInputLayout.editText?.text.toString().trim()

        // Simple validation to check if the fields are empty.
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Use a coroutine to handle the asynchronous login operation.
        // This prevents the UI from freezing.
        lifecycleScope.launch {
            // First, indicate that the login process is in progress.
            updateLoginState(LoginState.Loading)

            // Here, you would implement your actual login logic.
            // For demonstration, we'll simulate both a local and an API login.
            // You can choose which one to use.

            // Option 1: Simulate a login from a local source.
            val localLoginResult = loginLocally(email, password)
            updateLoginState(localLoginResult)

            // Option 2: Simulate a login from an API.
            // To use this, uncomment the following line and comment out the local login call above.
            // val apiLoginResult = loginWithApi(email, password)
            // updateLoginState(apiLoginResult)
        }
    }

    // Function to simulate a local login.
    // Replace with your actual local database or SharedPreferences logic.
    private suspend fun loginLocally(email: String, password: String): LoginState {
        // Simulate a network delay.
        val id=productConfigDao.getData()?.loginId
        val pwd=productConfigDao.getData()?.loginPwd
        delay(1000)


        // For this example, a hardcoded check for a valid user.
        return if (email == id && password == pwd) {

            LoginState.Success
        } else {
            LoginState.Error("Invalid credentials. Please try again.")
        }
    }

    // Function to simulate an API login.
    // You would use a library like Retrofit or Ktor here.
    private suspend fun loginWithApi(email: String, password: String): LoginState {
        // TODO: Implement your API call here.
        // Example with a placeholder:
        // val response = apiService.login(email, password)
        // return if (response.isSuccessful) {
        //     LoginState.Success
        // } else {
        //     LoginState.Error(response.message())
        // }

        // Returning a placeholder error for now.
        delay(1000)
        return LoginState.Error("API login is not yet implemented.")
    }

    // Function to update the UI based on the login state.
    private fun updateLoginState(state: LoginState) {
        when (state) {
            is LoginState.Loading -> {
                // Show a progress bar or disable the button.
                binding.loginButton.text = "Logging in..."
                binding.loginButton.isEnabled = false
            }
            is LoginState.Success -> {
                // Show a success message and navigate to the next screen.
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                binding.loginButton.text = "LOGIN"
                binding.loginButton.isEnabled = true
                // TODO: Add intent to start a new activity here.
                PreferenceManager.saveLoginStatus(true)
                nextActivity()
            }
            is LoginState.Error -> {
                // Show an error message to the user.
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                binding.loginButton.text = "LOGIN"
                binding.loginButton.isEnabled = true
            }
        }
    }

    private fun nextActivity(){
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }
}

