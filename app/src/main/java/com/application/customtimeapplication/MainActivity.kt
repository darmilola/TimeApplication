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
    private var ukClockView: ClockView? = null
    private var amClockView: ClockView? = null
    private var uaeClockView: ClockView? = null
    private var colClockView: ClockView? = null

    private var ukTextClock: TextClock? = null
    private var amsTextClock: TextClock? = null
    private var uaeTextClock: TextClock? = null
    private var colTextClock: TextClock? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        amClockView = findViewById(R.id.amClockView)
        ukClockView = findViewById(R.id.ukClockView)
        uaeClockView = findViewById(R.id.uaeClockView)
        colClockView = findViewById(R.id.colClockView)

        ukClockView!!.setIsDarkMode(false)
        uaeClockView!!.setIsDarkMode(false)

        ukTextClock = findViewById(R.id.ukTextClock)
        amsTextClock = findViewById(R.id.amTextClock)
        uaeTextClock = findViewById(R.id.uaeTextClock)
        colTextClock = findViewById(R.id.colTextClock)

        colClockView!!.setTimeZone("America/Bogota")
        amClockView!!.setTimeZone("Europe/Amsterdam")
        uaeClockView!!.setTimeZone("Asia/Dubai")
        ukClockView!!.setTimeZone("Europe/London")

        colTextClock!!.setTimeZone("America/Bogota")
        amsTextClock!!.setTimeZone("Europe/Amsterdam")
        uaeTextClock!!.setTimeZone("Asia/Dubai")
        ukTextClock!!.setTimeZone("Europe/London")



    }
}