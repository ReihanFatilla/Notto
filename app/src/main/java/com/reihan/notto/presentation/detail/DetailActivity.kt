package com.reihan.notto.presentation.detail

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.*
import com.bumptech.glide.Glide
import com.reihan.notto.R
import com.reihan.notto.data.local.Notto
import com.reihan.notto.databinding.ActivityDetailBinding
import com.reihan.notto.helper.checkIfImageIsNull
import com.reihan.notto.presentation.NottoViewModel

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    private val detailViewModel: NottoViewModel by viewModels()

    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkIfImgIsNull()
        initView()
    }

    override fun onRestart() {
        super.onRestart()
        checkIfImgIsNull()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            binding.imgNottoDetail.setImageURI(data?.data)
            imageUri = data?.data
        }
    }

    private fun initView() {

        val currentTitle: String? = intent.getStringExtra(EXTRA_TITLE)
        val currentDesc: String? = intent.getStringExtra(EXTRA_DESC)
        val intentOrigin: String? = intent.getStringExtra(EXTRA_ORIGIN)
        val currentImage: String? = intent.getStringExtra(EXTRA_IMAGE)


        binding.apply{
            if(intentOrigin == "Update-Method"){
                edtTitle.setText(currentTitle)
                edtDesc.setText(currentDesc)
                tvDate.text = intent.getStringExtra(EXTRA_DATE)

                Glide.with(this@DetailActivity)
                    .load(Uri.parse(currentImage))
                    .centerCrop()
                    .into(findViewById(R.id.img_notto_detail))
//                grantUriPermission()
//                imgNottoDetail.setImageURI(Uri.parse(currentImage))
//

                Log.i("IntentImage", "Successfully Intent Image $currentImage")
            }


            btnBack.setOnClickListener{
                finish()
            }
            fabAddImage.setOnClickListener{
                addImage()
            }
            btnConfirm.setOnClickListener{
                if(intentOrigin == "Add-Method"){
                    insertNotto()
                } else {
                    updateNotto()
                }
            }
        }

    }

    private fun checkIfImgIsNull() {
        val intentOrigin: String? = intent.getStringExtra(EXTRA_ORIGIN)
        val currentImage: String? = intent.getStringExtra(EXTRA_IMAGE)
        if(intentOrigin == "Update-Method"){
            checkIfImageIsNull(binding, currentImage!!)
        } else {
            checkIfImageIsNull(binding, imageUri.toString())
        }
    }

    private fun updateNotto() {
        binding.apply{
            binding.apply{
                if(edtTitle.text.isEmpty() && edtDesc.text.isEmpty()){
                    edtDesc.error = "Please fill the field"
                    edtTitle.error = "Please fill this field"
                } else if(edtDesc.text.isEmpty()){
                    edtDesc.error = "Please fill the field"
                } else if(edtTitle.text.isEmpty()) {
                    edtTitle.error = "Please fill this field"
                } else {
                    val currentNottoId: Int = intent.getStringExtra(EXTRA_CURRENT_ID)!!.toInt()
                    val title = edtTitle.text.toString()
                    val desc = edtDesc.text.toString()
                    val date = tvDate.text.toString()
                    val image  = imageUri.toString()

                    val updatedData = Notto(
                        currentNottoId,
                        title,
                        date,
                        desc,
                        image
                    )
                    detailViewModel.updateNotto(updatedData)
                    Toast.makeText(this@DetailActivity, "Notto Successfully Updated!", Toast.LENGTH_SHORT).show()
                    Log.i("updateNotto", "Successfully Updating Note with $updatedData")
                    finish()
                }                }

        }
    }
    private fun insertNotto() {
        binding.apply{
            if(edtTitle.text.isEmpty() && edtDesc.text.isEmpty()){
                edtDesc.error = "Please fill the field"
                edtTitle.error = "Please fill this field"
            } else if(edtDesc.text.isEmpty()){
                edtDesc.error = "Please fill the field"
            } else if(edtTitle.text.isEmpty()) {
                edtTitle.error = "Please fill this field"
            } else {
                val title = edtTitle.text.toString()
                val desc = edtDesc.text.toString()
                val date = tvDate.text.toString()
                // get the uri of imgNotto
                val image  = imageUri.toString()

                val insertedData = Notto(
                    0,
                    title,
                    date,
                    desc,
                    image
                )
                detailViewModel.insertNotto(insertedData)
                Toast.makeText(this@DetailActivity, "Notto Successfully Added!", Toast.LENGTH_SHORT).show()
                Log.i("InsertNotto", "Succesully adding note with $insertedData\n" )
                finish()
            }

        }
    }

    private fun addImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }
//    content://com.google.android.apps.photos.contentprovider/-1/1/content%3A%2F%2Fmedia%2Fexternal%2Fimages%2Fmedia%2F18/ORIGINAL/NONE/image%2Fjpeg/1451873816
//    content://com.google.android.apps.photos.contentprovider/-1/1/content%3A%2F%2Fmedia%2Fexternal%2Fimages%2Fmedia%2F18/ORIGINAL/NONE/image%2Fjpeg/1451873816

    companion object{
        const val EXTRA_IMAGE = ""
        const val REQUEST_CODE = 100
        const val EXTRA_CURRENT_ID = "1"
        const val EXTRA_TITLE = "Title"
        const val EXTRA_DESC = "Desc"
        const val EXTRA_DATE = "Date"
        const val EXTRA_ORIGIN = "Origin"
    }
}