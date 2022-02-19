package dev.dayaonweb.incrementdecrementbutton

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.isDigitsOnly
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import dev.dayaonweb.incrementdecrementbutton.animations.Animation
import dev.dayaonweb.incrementdecrementbutton.animations.AnimationType
import dev.dayaonweb.incrementdecrementbutton.util.getEnum
import dev.dayaonweb.incrementdecrementbutton.util.toPx
import kotlin.time.Duration

@Suppress("MemberVisibilityCanBePrivate", "unused")
class IncrementDecrementButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyle, defStyleRes) {

    // attributes
    private var fontFamily: Int
    private var fontSize: Int
    private var decrementText: String
    private var borderStrokeWidth: Int
    private var borderStrokeColor: Int
    private var textColor: Int
    private var incrementText: String
    private var middleText: String
    private var cornerRadius: Float
    private var background: Int
    private var animationDuration: Long
    private var animationType: AnimationType

    // views
    private lateinit var btnIncrement: MaterialButton
    private lateinit var btnDecrement: MaterialButton
    private lateinit var btnText: MaterialTextView
    private lateinit var btnRoot: MaterialCardView

    var value = 0
    private var previousValue = value
    private lateinit var animation: Animation


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
            cornerRadius =
                getDimension(R.styleable.IncrementDecrementButton_cornerRadius, DEFAULT_CORNER_SIZE)
            borderStrokeColor = getColor(
                R.styleable.IncrementDecrementButton_borderStrokeColor,
                ResourcesCompat.getColor(resources, android.R.color.white, null)
            )
            textColor = getColor(
                R.styleable.IncrementDecrementButton_textColor,
                ResourcesCompat.getColor(resources, android.R.color.black, null)
            )
            borderStrokeWidth = getInt(R.styleable.IncrementDecrementButton_borderStrokeWidth, 0)
            background = getResourceId(R.styleable.IncrementDecrementButton_buttonBackground, -1)
            animationType =
                getEnum(R.styleable.IncrementDecrementButton_animationType, AnimationType.FADE)
            animationDuration =
                getInt(
                    R.styleable.IncrementDecrementButton_animationDuration,
                    DEFAULT_ANIMATION_DURATION
                ).toLong()
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

    fun setFontSize(@Size size: Int) {
        if (size < 0) return
        fontSize = size
        btnIncrement.textSize = fontSize.toFloat()
        btnDecrement.textSize = fontSize.toFloat()
        btnText.textSize = fontSize.toFloat()
        invalidateLayout()
    }

    fun setButtonBackgroundColor(@ColorRes color: Int) {
        if (color == -1) return
        background = color
        btnRoot.setCardBackgroundColor(ResourcesCompat.getColor(resources, background, null))
        invalidateLayout()
    }

    private fun setButtonTextColor(@ColorInt color: Int) {
        textColor = color
        btnIncrement.setTextColor(textColor)
        btnDecrement.setTextColor(textColor)
        btnText.setTextColor(textColor)
        invalidateLayout()
    }

    fun setIncrementButtonText(text: String) {
        incrementText = text
        setResourceText(btnIncrement, incrementText)
        invalidateLayout()
    }

    fun setDecrementButtonText(text: String) {
        decrementText = text
        setResourceText(btnDecrement, decrementText)
        invalidateLayout()
    }

    fun setMiddleText(text: String) {
        middleText = text
        setResourceText(btnText, middleText)
        invalidateLayout()
    }

    fun setCornerRadius(@Dimension radius: Float) {
        cornerRadius = radius
        btnRoot.radius = cornerRadius
        invalidateLayout()
    }

    fun setBorderStrokeWidth(width: Int) {
        borderStrokeWidth = width
        btnRoot.strokeWidth = borderStrokeWidth
        invalidateLayout()
    }

    fun setBorderStrokeColor(@ColorRes color: Int) {
        borderStrokeColor = color
        btnRoot.strokeColor = borderStrokeColor
        invalidateLayout()
    }

    fun setAnimation(animation: AnimationType) {
        animationType = animation
    }

    fun setAnimationDuration(duration: Duration) {
        animationDuration = duration.inWholeMilliseconds
    }


    /**
     * ****************************For release v2.0*******************************

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

     * ****************************For release v2.0*******************************
     */

    // public getters
    fun getCurrentValue() = value

    private fun initializeIncDecButton() {
        btnIncrement = findViewById(R.id.btn_increment)
        btnDecrement = findViewById(R.id.btn_decrement)
        btnText = findViewById(R.id.btn_text)
        btnRoot = findViewById(R.id.btn_root)
        animation = Animation(btnText, duration = animationDuration)
        setFontFamily(fontFamily)
        setButtonBackgroundColor(background)
        setIncrementButtonText(incrementText)
        setDecrementButtonText(decrementText)
        setMiddleText(middleText)
        setButtonTextColor(textColor)
        setFontSize(fontSize)
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
            setResourceText(
                btnText,
                if (animationType != AnimationType.FADE) previousValue.toString() else value.toString()
            )

    }

    private fun onDecrementRangeCheck() {
        if (value <= 0) {
            value = 0
            previousValue = value
            setResourceText(btnText, middleText, isDecrement = true)
        } else
            setResourceText(
                btnText,
                if (animationType != AnimationType.FADE) previousValue.toString() else value.toString(),
                isDecrement = true
            )
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
            is MaterialButton -> view.text = text
            is MaterialTextView -> {
                view.text = text
                if (value != 0 && shouldAnimate) {
                    animation.shouldReverse = isDecrement
                    animation.duration = animationDuration
                    animation.animate(animationType, getListenerForAnimation(animationType))
                }
            }
        }
        invalidateLayout()
    }


    private fun getListenerForAnimation(animationType: AnimationType): Animator.AnimatorListener? {
        return when (animationType) {
            AnimationType.FADE -> null
            else -> object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) = Unit
                override fun onAnimationEnd(p0: Animator?) {
                    setResourceText(btnText, value.toString(), false)
                }

                override fun onAnimationCancel(p0: Animator?) = Unit
                override fun onAnimationRepeat(p0: Animator?) = Unit
            }
        }
    }


    companion object {
        private const val DEFAULT_FONT_SIZE = 16
        private val DEFAULT_CORNER_SIZE = 8.0f.toPx
        private const val DEFAULT_ANIMATION_DURATION = 500
        private const val DEFAULT_DECREMENT_TEXT = "-"
        private const val DEFAULT_INCREMENT_TEXT = "+"
        private const val DEFAULT_MIDDLE_TEXT = "ADD"
    }
}