package com.hypersoft.cropview.ui.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updatePadding
import androidx.viewbinding.ViewBinding

/**
 *   Developer: Sohaib Ahmed
 *   Date: 9/26/2024
 *   Profile:
 *     -> github.com/epegasus
 *     -> linkedin.com/in/epegasus
 */

const val TAG = "TAG_MyTag"

abstract class BaseActivity<T : ViewBinding>(private val bindingFactory: (LayoutInflater) -> T) : AppCompatActivity() {

    protected val binding by lazy { bindingFactory(layoutInflater) }
    protected var includeTopPadding = true
    protected var includeBottomPadding = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        setPadding()
        onCreated()
    }

    open fun onPreCreated() {

    }

    private fun setPadding() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
            val topPadding = if (includeTopPadding) bars.top else 0
            val bottomPadding = if (includeBottomPadding) bars.bottom else 0
            v.updatePadding(left = bars.left, top = topPadding, right = bars.right, bottom = bottomPadding)
            WindowInsetsCompat.CONSUMED
        }
    }

    /**
     * @param type
     *     0: Show SystemBars
     *     1: Hide StatusBars
     *     2: Hide NavigationBars
     *     3: Hide SystemBars
     */

    protected open fun hideStatusBar(type: Int) {
        Log.d(TAG, "hideStatusBar: Showing/Hiding: Type: $type")
        WindowInsetsControllerCompat(window, window.decorView).apply {
            systemBarsBehavior = when (type) {
                0 -> WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
                else -> WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
            when (type) {
                0 -> show(WindowInsetsCompat.Type.systemBars())
                1 -> hide(WindowInsetsCompat.Type.systemBars())
                2 -> hide(WindowInsetsCompat.Type.statusBars())
                3 -> hide(WindowInsetsCompat.Type.navigationBars())
                else -> hide(WindowInsetsCompat.Type.systemBars())
            }
        }
    }

    abstract fun onCreated()
}