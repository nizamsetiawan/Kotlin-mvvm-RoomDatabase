package com.example.example.util

import android.content.Context
import android.view.View
import androidx.appcompat.widget.PopupMenu

fun Context.popUpMenu(view: View, list: List<String>, onClicked: (String) -> Unit) {
    val popupMenu = PopupMenu(this, view)
    popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener {
        onClicked.invoke(it.toString())
        return@OnMenuItemClickListener true
    })
    list.forEach {
        popupMenu.menu.add(it)
    }
    popupMenu.show()
}

fun String.toUrlBank() = "${Constants.urlStorage}/bank/$this"