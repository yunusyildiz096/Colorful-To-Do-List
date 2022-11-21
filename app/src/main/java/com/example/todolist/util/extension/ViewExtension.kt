package com.example.todolist.util.extension

import android.view.View
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
