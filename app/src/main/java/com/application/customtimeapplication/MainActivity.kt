package com.application.customtimeapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.DigitalClock
import android.widget.TextClock
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.application.customtimeapplication.ui.theme.CustomTimeApplicationTheme

class MainActivity : Activity() {
    private var amClockView: ClockView? = null
    private var loClockView: ClockView? = null
    private var boClockView: ClockView? = null
    private var duClockView: ClockView? = null

    private var amDigitalView: TextClock? = null
    private var loDigitalView: TextClock? = null
    private var boDigitalView: TextClock? = null
    private var duDigitalView: TextClock? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        amClockView = findViewById(R.id.amclockView)
        loClockView = findViewById(R.id.lonclockView)
        boClockView = findViewById(R.id.boclockView)
        duClockView = findViewById(R.id.duclockView)

        amDigitalView = findViewById(R.id.amDigitalView)
        loDigitalView = findViewById(R.id.lonDigitalView)
        boDigitalView = findViewById(R.id.boDigitalView)
        duDigitalView = findViewById(R.id.duDigitalView)

        boClockView!!.setTimeZone("America/Bogota")
        amClockView!!.setTimeZone("Europe/Amsterdam")
        duClockView!!.setTimeZone("Asia/Dubai")
        loClockView!!.setTimeZone("Europe/London")

        amDigitalView!!.timeZone = "Europe/Amsterdam"
        loDigitalView!!.timeZone = "Europe/London"
        boDigitalView!!.timeZone = "America/Bogota"
        duDigitalView!!.timeZone = "Asia/Dubai"

    }
}