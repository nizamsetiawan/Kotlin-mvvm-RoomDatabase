package com.example.example.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.example.core.data.source.remote.network.Resource
import com.example.example.util.Constants
import com.inyongtisto.myhelper.extension.toastError
import com.inyongtisto.myhelper.util.AppProgressDialog

abstract class BaseFragment : Fragment() {
    lateinit var progress: AppProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupProgress()
    }

    fun <T> hendleError(resource: Resource<T>?, toast: Boolean = true) {
        if (resource?.errorCode != null) {
            if (resource.errorCode == "NOT_AUTHORIZED") {
//                Prefs.isLogin = false
//                Prefs.setUser(null)
//                showUnauthorizedError("Token expired!", "Sesi login telah habis",
//                        onCloseClickListener = {
//                            pushActivity(CloasActivity::class.java)
//                        },
//                        onReloginClickListener = {
//                            pushActivity(SplashScreenActivity::class.java)
//                        })
            } else {
                if (toast) toastError(resource.message ?: Constants.DEFAULT_ERROR)
            }
        } else {
            if (toast) toastError(resource?.message ?: Constants.DEFAULT_ERROR)
        }

    }

    fun getActivityResult(name: String = "extra", onResultListener: (String) -> Unit): ActivityResultLauncher<Intent> {
        return registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == 0) {
                val str: String? = it.data?.getStringExtra(name)
                onResultListener.invoke(str ?: "")
            }
        }
    }

    private fun setupProgress() {
        progress = AppProgressDialog(requireActivity())
        progress.setCancelable(false)
        progress.setCanceledOnTouchOutside(false)
    }

}