package com.reihan.notto.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.reihan.notto.R
import com.reihan.notto.data.local.Notto
import com.reihan.notto.databinding.ItemNoteBinding
import com.reihan.notto.helper.NottoDiffUtil
import com.reihan.notto.presentation.detail.DetailActivity

class NottoAdapter: RecyclerView.Adapter<NottoAdapter.MyViewHolder>() {

    var listNotto: ArrayList<Notto> = arrayListOf()

    inner class MyViewHolder(val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val notto = listNotto[position]

        holder.binding.apply{
            tvTitle.text = notto.title
            tvDesc.text = notto.desc
            tvDate.text = notto.date
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_TITLE, notto.title)
            intent.putExtra(DetailActivity.EXTRA_DESC, notto.desc)
            intent.putExtra(DetailActivity.EXTRA_DATE, notto.date)
            intent.putExtra(DetailActivity.EXTRA_ORIGIN, "Update-Method")
            intent.putExtra(DetailActivity.EXTRA_CURRENT_ID, notto.id.toString())
            holder.itemView.context.startActivity(intent)
        }
        holder.binding.cvItemNotto.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.scale)
    }

    override fun getItemCount() = listNotto.size

    fun setData(newList: List<Notto>){
        val nottoDiffUtil = NottoDiffUtil(listNotto, newList)
        val diffUtilResult = DiffUtil.calculateDiff(nottoDiffUtil)
        listNotto.clear()
        listNotto.addAll(newList)
        diffUtilResult.dispatchUpdatesTo(this)
    }
}