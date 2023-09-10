package com.example.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContactDAO {

    @Insert
    suspend fun insertContact(contact: Contact)

    @Query("SELECT * FROM contact")
    fun getContact() : LiveData<List<Contact>>



}