package com.example.app_ticket.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app_ticket.Models.Movie
import com.example.app_ticket.Interface.MovieDao

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}