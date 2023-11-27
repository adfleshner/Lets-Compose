package io.flesh.letscompose

import android.content.Context
import android.widget.Toast

fun Context.toast(message: () -> String) {
    Toast.makeText(this, message.invoke(), Toast.LENGTH_SHORT).show()
}