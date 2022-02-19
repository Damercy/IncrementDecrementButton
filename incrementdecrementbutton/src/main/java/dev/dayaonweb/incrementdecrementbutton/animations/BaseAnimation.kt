package dev.dayaonweb.incrementdecrementbutton.animations

import android.animation.Animator
import android.view.View

interface BaseAnimation {
    var duration: Long
    var targetView: View
    var shouldReverse: Boolean
    val signPrefix: Int
        get() = if (shouldReverse) -1 else 1

    fun animate(animationType: AnimationType, animationListener: Animator.AnimatorListener? = null)
}