package com.example.myapplication

import ContactEntity
import androidx.room.*

@Dao
interface SQLObject {
    @get:Query("SELECT * FROM contacts")
    val getAllContacts: List<ContactEntity>

    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getById(id: Long): ContactEntity

    @Insert
    fun insert(contact: ContactEntity): Long

    @Update
    fun update(contact: ContactEntity)

    @Delete
    fun delete(contact: ContactEntity)
}