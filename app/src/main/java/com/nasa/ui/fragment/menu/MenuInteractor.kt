package com.nasa.ui.fragment.menu

import com.nasa.db.room.repository.NoteRepository
import com.notes.ui.base.BaseInteractor
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Fedotov Yakov
 */
class MenuInteractor @Inject constructor(private val repository: NoteRepository) :
    BaseInteractor() {

    fun clearDB() = flow {
        repository.clear()
        emit(Unit)
    }
}