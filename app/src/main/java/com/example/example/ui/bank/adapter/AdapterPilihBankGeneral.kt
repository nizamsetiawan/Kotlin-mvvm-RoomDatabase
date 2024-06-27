package com.example.example.ui.bank.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.example.R
import com.example.example.core.data.source.remote.model.BankGeneral
import com.example.example.databinding.ItemPilihBankBinding
import com.example.example.util.toUrlBank
import com.squareup.picasso.Picasso

class AdapterPilihBankGeneral(var onClicked: ((item: BankGeneral) -> Unit)? = null) : RecyclerView.Adapter<AdapterPilihBankGeneral.Holder>() {

    val data = ArrayList<BankGeneral>()

    lateinit var contex: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        contex = parent.context
        return Holder(
                ItemPilihBankBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                ))
    }

    override fun getItemCount() = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(item: List<BankGeneral>) {
        data.addAll(item)
        notifyDataSetChanged()
    }

    fun remove(i: Int) {
        data.removeAt(i)
        notifyItemRemoved(i)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(position)
    }

    inner class Holder(val binding: ItemPilihBankBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            with(binding) {
                val a = data[position]
                tvName.text = a.name
                Picasso.get().load(a.image?.toUrlBank())
                        .placeholder(R.drawable.image_loading)
                        .into(image)
                layout.setOnClickListener {
                    onClicked?.invoke(a)
                }
            }
        }
    }


}