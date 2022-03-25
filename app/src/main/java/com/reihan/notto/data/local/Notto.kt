package com.reihan.notto.data.local

import androidx.annotation.ColorInt
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notto_data_table")
data class Notto(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_notto")
    val id: Int,
    @ColumnInfo(name = "title_notto")
    val title: String,
    @ColumnInfo(name = "date_notto")
    val date: String,
    @ColumnInfo(name = "desc_notto")
    val desc: String,
)
