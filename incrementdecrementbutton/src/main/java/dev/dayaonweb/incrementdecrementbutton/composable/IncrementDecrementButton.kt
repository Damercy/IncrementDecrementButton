package dev.dayaonweb.incrementdecrementbutton.composable

import android.annotation.SuppressLint
import android.graphics.Typeface
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.dayaonweb.incrementdecrementbutton.animations.AnimationType


@Composable
fun IncrementDecrementButton(
    modifier: Modifier = Modifier,
    fontFamily: FontFamily = FontFamily(typeface = Typeface.DEFAULT),
    fontSize: TextUnit = 16.0.sp,
    cornerRadius: Dp = 8.dp,
    animationType: AnimationType = AnimationType.FADE,
    animationDuration: Double = 500.0,
    backgroundColor: Color = MaterialTheme.colors.background,
    contentColor: Color = MaterialTheme.colors.contentColorFor(backgroundColor),
    borderStroke: BorderStroke = BorderStroke(0.dp, Color.White),
    value: Int = 0,
    onDecrementClick: (Int) -> Unit = {},
    onIncrementClick: (Int) -> Unit = {},
    onMiddleClick: (Int) -> Unit = {},
    decrementComposable: @Composable (cb: (Int) -> Unit) -> Unit = { cb ->
        DefaultDecrementComposable(
            modifier = modifier,
            textColor = contentColor,
            fontFamily = fontFamily,
            fontSize = fontSize,
            backgroundColor = backgroundColor,
            cornerRadius = cornerRadius,
            borderStroke = borderStroke,
            onDecrementClick = { cb(-1) }
        )
    },
    incrementComposable: @Composable (cb: (Int) -> Unit) -> Unit = { cb ->
        DefaultIncrementComposable(
            modifier = modifier,
            textColor = contentColor,
            fontFamily = fontFamily,
            fontSize = fontSize,
            backgroundColor = backgroundColor,
            cornerRadius = cornerRadius,
            borderStroke = borderStroke,
            onIncrementClick = { cb(-1) }
        )
    },
    middleComposable: @Composable (Int, cb: (Int) -> Unit) -> Unit = { buttonValue, cb ->
        DefaultMiddleComposable(
            modifier = modifier,
            textColor = contentColor,
            fontFamily = fontFamily,
            fontSize = fontSize,
            backgroundColor = backgroundColor,
            borderStroke = borderStroke,
            onMiddleClick = { cb(-1) },
            value = buttonValue
        )
    },
) {
    var buttonValue by rememberSaveable { mutableStateOf(value) }

    Row {
        decrementComposable {
            if (buttonValue <= 0)
                buttonValue = 0
            else
                buttonValue--
            onDecrementClick(buttonValue)
        }
        middleComposable(buttonValue) {
            buttonValue++
            onMiddleClick(buttonValue)
        }
        incrementComposable {
            buttonValue++
            onIncrementClick(buttonValue)
        }
    }
}


@Composable
fun DefaultDecrementComposable(
    modifier: Modifier = Modifier,
    textColor: Color,
    backgroundColor: Color,
    fontFamily: FontFamily,
    fontSize: TextUnit,
    cornerRadius: Dp,
    borderStroke: BorderStroke,
    onDecrementClick: () -> Unit
) {
    val shape = RoundedCornerShape(
        topStart = cornerRadius,
        bottomStart = cornerRadius
    )
    val buttonModifier = modifier
        .background(
            color = backgroundColor,
            shape = shape
        )
        .clip(
            shape = shape
        )
        .clickable {
            onDecrementClick()
        }
        .border(
            border = borderStroke,
            shape = shape
        )
        .defaultMinSize(minWidth = 64.dp, minHeight = 36.dp)
        .wrapContentHeight()

    Text(
        text = "-",
        color = textColor,
        fontFamily = fontFamily,
        fontSize = fontSize,
        textAlign = TextAlign.Center,
        modifier = buttonModifier
    )
}

@Composable
fun DefaultIncrementComposable(
    modifier: Modifier = Modifier,
    textColor: Color,
    backgroundColor: Color,
    fontFamily: FontFamily,
    fontSize: TextUnit,
    cornerRadius: Dp,
    borderStroke: BorderStroke,
    onIncrementClick: () -> Unit
) {
    val shape = RoundedCornerShape(
        topEnd = cornerRadius,
        bottomEnd = cornerRadius
    )
    val buttonModifier = modifier
        .background(
            color = backgroundColor,
            shape = shape
        )
        .clip(
            shape = shape
        )
        .clickable {
            onIncrementClick()
        }
        .border(
            border = borderStroke,
            shape = shape
        )
        .defaultMinSize(minWidth = 64.dp, minHeight = 36.dp)
        .wrapContentHeight()


    Text(
        text = "+",
        color = textColor,
        fontFamily = fontFamily,
        fontSize = fontSize,
        textAlign = TextAlign.Center,
        modifier = buttonModifier
    )

}

@Composable
fun DefaultMiddleComposable(
    modifier: Modifier = Modifier,
    textColor: Color,
    backgroundColor: Color,
    fontFamily: FontFamily,
    fontSize: TextUnit,
    borderStroke: BorderStroke,
    value: Int,
    onMiddleClick: () -> Unit
) {

    val buttonModifier = modifier
        .background(
            color = backgroundColor,
        )
        .clickable {
            onMiddleClick()
        }
        .border(
            border = borderStroke
        )
        .defaultMinSize(minWidth = 64.dp, minHeight = 36.dp)
        .wrapContentHeight()


    Text(
        text = if (value == 0) "Add".toUpperCase(Locale.current) else value.toString()
            .toUpperCase(
                Locale.current
            ),
        color = textColor,
        fontFamily = fontFamily,
        fontSize = fontSize,
        textAlign = TextAlign.Center,
        modifier = buttonModifier
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun IncrementDecrementButtonPreview() {
    Scaffold {
        IncrementDecrementButton()
    }
}