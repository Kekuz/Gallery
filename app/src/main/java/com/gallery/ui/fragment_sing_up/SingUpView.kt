package com.gallery.ui.fragment_sing_up

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface SingUpView : MvpView {
    /*@StateStrategyType(AddToEndSingleStrategy::class)
    fun render(state: MoviesState)*/
    /*@StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(additionalMessage: String)*/

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun renderFieldError(state: SingUpState)
}