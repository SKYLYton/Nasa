package com.nasa.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nasa.BuildConfig
import com.nasa.db.room.convert.Converters
import com.nasa.db.room.dao.NoteDao
import com.nasa.db.room.entity.NoteEntity

/**
 * База данных приложения
 *
 * @author Fedotov Yakov
 */
@Database(
    version = BuildConfig.DB_VERSION,
    exportSchema = false,
    entities = [
        NoteEntity::class
    ]
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}