package dev.dayaonweb.incrementdecrementbutton.animations

import android.animation.Animator
import android.view.View

class Animation(
    override var targetView: View,
    override var shouldReverse: Boolean = false,
    override var duration: Long = 500L
) : BaseAnimation {

    private fun crossFade(view: View) {
        view.apply {
            alpha = 0f
            animate()
                .alpha(1.0f)
                .setDuration(500)
                .start()

        }
    }

    private fun translateTopToDown(view: View, listener: Animator.AnimatorListener) {
        val finalTranslateValue = signPrefix * 1000.0f
        val initialTranslateValue = 0f
        view.apply {
            translationY = initialTranslateValue
            animate()
                .translationY(finalTranslateValue)
                .setDuration(duration / 2)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(p0: Animator?) = Unit
                    override fun onAnimationEnd(p0: Animator?) {
                        translationY = -finalTranslateValue
                        animate()
                            .translationY(initialTranslateValue)
                            .setDuration(duration / 2)
                            .setListener(listener)
                            .start()
                    }

                    override fun onAnimationCancel(p0: Animator?) = Unit
                    override fun onAnimationRepeat(p0: Animator?) = Unit

                })
                .start()
        }
    }

    private fun translateLeftToRight(view: View, listener: Animator.AnimatorListener) {
        val finalTranslateValue = signPrefix * 100.0f
        val initialTranslateValue = 0f
        view.apply {
            translationX = initialTranslateValue
            animate()
                .translationX(finalTranslateValue)
                .setDuration(duration / 2)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(p0: Animator?) = Unit
                    override fun onAnimationEnd(p0: Animator?) {
                        translationX = -finalTranslateValue
                        animate()
                            .translationX(initialTranslateValue)
                            .setDuration(duration / 2)
                            .setListener(listener)
                            .start()
                    }

                    override fun onAnimationCancel(p0: Animator?) = Unit
                    override fun onAnimationRepeat(p0: Animator?) = Unit

                })
                .start()
        }
    }


    override fun animate(
        animationType: AnimationType,
        animationListener: Animator.AnimatorListener?
    ) {
        when (animationType) {
            AnimationType.FADE -> crossFade(targetView)
            AnimationType.HORIZONTAL -> animationListener?.let { animListener ->
                translateLeftToRight(
                    targetView,
                    animListener
                )
            }
            AnimationType.VERTICAL -> animationListener?.let { animListener ->
                translateTopToDown(
                    targetView,
                    animListener
                )
            }
        }
    }
}