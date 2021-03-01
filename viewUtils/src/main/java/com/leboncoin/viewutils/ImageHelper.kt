package com.leboncoin.viewutils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImageUrl(url: String?) {
    Glide.with(this).load(url).placeholder(R.drawable.placeholder_img).into(this)
}
