package com.reihan.notto.presentation.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.reihan.notto.R
import com.reihan.notto.data.local.Notto
import com.reihan.notto.databinding.ActivityMainBinding
import com.reihan.notto.helper.SwipeToDelete
import com.reihan.notto.presentation.NottoViewModel
import com.reihan.notto.presentation.adapter.NottoAdapter
import com.reihan.notto.presentation.detail.DetailActivity

class MainActivity : AppCompatActivity(), androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var nottoAdapter: NottoAdapter? = null


    private lateinit var nottoViewModel: NottoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        nottoViewModel = ViewModelProvider(this).get(NottoViewModel::class.java)
        nottoAdapter = NottoAdapter()

        setUpRecyclerView()
        initView()
    }

    private fun initView() {
        binding.svNote.setOnQueryTextListener(this)

        binding.apply {
            fabAdd.setOnClickListener {
                val intent = Intent(applicationContext, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_ORIGIN, "Add-Method")
                startActivity(intent)
            }
        }
    }

    private fun setUpRecyclerView() {
        nottoViewModel.getDataNotto.observe(this){
            nottoAdapter?.setData(it)
        }
        binding.rvNotto.apply {
            adapter = nottoAdapter
            layoutManager = StaggeredGridLayoutManager(2, 1)
        }
        swipeToDelete(binding.rvNotto)
    }

    private fun swipeToDelete(recyclerView: RecyclerView) {
        val swipeToDeleteCallback = object : SwipeToDelete() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedItem = nottoAdapter?.listNotto?.get(viewHolder.adapterPosition)
                nottoViewModel.deleteNotto(deletedItem!!)
//                nottoAdapter?.notifyItemRemoved(viewHolder.adapterPosition)
                Toast.makeText(applicationContext, "Successfully Deleting Note", Toast.LENGTH_SHORT).show()
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { searchNottoByQuery(it) }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { searchNottoByQuery(it) }
        return true
    }

    private fun searchNottoByQuery(query: String) {
        val querySearch = "%$query%"
        nottoViewModel.searchNottoByQuery(querySearch).observe(this) {
            nottoAdapter?.setData(it)
        }
    }
}