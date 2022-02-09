package dev.dayaonweb.incrementdecrementbutton

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.button.MaterialButton
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
    private var value = 0

    private lateinit var btnIncrement: MaterialButton
    private lateinit var btnDecrement: MaterialButton
    private lateinit var btnText: MaterialTextView


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

    // public getters
    fun getCurrentValue() = value


    private fun initializeIncDecButton() {
        btnIncrement = findViewById(R.id.btn_increment)
        btnDecrement = findViewById(R.id.btn_decrement)
        btnText = findViewById(R.id.btn_text)
        setFontFamily(fontFamily)
        setIncrementButtonText(incrementText)
        setDecrementButtonText(decrementText)
        setMiddleText(middleText)
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
            setResourceText(btnText, middleText)
            value = 0
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
            is MaterialTextView -> view.text = text
        }
        invalidateLayout()
    }


    companion object {
        private const val DEFAULT_FONT_SIZE = 24
        private const val DEFAULT_DECREMENT_TEXT = "-"
        private const val DEFAULT_INCREMENT_TEXT = "+"
        private const val DEFAULT_MIDDLE_TEXT = "Add"
    }
}