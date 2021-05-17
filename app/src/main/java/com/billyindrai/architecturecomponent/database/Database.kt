package com.billyindrai.architecturecomponent.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.billyindrai.architecturecomponent.data.Converter
import com.billyindrai.architecturecomponent.data.Movie
import com.billyindrai.architecturecomponent.data.TvShows

@Database(entities = [Movie::class, TvShows::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract  class Database  : RoomDatabase(){
    abstract fun databaseDAO(): DatabaseDAO

    companion object{
        @Volatile
        private var INSTANCE: com.billyindrai.architecturecomponent.database.Database? = null

        fun getDatabase(context: Context): com.billyindrai.architecturecomponent.database.Database? {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    com.billyindrai.architecturecomponent.database.Database::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}