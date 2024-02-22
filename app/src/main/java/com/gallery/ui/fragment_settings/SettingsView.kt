package com.gallery.ui.fragment_settings

import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SettingsView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateSingOut()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateBack()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateBackWithSave()
}