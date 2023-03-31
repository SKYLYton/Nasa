package com.nasa.ui.fragment.menu

import com.nasa.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Fedotov Yakov
 */
@HiltViewModel
class MenuViewModel @Inject constructor(
    private val interactor: MenuInteractor
) : BaseViewModel() {

    override fun onStart() {
        /* no-op */
    }

    fun logOut() {
        interactor.clearDB().handleResult()
    }
}