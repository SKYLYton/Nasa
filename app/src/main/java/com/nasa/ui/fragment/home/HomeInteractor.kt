package com.nasa.ui.fragment.home

import com.nasa.api.ApiRepository
import com.nasa.model.ImageModel
import com.nasa.model.toModel
import com.notes.ui.base.BaseInteractor
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class HomeInteractor @Inject constructor(private val api: ApiRepository) : BaseInteractor() {

    fun getImages() = flow {
        emit(api.getImages().map {
            it.toModel
        })
    }

    fun getCurrentImages() = flow {
        val list = mutableListOf<ImageModel>()
        list.add(getTodayImage())
        list.add(getYesterdayImage())
        list.add(getDayBeforeYesterdayImage())
        emit(list)
    }

    private suspend fun getTodayImage() = api.getImage().toModel

    private suspend fun getYesterdayImage() = api.getImage(getYesterdayDateString()).toModel

    private suspend fun getDayBeforeYesterdayImage() = api.getImage(getDayBeforeYesterdayDateString()).toModel

    private fun getYesterdayDateString(): Date {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        return cal.time
    }

    private fun getDayBeforeYesterdayDateString(): Date {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -2)
        return cal.time
    }

}