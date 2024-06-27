package com.example.example.ui.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.example.core.data.source.remote.network.Resource
import com.example.example.util.Constants
import com.inyongtisto.myhelper.extension.pushActivity
import com.inyongtisto.myhelper.extension.toastError
import com.inyongtisto.myhelper.util.AppProgressDialog

abstract class BaseActivity : AppCompatActivity() {
    lateinit var progress: AppProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupProgress()
    }

    fun <T> hendleError(resource: Resource<T>?, toast: Boolean = true) {
        if (resource?.errorCode != null) {
            if (resource.errorCode == "NOT_AUTHORIZED") {

            } else {
                if (toast) toastError(resource.message ?: Constants.DEFAULT_ERROR)
            }
        } else {
            if (toast) toastError(resource?.message ?: Constants.DEFAULT_ERROR)
        }
    }

    private fun setupProgress() {
        progress = AppProgressDialog(this)
        progress.setCancelable(false)
        progress.setCanceledOnTouchOutside(false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}