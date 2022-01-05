package com.example.miagendamvvm.dao

import androidx.room.*
import com.example.miagendamvvm.models.Personal

@Dao
interface PersonalDao {

    @Query("SELECT * FROM Personal")
        fun getAll():List<Personal>


    @Query("SELECT * FROM Personal WHERE idEmpleado=:id")
        fun getById(id:Long):Personal

    @Query("SELECT * FROM Personal WHERE nombre LIKE '%' ||:name || '%' OR apellidos LIKE '%' ||:name || '%'")
        fun getByName(name:String):List<Personal>

    @Insert
         fun insert(personas:List<Personal>):List<Long>

     @Update
          fun update(persona:Personal):Int

     @Delete
         fun delete (personal: Personal):Int
}