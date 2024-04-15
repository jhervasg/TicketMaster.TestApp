package com.example.ticketmasterapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ticketmasterapp.data.local.TicketsModel

@Dao
interface TicketsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTicketsResults(ticketsModel: List<TicketsModel>)

    @Query("DELETE FROM tickets_table")
    suspend fun deleteAllTicketsResults()

    @Query("SELECT * FROM tickets_table")
    suspend fun readAllTicketsResults(): List<TicketsModel>

    @Query("SELECT COUNT(*) FROM tickets_table")
    suspend fun getTicketsRowCount(): Int
}