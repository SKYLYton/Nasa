package com.nasa.ui.fragment.home

import com.nasa.model.ImageModel
import com.nasa.ui.base.BaseViewModel
import com.nasa.ui.state.BaseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val interactor: HomeInteractor) : BaseViewModel() {

    private val _imageState = MutableSharedFlow<BaseState<List<ImageModel>>>()
    val imageState = _imageState.asSharedFlow()

    private var imagesModel: List<ImageModel> = emptyList()

    fun hdModChange() {
        //imagesModel.isHd = !imagesModel.isHd
        //emit(_imageState, BaseState.Success(imagesModel))
    }

    override fun onStart() {
        if (imagesModel.isEmpty()) {
            interactor.getCurrentImages().handleResult(onSuccess = {
                emit(_imageState, BaseState.Success(it))
                imagesModel = it
            }) {
                emit(_imageState, BaseState.Error(it))
            }
        } else {
            emit(_imageState, BaseState.Success(imagesModel))
        }
    }

    fun getImage() {
        emit(_imageState, BaseState.Success(imagesModel))
    }
}