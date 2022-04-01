package com.reihan.notto.data.local

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "notto_data_table")
@Parcelize
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
    val image: String
) : Parcelable
