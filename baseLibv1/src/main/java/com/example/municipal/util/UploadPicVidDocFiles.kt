package com.example.municipal.util

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.baselibv1.BuildConfig
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class UploadPicVidDocFiles {

    var CAMERA_REQUEST = 111
    var GALLERY_REQUEST = 112
    var DOC_REQUEST = 113
    var FilePath = ""


    fun selectImage(activity: Activity, fragment: Fragment?) {
        if (checkAndRequestPermissions(activity)) {
            return
        }
        try {
            val options =
                arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Upload Doc", "Cancel")
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Add Photo!")
            builder.setItems(options) { dialog, item ->
                if (options[item] == "Take Photo") {
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    if (cameraIntent.resolveActivity(activity.packageManager) != null) {
                        // Create the File where the photo should go
                        var photoFile: File? = null
                        try {
                            photoFile = createImageFile(".jpg")
                        } catch (ex: IOException) {
                            // Error occurred while creating the File
                            //                                Log.i(TAG, "IOException");
                        }
                        // Continue only if the File was successfully created
                        val finalPhotoFile = photoFile
                        Handler().postDelayed({
                            if (finalPhotoFile != null) {
                                FilePath = finalPhotoFile.absolutePath
                                val outputFileUri = FileProvider.getUriForFile(
                                    Objects.requireNonNull(activity),
                                    activity.packageName + ".fileprovider",
                                    finalPhotoFile
                                )
                                val mimeTypes = arrayOf("image/jpeg", "image/png")
                                cameraIntent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
                                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri)
                                cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                                if (fragment != null) {
                                    fragment.startActivityForResult(cameraIntent, CAMERA_REQUEST)
                                } else {
                                    activity.startActivityForResult(cameraIntent, CAMERA_REQUEST)
                                }
                            }
                        }, 400)
                    }
                } else if (options[item] == "Choose from Gallery") {
                    //                    galleryIntent();
                    Handler().postDelayed({
                        val intent = Intent()
                        intent.type = "image/*"
                        val mimeTypes = arrayOf("image/jpeg", "image/png")
                        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
                        intent.action = Intent.ACTION_GET_CONTENT
                        if (fragment != null) {
                            fragment.startActivityForResult(intent, GALLERY_REQUEST)
                        } else {
                            activity.startActivityForResult(intent, GALLERY_REQUEST)
                        }
                    }, 400)
                } else if (options[item] == "Upload Doc") {
                    //                    galleryIntent();
                    Handler().postDelayed({
                        val intent = Intent()
                        val mimeTypes = arrayOf("application/pdf", "application/msword")
                        intent.type = "*/*"
                        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
                        intent.action = Intent.ACTION_GET_CONTENT
                        if (fragment != null) {
                            fragment.startActivityForResult(intent, DOC_REQUEST)
                        } else {
                            activity.startActivityForResult(intent, DOC_REQUEST)
                        }
                    }, 400)
                } else if (options[item] == "Cancel") {
                    dialog.dismiss()
                }
            }
            builder.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    fun createImageFile(extention: String?): File? {
        // Create an image file name
        val timeStamp =
            SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES
        )

        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return File.createTempFile(
            imageFileName,  // prefix
            extention,  // suffix
            storageDir // directory
        )
    }

    private fun checkAndRequestPermissions(activity: Activity): Boolean {
        val camera = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
        val read =
            ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)

        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }
        if (read != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (!listPermissionsNeeded.isEmpty()) {
            val REQUEST_CAMERA = 105
            ActivityCompat.requestPermissions(
                activity,
                listPermissionsNeeded.toTypedArray(),
                REQUEST_CAMERA
            )
        } else {
            return false
        }
        return true
    }

    fun setImage(context: Context, imageView: ImageView, uri: Uri?) {
        try {
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
            //                setPreviewImage(bitmap, path);
            imageView.setImageBitmap(bitmap)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}