package com.reihan.notto.helper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

fun fromBitmap(bitmap: Bitmap): ByteArray {
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    return outputStream.toByteArray()
}

fun toBitmap(byteArray: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}