package com.example.example.ui.bank.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.example.R
import com.example.example.core.data.source.remote.model.BankUser
import com.example.example.databinding.ItemBankUserBinding
import com.example.example.util.popUpMenu
import com.example.example.util.toUrlBank
import com.squareup.picasso.Picasso

class BankUserAdapter(var onClick: ((data: BankUser) -> Unit?)? = null) : RecyclerView.Adapter<BankUserAdapter.ViewHolder>() {

    var data = ArrayList<BankUser>()

    lateinit var contex: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        contex = parent.context
        return ViewHolder(
                ItemBankUserBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(item: List<BankUser>) {
        data.clear()
        data.addAll(item)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeAt(i: Int) {
        data.removeAt(i)
        notifyItemRemoved(i)
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val itemBinding: ItemBankUserBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            with(itemBinding) {
                val a = data[position]
                tvName.text = a.bank?.name
                tvAtasNama.text = a.number + "\na.n ${a.account?.uppercase()}"

                Picasso.get().load(a.bank?.image?.toUrlBank())
                        .placeholder(R.drawable.image_loading)
                        .into(image)

                layout.setOnClickListener {
                    onClick?.invoke(a)
                }

                btnMore.setOnClickListener { view ->
                    val menus = listOf("Hapus", "Detail")
                    root.context.popUpMenu(view, menus) {
                        when (it) {
                            "Hapus" -> onDelete?.invoke(a, adapterPosition)
                            "Detail" -> onClick?.invoke(a)
                        }
                    }
                }
            }
        }
    }

    var onDelete: ((data: BankUser, i: Int) -> Unit?)? = null
}