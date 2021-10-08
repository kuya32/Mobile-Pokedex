package com.example.mobilepokedex.data.remote.responses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

@Entity
data class Stat(
    @ColumnInfo val base_stat: Int,
    @Ignore val effort: Int,
    @ColumnInfo val stat: StatX
)