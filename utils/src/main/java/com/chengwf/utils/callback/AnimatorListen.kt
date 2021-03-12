package com.chengwf.utils.callback

import android.animation.Animator

class AnimatorListen(
    private val repeat: ((Animator) -> Unit) = {}
    , private val start: (animation: Animator) -> Unit = {}
    , private val end: (animation: Animator) -> Unit = {}
    , private val cancel: (animation: Animator) -> Unit = {}
) : Animator.AnimatorListener {
    override fun onAnimationRepeat(animation: Animator?) {
        animation?.let(repeat)
    }

    override fun onAnimationEnd(animation: Animator?) {
        animation?.let(end)
    }

    override fun onAnimationCancel(animation: Animator?) {
        animation?.let(cancel)
    }

    override fun onAnimationStart(animation: Animator?) {
        animation?.let(start)
    }

}