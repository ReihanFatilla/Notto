package com.reihan.notto.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NottoDAO {

    @Insert
    suspend fun insertNotto(notto: Notto)

    @Update
    suspend fun updateNotto(notto: Notto)

    @Delete
    suspend fun deleteNotto(notto: Notto)

    @Query("DELETE FROM notto_data_table")
    suspend fun deleteAllNotto()

    @Query("SELECT * FROM notto_data_table")
    fun getAllNotto(): LiveData<List<Notto>>
}