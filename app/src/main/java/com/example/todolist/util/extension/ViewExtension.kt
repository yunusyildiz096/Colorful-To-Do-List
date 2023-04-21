package com.example.todolist.util.extension

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar

fun View.remove() {
    this.visibility = View.GONE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.snackBarWithAction(message: String, actionlable: String, block: () -> Unit) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .setAction(actionlable) {
            block()
        }.show()
}

fun View.hideKeyboard() {
    val inputMethodService = context.getSystemService(Context.INPUT_METHOD_SERVICE)
    (inputMethodService as? InputMethodManager)?.hideSoftInputFromWindow(windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}
