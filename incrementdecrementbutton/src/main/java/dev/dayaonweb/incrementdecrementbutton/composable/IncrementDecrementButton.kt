package dev.dayaonweb.incrementdecrementbutton.composable

import android.graphics.Typeface
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.dayaonweb.incrementdecrementbutton.animations.AnimationType


@Composable
fun IncrementDecrementButton(
    decrementComposable: @Composable () -> Unit = { DefaultDecrementComposable() },
    incrementComposable: @Composable () -> Unit = { DefaultIncrementComposable() },
    middleComposable: @Composable () -> Unit = { DefaultMiddleComposable() },
    fontFamily: FontFamily = FontFamily(typeface = Typeface.DEFAULT),
    fontSize: TextUnit = 16.0.sp,
    cornerShape: Shape = RoundedCornerShape(8.dp),
    animationType: AnimationType = AnimationType.FADE,
    animationDuration: Double = 500.0,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = MaterialTheme.colors.contentColorFor(backgroundColor),
    borderStroke: BorderStroke = BorderStroke(0.dp, Color.White)
) {

}


@Composable
fun DefaultDecrementComposable() {

}

@Composable
fun DefaultIncrementComposable() {

}

@Composable
fun DefaultMiddleComposable() {

}