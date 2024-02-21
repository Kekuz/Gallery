package com.gallery.ui.fragment_sign_in

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SingInView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun renderFieldError(state: SingInState)
}