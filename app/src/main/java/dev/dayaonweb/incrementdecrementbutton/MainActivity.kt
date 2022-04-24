package dev.dayaonweb.incrementdecrementbutton

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import dev.dayaonweb.incrementdecrementbutton.animations.AnimationType
import dev.dayaonweb.incrementdecrementbutton.composable.IncrementDecrementButton

class MainActivity : AppCompatActivity() {
    private lateinit var composeView: ComposeView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        composeView = findViewById(R.id.compose_view)
        composeView.setContent {
            MaterialTheme {
                IncrementDecrementButton(
                    contentColor = Color.Black,
                    animationType = AnimationType.HORIZONTAL,

                )
            }
        }
    }
}