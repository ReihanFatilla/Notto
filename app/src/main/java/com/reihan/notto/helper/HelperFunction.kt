package com.reihan.notto.helper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.room.TypeConverter
import com.reihan.notto.R
import com.reihan.notto.databinding.ActivityDetailBinding
import com.reihan.notto.databinding.ItemNoteBinding
import com.reihan.notto.presentation.detail.DetailActivity
import java.io.ByteArrayOutputStream

fun checkIfImageIsNull(binding: ActivityDetailBinding, imageUri: String) {

    val constraintSet = ConstraintSet()


    binding.apply{
        if(imageUri == "null"){
            binding.cvImg.visibility = View.INVISIBLE
            constraintSet.clone(binding.constrainDetail)
            constraintSet.connect(
                edtDesc.id,
                ConstraintSet.TOP,
                tvDate.id,
                ConstraintSet.BOTTOM
            )
        } else {
            cvImg.visibility = View.VISIBLE
            constraintSet.clone(binding.constrainDetail)
            constraintSet.connect(
                edtDesc.id,
                ConstraintSet.TOP,
                cvImg.id,
                ConstraintSet.BOTTOM
            )
            icNote.setImageResource(R.drawable.ic_image_item)
        }
        constraintSet.applyTo(binding.constrainDetail)
        Log.i("checkImagiUri", "$imageUri")
    }

}

fun checkIfItemImageIsNull(binding: ItemNoteBinding, imageUri: String){
    val constraintSet = ConstraintSet()

    binding.apply{
        if(imageUri == "null"){
            binding.cvImg.visibility = View.GONE
            constraintSet.clone(binding.constraintItemNote)
            constraintSet.connect(
                tvDesc.id,
                ConstraintSet.TOP,
                tvDate.id,
                ConstraintSet.BOTTOM
            )
        } else {
            cvImg.visibility = View.VISIBLE
            constraintSet.clone(binding.constraintItemNote)
            constraintSet.connect(
                tvDesc.id,
                ConstraintSet.TOP,
                cvImg.id,
                ConstraintSet.BOTTOM
            )
        }
        constraintSet.applyTo(binding.constraintItemNote)
    }
}