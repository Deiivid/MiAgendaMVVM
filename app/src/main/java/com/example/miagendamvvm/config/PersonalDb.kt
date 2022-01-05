package com.example.miagendamvvm.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.miagendamvvm.dao.PersonalDao
import com.example.miagendamvvm.models.Personal

@Database(
    entities = [Personal::class],
    version  = 1
)
abstract class PersonalDb:RoomDatabase() {
abstract fun personalDao():PersonalDao

}