package com.gallery.ui.fragment_profile

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

interface ProfileView: MvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun loadUserData(userName: String, birthday: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToSettings()
}