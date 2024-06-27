package com.example.example.ui.bank

import android.os.Bundle
import com.example.example.core.data.source.remote.model.BankUser
import com.example.example.core.data.source.remote.network.State
import com.example.example.databinding.ActivityListBankUserBinding
import com.example.example.ui.bank.adapter.BankUserAdapter
import com.inyongtisto.myhelper.extension.*
import com.example.example.ui.base.BaseActivity
import com.inyongtisto.myhelper.util.EditTextSearchListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListUserBankActivity : BaseActivity() {

    private val viewModel: BankUserViewModel by viewModel()

    private var _binding: ActivityListBankUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityListBankUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar(binding.divToolbar.toolbar, "Daftar Bank User")
        init()

        displayData()
        mainButton()
        setupSearch()
    }

    var search: String? = null
    private fun setupSearch() {
        binding.lySearch.edtSearch.hint = "Cari"
        EditTextSearchListener(binding.lySearch.edtSearch, 0) {
            search = it.ifEmpty { null }
            val list = listItem.filter { its ->
                its.account?.lowercase()?.contains("" + search?.lowercase()) ?: false
            }
            adapter.addItem(list)
        }.onClear = {
            search = null
            adapter.addItem(listItem)
        }
    }

    override fun onResume() {
        getBank()
        super.onResume()
    }

    private fun init() {
    }

    private fun mainButton() {

        binding.swipeRefresh.setDefaultColor()
        binding.swipeRefresh.setOnRefreshListener {
            getBank()
        }

        binding.divToolbar.btnToolbarImage.toVisible()
        binding.divToolbar.btnToolbarImage.setOnClickListener {
            intentActivity(TambahBankUserActivity::class.java)
        }
    }

    private var listItem = ArrayList<BankUser>()
    private fun getBank() {
        viewModel.getAll().observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    binding.pd.toGone()
                    binding.swipeRefresh.isRefreshing = false
                    val body = it.body ?: listOf()

                    listItem = body as ArrayList
                    binding.tvError.visible(body.isEmpty())
                    adapter.addItem(body)
//                    binding.divCatatan.visible(body.isNotEmpty())
//                    binding.divToolbar.btnToolbarImage.visible(body.size <= 2)
                }
                State.ERROR -> {
                    binding.pd.toGone()
                    binding.swipeRefresh.isRefreshing = false
                    binding.tvError.setErrors {
                        getBank()
                    }
                }
                State.LOADING -> {
                    binding.swipeRefresh.isRefreshing = true
                }
            }
        }
    }

    private fun deleteBank(data: BankUser, p: Int) {
        viewModel.delete(data).observe(this) {
            when (it.state) {
                State.SUCCESS -> {
                    progress.dismiss()
                    val body = it.body!!
                    toastSuccess("Bank berhasil dihapus")
                    adapter.removeAt(p)

                    binding.tvError.visible(adapter.data.isEmpty())
                    binding.divToolbar.btnToolbarImage.visible(adapter.data.size <= 2)
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

    private val adapter = BankUserAdapter()
    private fun displayData() {
        adapter.onClick = {
            intentActivity(DetailBankUserActivity::class.java, it.toJson())
        }
        adapter.onDelete = { it, i ->
            deleteBank(it, i)
        }
        binding.rvData.layoutManager = verticalLayoutManager()
        binding.rvData.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}