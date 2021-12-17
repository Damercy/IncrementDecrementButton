package dev.dayaonweb.incrementdecrementbutton

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout

class IncrementDecrementButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int,
    defSStyleRes: Int
) : LinearLayout(context, attrs, defStyleAttr, defSStyleRes) {

    private var fontFamily: Int
    private var fontSize: Int
    private var decrementText: String
    private var incrementText: String
    private var middleText: String


    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.IncrementDecrementButton,
            defStyleAttr,
            defSStyleRes
        ).apply {
            fontFamily = getResourceId(R.styleable.IncrementDecrementButton_fontFamily, -1)
            fontSize = getInt(R.styleable.IncrementDecrementButton_fontSize, DEFAULT_FONT_SIZE)
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

    private fun initializeIncDecButton() {
        // TODO: Add public hook functions
    }


    companion object {
        private const val DEFAULT_FONT_SIZE = 24
        private const val DEFAULT_DECREMENT_TEXT = "-"
        private const val DEFAULT_INCREMENT_TEXT = "+"
        private const val DEFAULT_MIDDLE_TEXT = "Add"
    }
}