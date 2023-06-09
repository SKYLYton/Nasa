package com.nasa.db.room.repository

import com.nasa.model.NoteModel
import kotlinx.coroutines.flow.Flow

/**
 * Репозиторий заметок
 *
 * @author Fedotov Yakov
 */
interface NoteRepository {

    fun notesNotDeleted(): Flow<List<NoteModel>>

    fun deletedNotes(): Flow<List<NoteModel>>

    fun notes(): Flow<List<NoteModel>>

    fun lastNote(): Flow<NoteModel>

    fun note(id: Int): Flow<NoteModel>

    suspend fun add(note: NoteModel)

    suspend fun add(notes: List<NoteModel>)

    suspend fun restore(note: NoteModel)

    suspend fun update(note: NoteModel)

    suspend fun delete(note: List<NoteModel>)

    suspend fun delete(note: NoteModel)

    suspend fun deleteFromDB(note: List<NoteModel>)

    suspend fun deleteFromDB(note: NoteModel)

    suspend fun deleteEmpty()

    suspend fun clear()

}