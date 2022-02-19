package dev.dayaonweb.incrementdecrementbutton

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.isDigitsOnly
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import dev.dayaonweb.incrementdecrementbutton.animations.AnimationType
import dev.dayaonweb.incrementdecrementbutton.util.getEnum

class IncrementDecrementButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyle, defStyleRes) {

    private var fontFamily: Int
    private var fontSize: Int
    private var decrementText: String
    private var borderStrokeWidth: Int
    private var borderStrokeColor: Int
    private var incrementText: String
    private var middleText: String
    private var cornerRadius: Float
    private var enableRipple: Boolean
    private var animationType: AnimationType
    private var value = 0
    private var previousValue = value

    private lateinit var btnIncrement: MaterialButton
    private lateinit var btnDecrement: MaterialButton
    private lateinit var btnText: MaterialTextView
    private lateinit var btnRoot: MaterialCardView


    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.IncrementDecrementButton,
            defStyle,
            defStyleRes
        ).apply {
            fontFamily = getResourceId(R.styleable.IncrementDecrementButton_fontFamilyRes, -1)
            fontSize = getInt(R.styleable.IncrementDecrementButton_fontSizeRes, DEFAULT_FONT_SIZE)
            decrementText = getString(R.styleable.IncrementDecrementButton_decrementText)
                ?: DEFAULT_DECREMENT_TEXT
            incrementText = getString(R.styleable.IncrementDecrementButton_incrementText)
                ?: DEFAULT_INCREMENT_TEXT
            middleText =
                getString(R.styleable.IncrementDecrementButton_middleText) ?: DEFAULT_MIDDLE_TEXT
            cornerRadius = getDimension(R.styleable.IncrementDecrementButton_cornerRadius, 100.0f)
            enableRipple = getBoolean(R.styleable.IncrementDecrementButton_enableRipple, true)
            borderStrokeColor = getColor(
                R.styleable.IncrementDecrementButton_borderStrokeColor,
                ResourcesCompat.getColor(resources, android.R.color.white, null)
            )
            borderStrokeWidth = getInt(R.styleable.IncrementDecrementButton_borderStrokeWidth, 0)
            animationType = getEnum(R.styleable.IncrementDecrementButton_animationType,AnimationType.FADE)
        }
        LayoutInflater.from(context).inflate(R.layout.increment_decrement_button_layout, this, true)
        initializeIncDecButton()
    }


    // public setters
    fun setFontFamily(@FontRes fontRes: Int) {
        if (fontRes == -1) return
        val font = ResourcesCompat.getFont(context, fontRes)
        btnIncrement.typeface = font
        btnDecrement.typeface = font
        btnText.typeface = font
        invalidateLayout()
    }

    fun setIncrementButtonText(text: String) {
        incrementText = text
        setResourceText(btnIncrement, incrementText)
    }

    fun setDecrementButtonText(text: String) {
        decrementText = text
        setResourceText(btnDecrement, decrementText)
    }

    fun setMiddleText(text: String) {
        middleText = text
        setResourceText(btnText, middleText)
    }

    fun setCornerRadius(@Dimension radius: Float) {
        cornerRadius = radius
        btnRoot.radius = cornerRadius
    }

    fun setBorderStrokeWidth(width: Int) {
        borderStrokeWidth = width
        btnRoot.strokeWidth = borderStrokeWidth
    }

    fun setBorderStrokeColor(@ColorRes color: Int) {
        borderStrokeColor = color
        btnRoot.strokeColor = borderStrokeColor
    }


    /**
     * ****************************For release v1.2*******************************
     */
    fun setColor(@ColorRes color: Int) {

    }

    fun setIncrementButtonColor(@ColorRes color: Int) {

    }

    fun setDecrementButtonColor(@ColorRes color: Int) {

    }

    fun setMiddleColor(@ColorRes color: Int) {

    }

    fun setIncrementButtonDrawable(@DrawableRes drawableRes: Int) {

    }

    fun setDecrementButtonDrawable(@DrawableRes drawableRes: Int) {

    }

    fun toggleRipple(isEnabled: Boolean) {
        enableRipple = isEnabled

    }

    /**
     * ****************************For release v1.2*******************************
     */

    // public getters
    fun getCurrentValue() = value

    private fun initializeIncDecButton() {
        btnIncrement = findViewById(R.id.btn_increment)
        btnDecrement = findViewById(R.id.btn_decrement)
        btnText = findViewById(R.id.btn_text)
        btnRoot = findViewById(R.id.btn_root)
        setFontFamily(fontFamily)
        setIncrementButtonText(incrementText)
        setDecrementButtonText(decrementText)
        setMiddleText(middleText)
        setCornerRadius(cornerRadius)
        setBorderStrokeColor(borderStrokeColor)
        setBorderStrokeWidth(borderStrokeWidth)
        attachListeners()
    }

    private fun attachListeners() {
        btnIncrement.setOnClickListener {
            previousValue = value
            value++
            onIncrement()
        }
        btnDecrement.setOnClickListener {
            previousValue = value
            value--
            onDecrementRangeCheck()
        }
        btnText.setOnClickListener {
            if (btnText.text.isDigitsOnly())
                return@setOnClickListener
            previousValue = value
            value++
            onIncrement()
        }
    }

    private fun onIncrement() {
        if (value > 0)
            setResourceText(btnText, previousValue.toString())

    }

    private fun onDecrementRangeCheck() {
        if (value <= 0) {
            value = 0
            previousValue = value
            setResourceText(btnText, middleText, isDecrement = true)
        } else
            setResourceText(btnText, previousValue.toString(), isDecrement = true)
    }

    private fun invalidateLayout() {
        invalidate()
        requestLayout()
    }

    private fun setResourceText(
        view: View,
        text: String,
        shouldAnimate: Boolean = true,
        isDecrement: Boolean = false
    ) {
        when (view) {
            is MaterialButton -> view.text = if (text == "0") "" else text
            is MaterialTextView -> {
                view.text = if (text == "0") "" else text
                if (value != 0 && shouldAnimate)
                   translateTopToDown(btnText,isDecrement)
            }
        }
        invalidateLayout()
    }







    private fun updateRipple(view: MaterialButton, isEnabled: Boolean) {
        //    view.rippleColor =  // TODO: Use transparent color
    }


    companion object {
        private const val DEFAULT_FONT_SIZE = 24
        private const val DEFAULT_DECREMENT_TEXT = "-"
        private const val DEFAULT_INCREMENT_TEXT = "+"
        private const val DEFAULT_MIDDLE_TEXT = "ADD"
    }
}