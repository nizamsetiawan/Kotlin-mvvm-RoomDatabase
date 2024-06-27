package com.example.example.ui.bank.bottomSheet

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import com.example.example.R
import com.example.example.core.data.source.remote.model.BankGeneral
import com.example.example.ui.bank.adapter.AdapterPilihBankGeneral
import com.inyongtisto.myhelper.extension.verticalLayoutManager
import kotlinx.android.synthetic.main.layout_header_new.*
import kotlinx.android.synthetic.main.layout_pilih_bank.*

class PilihBankGeneralBottomSheet(var listBank: ArrayList<BankGeneral>, var onClicked: ((item: BankGeneral) -> Unit)? = null) : BaseSuperBottomSheet(true) {

    override fun setLayout(): Int = R.layout.layout_pilih_bank

    override fun initView(view: View) {
        setupData()
    }

    @SuppressLint("SetTextI18n")
    override fun initListener(view: View) {
        tv_title.text = "Pilih Bank"

        btn_close.setOnClickListener {
            this.dismiss()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupData() {
        val adapter = AdapterPilihBankGeneral()
        adapter.addItem(listBank)
        adapter.notifyDataSetChanged()
        adapter.onClicked = {
            onClicked?.invoke(it)
            this.dismiss()
        }

        rv_data.layoutManager = requireActivity().verticalLayoutManager()
        rv_data.adapter = adapter
    }

}