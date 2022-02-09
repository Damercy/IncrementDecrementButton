package dev.dayaonweb.incrementdecrementbutton

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.isDigitsOnly
import androidx.core.view.ViewCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.textview.MaterialTextView

class IncrementDecrementButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private var fontFamily: Int
    private var fontSize: Int
    private var decrementText: String
    private var incrementText: String
    private var middleText: String
    private var cornerRadius: Float
    private var shape: ShapeAppearanceModel
    private var enableRipple: Boolean
    private var value = 0

    private lateinit var btnIncrement: MaterialButton
    private lateinit var btnDecrement: MaterialButton
    private lateinit var btnText: MaterialTextView
    private lateinit var btnRoot: LinearLayout


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
            cornerRadius = getDimension(R.styleable.IncrementDecrementButton_cornerRadius, 24.0f)
            enableRipple = getBoolean(R.styleable.IncrementDecrementButton_enableRipple, true)
            shape = getDefaultShape()
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
        val shapeAppearanceModel = getDefaultShape().withCornerSize(cornerRadius)
        setShape(shapeAppearanceModel)
    }

    fun setShape(shapeAppearanceModel: ShapeAppearanceModel) {
//        shape = shapeAppearanceModel
//        val shapeDrawable = MaterialShapeDrawable(shape)
//        ViewCompat.setBackground(btnRoot, shapeDrawable)
//        invalidateLayout()
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

    fun getDefaultShape(): ShapeAppearanceModel {
        return ShapeAppearanceModel.Builder()
            .setAllCorners(CornerFamily.ROUNDED, cornerRadius)
            .build()
    }


    private fun initializeIncDecButton() {
        btnIncrement = findViewById(R.id.btn_increment)
        btnDecrement = findViewById(R.id.btn_decrement)
        btnText = findViewById(R.id.btn_text)
        btnRoot = findViewById(R.id.btn_root)
        setFontFamily(fontFamily)
        setIncrementButtonText(incrementText)
        setDecrementButtonText(decrementText)
        setMiddleText(middleText)
        setShape(shape)
        attachListeners()
    }

    private fun attachListeners() {
        btnIncrement.setOnClickListener {
            value++
            onIncrement()
        }
        btnDecrement.setOnClickListener {
            value--
            onDecrementRangeCheck()
        }
        btnText.setOnClickListener {
            if (btnText.text.isDigitsOnly())
                return@setOnClickListener
            value++
            onIncrement()
        }
    }

    private fun onIncrement() {
        if (value > 0)
            setResourceText(btnText, value.toString())

    }

    private fun onDecrementRangeCheck() {
        if (value <= 0) {
            value = 0
            setResourceText(btnText, middleText)
        } else
            setResourceText(btnText, value.toString())
    }

    private fun invalidateLayout() {
        invalidate()
        requestLayout()
    }

    private fun setResourceText(view: View, text: String) {
        when (view) {
            is MaterialButton -> view.text = text
            is MaterialTextView -> {
                view.text = text
                if (value != 0)
                    crossFade(btnText)
            }
        }
        invalidateLayout()
    }

    private fun crossFade(view: View) {
        view.apply {
            alpha = 0f
            animate()
                .alpha(1.0f)
                .setDuration(500)
                .start()
        }
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