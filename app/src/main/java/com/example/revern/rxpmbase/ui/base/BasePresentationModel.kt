package com.example.revern.rxpmbase.ui.base

import me.dmdev.rxpm.PresentationModel
import me.dmdev.rxpm.navigation.NavigationMessage

abstract class BasePresentationModel : PresentationModel() {

    val errors = Command<String>(bufferSize = 0)

    protected fun sendMessage(message: NavigationMessage) {
        navigationMessages.consumer.accept(message)
    }

    protected fun showError(error: String?) {
        errors.consumer.accept(error ?: "Unknown error")
    }
}
