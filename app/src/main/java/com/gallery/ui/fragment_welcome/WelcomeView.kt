package com.gallery.ui.fragment_welcome

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface WelcomeView: MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateAuth()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToSingIn()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToSingUp()
}