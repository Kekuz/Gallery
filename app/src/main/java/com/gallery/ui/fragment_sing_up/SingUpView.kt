package com.gallery.ui.fragment_sing_up

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SingUpView : MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToMain()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToSingIn()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateBack()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun renderFieldError(state: SingUpState)
}