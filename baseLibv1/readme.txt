🚀 How to Use
1️⃣ Initialize the Library

Call LibInitializer.init() once, preferably in your Application class or early in app startup.

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        LibInitializer.init(
            appContext = this,
            baseUrl = "https://your-api-base-url.com"
        )
    }
}


⚠️ Important:

Initialization runs on a background thread (Dispatchers.IO)

Make sure this is called before accessing any SDK features
