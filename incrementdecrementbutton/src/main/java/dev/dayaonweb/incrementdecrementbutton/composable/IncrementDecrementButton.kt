package dev.dayaonweb.incrementdecrementbutton.composable

import android.annotation.SuppressLint
import android.graphics.Typeface
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.dayaonweb.incrementdecrementbutton.animations.AnimationType


@Composable
fun IncrementDecrementButton(
    modifier: Modifier = Modifier,
    decrementComposable: @Composable (Modifier) -> Unit = { DefaultDecrementComposable(it) },
    incrementComposable: @Composable (Modifier) -> Unit = { DefaultIncrementComposable(it) },
    middleComposable: @Composable (Modifier) -> Unit = { DefaultMiddleComposable(it) },
    fontFamily: FontFamily = FontFamily(typeface = Typeface.DEFAULT),
    fontSize: TextUnit = 16.0.sp,
    cornerShape: Shape = RoundedCornerShape(8.dp),
    animationType: AnimationType = AnimationType.FADE,
    animationDuration: Double = 500.0,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = MaterialTheme.colors.contentColorFor(backgroundColor),
    borderStroke: BorderStroke = BorderStroke(0.dp, Color.White),
) {
    val buttonModifier = modifier
        .background(
            color = backgroundColor,
            shape = cornerShape
        )
        .border(
            border = borderStroke,
            shape = cornerShape
        )
    Row{
        decrementComposable(buttonModifier)
        middleComposable(buttonModifier)
        incrementComposable(buttonModifier)
    }
}


@Composable
fun DefaultDecrementComposable(
    modifier: Modifier = Modifier
) {
    TextButton(onClick = { /*TODO*/ }, modifier = modifier) {
        Text(text = "-")
    }
}

@Composable
fun DefaultIncrementComposable(
    modifier: Modifier = Modifier
) {
    TextButton(onClick = { /*TODO*/ }, modifier = modifier) {
        Text(text = "+")
    }
}

@Composable
fun DefaultMiddleComposable(
    modifier: Modifier = Modifier
) {
    TextButton(onClick = { /*TODO*/ }, modifier = modifier) {
        Text(text = "Add".toUpperCase(Locale.current))
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun IncrementDecrementButtonPreview() {
    Scaffold {
        IncrementDecrementButton()
    }
}