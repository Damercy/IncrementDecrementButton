package dev.dayaonweb.incrementdecrementbutton

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: IncrementDecrementButton = findViewById(R.id.btn_inc_dec)
        val duration = 1L
        btn.setAnimationDuration(duration.toDuration(DurationUnit.SECONDS))
    }
}