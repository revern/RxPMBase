package com.example.revern.rxpmbase.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.ButterKnife
import me.dmdev.rxpm.base.PmController

abstract class BaseScreen<PM : BasePresentationModel> : PmController<PM>() {

    protected abstract val screenLayout: Int

    override fun createView(inflater: LayoutInflater, container: ViewGroup, savedViewState: Bundle?): View {
        val view = inflater.inflate(screenLayout, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onBindPresentationModel(pm: PM) {
        pm.errors.observable.bindTo { showError(it) }
    }

    protected open fun showError(errorMessage: String) {
        Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show()
    }

}
