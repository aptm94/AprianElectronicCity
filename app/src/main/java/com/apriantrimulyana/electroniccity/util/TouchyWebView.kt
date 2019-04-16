package com.apriantrimulyana.electroniccity.util

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import im.delight.android.webview.AdvancedWebView

class TouchyWebView : AdvancedWebView {
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    override fun onTouchEvent(event: MotionEvent): Boolean {
        requestDisallowInterceptTouchEvent(true)
        return super.onTouchEvent(event)
    }
}