package com.example.municipal.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream


object FileUtil {
    fun compressImageByteArray(data: ByteArray): ByteArray {
        try { //"data" is your "byte[] data"
            val blob = ByteArrayOutputStream()
            val bmp = BitmapFactory.decodeByteArray(data, 0, data.size)
            bmp.compress(Bitmap.CompressFormat.PNG, 70, blob) //100-best quality
            return blob.toByteArray()
        } catch (e: Exception) {
            Log.d("Error", "Image " + data.size + " not saved: " + e.message)
        }
        return byteArrayOf()
    }

    fun compressImageByteArrayLessOneMB(imageByteArray: ByteArray): ByteArray {
        // Convert the bytearray to a Bitmap
        val bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.size)

        // Create a ByteArrayOutputStream to hold the compressed image
        val outputStream = ByteArrayOutputStream()

        // Compress the Bitmap to the ByteArrayOutputStream
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
        // The second parameter in the compress method is the quality percentage (0-100)
        // Adjust the quality to find a good balance between size and image quality

        // Keep compressing until the size is less than 1 MB
        while (outputStream.toByteArray().size > 1 * 1024 * 1024) {
            outputStream.reset()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
            // Adjust the quality as needed
        }

        // Return the compressed image as a bytearray
        return outputStream.toByteArray()
    }

    @Throws(IOException::class)
    fun getBytes(inputStream: InputStream): ByteArray? {
        val byteBuffer = ByteArrayOutputStream()
        val bufferSize = 1024
        val buffer = ByteArray(bufferSize)
        var len = 0
        while (inputStream.read(buffer).also { len = it } != -1) {
            byteBuffer.write(buffer, 0, len)
        }
        return byteBuffer.toByteArray()
    }

    @Throws(IOException::class)
    fun getBytes(context: Context, uri: Uri?): ByteArray? {
        val iStream: InputStream = context.getContentResolver().openInputStream(uri!!)!!
        return try {
            getBytes(iStream)
        } finally {
            // close the stream
            try {
                iStream.close()
            } catch (ignored: IOException) { /* do nothing */
            }
        }
    }
}