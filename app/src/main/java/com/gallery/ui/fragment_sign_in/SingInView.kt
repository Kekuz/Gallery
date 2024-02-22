package com.gallery.ui.fragment_sign_in

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SingInView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun renderFieldError(state: SingInState)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateBack()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToSingUp()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToMain()
}