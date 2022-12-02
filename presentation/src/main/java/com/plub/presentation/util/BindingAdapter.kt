package com.plub.presentation.util

import android.view.View
import android.webkit.WebView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.google.android.gms.common.SignInButton
import com.plub.domain.UiState

@BindingAdapter("showLoadingProgressBar")
fun ProgressBar.bindShow(uiState: UiState<*>) {
    visibility = if (uiState is UiState.Loading) View.VISIBLE else View.GONE
}

@BindingAdapter("showErrorPage")
fun View.bindShowErrorPage(uiState: UiState<*>) {
    visibility = if (uiState is UiState.Error) View.VISIBLE else View.GONE
}

@BindingAdapter("android:onClick")
fun SignInButton.bindSignInClick(method: () -> Unit) {
    setOnClickListener { method.invoke() }
}

@BindingAdapter("loadUrl")
fun loadUrl(view: WebView, url: String) {
    if(view.url != url) view.loadUrl(url)
}

@BindingAdapter("entries","default")
fun Spinner.setEntries(entries: List<Int>, default:Int) {
    val arrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, entries).apply {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }
    val defaultPosition = arrayAdapter.getPosition(default)
    adapter = arrayAdapter
    setSelection(defaultPosition)
}