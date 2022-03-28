package com.reihan.notto.data.local

import android.content.Context
import androidx.room.*
import com.reihan.notto.data.Converter

@Database(entities = [Notto::class], version = 2)
@TypeConverters(Converter::class)
abstract class NottoDB: RoomDatabase() {
    abstract val nottoDao: NottoDAO

    companion object{
        @Volatile
        var instace: NottoDB? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instace ?: synchronized(LOCK) {
            instace ?: buildDataBase(context).also {
                instace = it
            }
        }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context, NottoDB::class.java, "notto.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}