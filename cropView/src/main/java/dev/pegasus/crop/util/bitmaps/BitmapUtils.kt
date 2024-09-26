package dev.pegasus.crop.util.bitmaps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.exifinterface.media.ExifInterface
import dev.pegasus.crop.ui.CroppedBitmapData
import dev.pegasus.crop.util.extensions.rotateBitmap
import io.reactivex.Completable
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

/**
 *   Developer: Sohaib Ahmed
 *   Date: 9/25/2024
 *   Profile:
 *     -> github.com/epegasus
 *     -> linkedin.com/in/epegasus
 */


object BitmapUtils {

    private const val MAX_SIZE = 1024

    fun saveBitmap(croppedBitmapData: CroppedBitmapData, file: File): Completable {
        return Completable.create {
            try {
                FileOutputStream(file).use { out ->
                    croppedBitmapData.croppedBitmap?.compress(Bitmap.CompressFormat.PNG, 100, out)
                    it.onComplete()
                }
            } catch (e: Exception) {
                it.onError(e)
            }

        }
    }

    suspend fun resize(uri: Uri, context: Context): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                // Decode image dimensions without loading the entire bitmap into memory
                val options = BitmapFactory.Options().apply {
                    inJustDecodeBounds = true
                }
                context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    BitmapFactory.decodeStream(inputStream, null, options)
                }

                // Calculate scale to reduce the image size
                var widthTemp = options.outWidth
                var heightTemp = options.outHeight
                var scale = 1

                while (widthTemp / 2 >= MAX_SIZE && heightTemp / 2 >= MAX_SIZE) {
                    widthTemp /= 2
                    heightTemp /= 2
                    scale *= 2
                }

                // Decode the image with scaling
                val resultOptions = BitmapFactory.Options().apply {
                    inSampleSize = scale
                }

                val resizedBitmap = context.contentResolver.openInputStream(uri)?.use { inputStream ->
                    BitmapFactory.decodeStream(inputStream, null, resultOptions)
                }

                // Rotate the bitmap if necessary
                val rotatedBitmap = resizedBitmap?.rotateBitmap(
                    getOrientation(context.contentResolver.openInputStream(uri))
                )

                // Return the result
                rotatedBitmap
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    /*fun resize(uri: Uri, context: Context): Single<ResizedBitmap> {
        return Single.create {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeStream(context.contentResolver.openInputStream(uri), null, options)

            var widthTemp = options.outWidth
            var heightTemp = options.outHeight
            var scale = 1

            while (true) {
                if (widthTemp / 2 < MAX_SIZE || heightTemp / 2 < MAX_SIZE)
                    break
                widthTemp /= 2
                heightTemp /= 2
                scale *= 2
            }

            val resultOptions = BitmapFactory.Options().apply {
                inSampleSize = scale
            }
            var resizedBitmap = BitmapFactory.decodeStream(
                context.contentResolver.openInputStream(uri),
                null,
                resultOptions
            )

            resizedBitmap = resizedBitmap?.rotateBitmap(getOrientation(context.contentResolver.openInputStream(uri)))

            it.onSuccess(ResizedBitmap(resizedBitmap))
        }
    }*/

    private fun getOrientation(inputStream: InputStream?): Int {
        val exifInterface: ExifInterface
        var orientation = 0
        try {
            exifInterface = ExifInterface(inputStream!!)
            orientation = exifInterface.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED
            )
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return orientation
    }
}