package com.example.example.util

import com.chibatching.kotpref.KotprefModel

object Prefs : KotprefModel() {

    // fitur
    var tokoSlug by stringPref(Constants.SLUG_TOKO)
    var token by stringPref("SFsebcA7O963iK80Ju546qw9gPWZyv1UV7EY1BhRL3zro8NmXMtajGx5IpTC")
    var brandId by stringPref("0")

}