package com.reihan.notto.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.reihan.notto.data.local.Notto
import com.reihan.notto.databinding.ActivityDetailBinding
import com.reihan.notto.databinding.ItemNoteBinding
import com.reihan.notto.presentation.NottoViewModel
import com.reihan.notto.presentation.adapter.NottoAdapter

class DetailActivity : AppCompatActivity() {

    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    private val detailViewModel: NottoViewModel by viewModels()
    private var nottoAdapter: NottoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        val currentTitle: String? = intent.getStringExtra(EXTRA_TITLE)
        val currentDesc: String? = intent.getStringExtra(EXTRA_DESC)
        val intentOrigin: String? = intent.getStringExtra(EXTRA_ORIGIN)


        binding.apply{
            edtTitle.setText(currentTitle)
            edtDesc.setText(currentDesc)
            tvDate.text = intent.getStringExtra(EXTRA_DATE)

            btnBack.setOnClickListener{
                finish()
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

    private fun updateNotto() {
        binding.apply{
            val currentNottoId: Int = intent.getStringExtra(EXTRA_CURRENT_ID)!!.toInt()
            val title = edtTitle.text.toString()
            val desc = edtDesc.text.toString()
            val date = tvDate.text.toString()


            val updatedData = Notto(
                currentNottoId,
                title,
                date,
                desc
            )
            detailViewModel.updateNotto(updatedData)
            Toast.makeText(this@DetailActivity, "Notto Successfully Updated!", Toast.LENGTH_SHORT).show()
            Log.i("updateNotto", "Successfully Updating Note with $updatedData")
            finish()
        }
    }
    private fun insertNotto() {
        binding.apply{
            val title = edtTitle.text.toString()
            val desc = edtDesc.text.toString()
            val date = tvDate.text.toString()

            val insertedData = Notto(
                0,
                title,
                date,
                desc
            )
            detailViewModel.insertNotto(insertedData)
            Toast.makeText(this@DetailActivity, "Notto Successfully Added!", Toast.LENGTH_SHORT).show()
            Log.i("InsertNotto", "Succesully adding note with $insertedData")
            finish()
        }
    }

    companion object{
        const val EXTRA_CURRENT_ID = "1"
        const val EXTRA_TITLE = "Title"
        const val EXTRA_DESC = "Desc"
        const val EXTRA_DATE = "Date"
        const val EXTRA_ORIGIN = "Origin"
    }
}