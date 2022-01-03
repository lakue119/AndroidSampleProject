package com.lakue.androidsampleproject.extension

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

fun AppCompatActivity.isAvailableActivity(block: () -> Unit) {
    if(!isDestroyed && !isFinishing) {
        block()
    }
}

inline fun <reified T> AppCompatActivity.startActivity(
    vararg pairs: Pair<String, Any?>,
) {
    startActivity(Intent(this, T::class.java).apply {
        putExtras(bundleOf(*pairs))
    })
}

inline fun <reified T> AppCompatActivity.startNewTaskActivity(
    vararg pairs: Pair<String, Any?>,
) {
    startActivity(Intent(this, T::class.java).apply {
        putExtras(bundleOf(*pairs))
        addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
    })
}

fun AppCompatActivity.showFragment(fragment: Fragment, frameId: Int) {
    val tag = fragment.javaClass.simpleName

    val findFragment = supportFragmentManager.findFragmentByTag(tag)
    supportFragmentManager.fragments.forEach { fm ->
        supportFragmentManager.beginTransaction().hide(fm).commitAllowingStateLoss()
    }
    findFragment?.let {
        supportFragmentManager.beginTransaction().show(it).commitAllowingStateLoss()
    } ?: kotlin.run {
        supportFragmentManager.beginTransaction()
            .add(frameId, fragment, tag)
            .commitAllowingStateLoss()
    }
}

fun AppCompatActivity.showToast(msg: CharSequence, isLong: Boolean = false) {
    Toast.makeText(
        applicationContext,
        msg,
        if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    )
        .show()
}

fun AppCompatActivity.showToast(msgId: Int, isLong: Boolean = false) {
    showToast(getString(msgId), isLong)
}
