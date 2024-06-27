package com.example.example.ui.bank

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.example.example.core.data.source.remote.model.BankGeneral
import com.inyongtisto.myhelper.base.BaseActivity
import com.inyongtisto.myhelper.extension.*
import com.example.example.core.data.source.remote.model.BankUser
import com.example.example.core.data.source.remote.network.State
import com.example.example.core.data.source.remote.request.BankUserRequest
import com.example.example.databinding.ActivityTambahBankBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailBankUserActivity : BaseActivity() {

    private val viewModel: BankUserViewModel by viewModel()
    private var selectedBank: BankGeneral? = null

    private var _binding: ActivityTambahBankBinding? = null
    private val binding get() = _binding!!
    private var bankId = 0

    private var listBank = ArrayList<BankGeneral>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTambahBankBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.lyToolbar.toolbar, "Tambah Bank")
        init()

        getBankGeneral()
        mainButton()
    }

    private fun init() {
        // setup layout
        val bank = getStringExtra().toModel(BankUser::class.java)
        binding.apply {
            edtName.setText(bank?.account)
            edtNomor.setText(bank?.number)
        }
        bankId = bank?.id ?: 0
    }

    private fun mainButton() {

        binding.apply {
            lyToolbar.btnToolbar.visibility = View.VISIBLE
            lyToolbar.btnToolbar.setOnClickListener {
                simpan()
            }

            btnDummy.setOnClickListener {
                dummy()
            }

            btnBank.setOnClickListener {
                showPilihBank()
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private fun showPilihBank() {
//        PilihBankGeneralBottomSheet(listBank) {
//            binding.tvBank.text = "Bank " + it.name
//            binding.imageBank.toVisible()
//            Picasso.get().load(it.image?.toUrlBank())
//                    .placeholder(R.drawable.image_loading)
//                    .error(R.drawable.error)
//                    .into(binding.imageBank)
//            selectedBank = it
//        }.show(supportFragmentManager, "PilihBankBottomSheet")
    }

    @SuppressLint("SetTextI18n")
    private fun dummy() {
        binding.edtName.setText("Tisto wahyudi")
        binding.edtNomor.setText("3823908223")
    }

    private fun simpan() {
        if (validate()) {
            val mData = BankUserRequest(
                    bankId,
                    selectedBank?.id ?: 1,
                    binding.edtName.getString(),
                    binding.edtNomor.getString()
            )
            update(mData)
        }
    }

    private fun update(mData: BankUserRequest) {
        viewModel.update(mData).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    val body = it.body ?: BankUser()
                    progress.dismiss()
                    toastSuccess("Bank berhasil ditambah")
                    onBackPressed()
                }
                State.ERROR -> {
                    progress.dismiss()
                    showErrorDialog(it.message!!)
                }
                State.LOADING -> {
                    progress.show()
                }
            }
        }
    }

    private fun validate(): Boolean {
        return when {
            binding.edtName.isEmpty() -> false
            binding.edtNomor.isEmpty() -> false
//            selectedBank == null -> {
//                toastSimple("Harap pilih bank terlebih dahulu")
//                false
//            }
            else -> true
        }
    }

    private fun getBankGeneral() {
//        viewModel.getBankGeneral().observe(this, {
//            when (it.state) {
//                State.SUCCESS -> {
//                    progress.dismiss()
//                    val body = it.body ?: emptyList()
//                    listBank = body as ArrayList
//                }
//                State.LOADING -> progress.show()
//                else -> logs("do nothing")
//            }
//        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}