package com.example.revern.rxpmbase.extensions

import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler

fun RouterTransaction.applyFadeAnimations(): RouterTransaction = this
        .popChangeHandler(FadeChangeHandler())
        .pushChangeHandler(FadeChangeHandler())
