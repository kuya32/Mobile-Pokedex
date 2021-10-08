package com.example.mobilepokedex.data.remote.responses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

@Entity
data class Type(
    @Ignore val slot: Int,
    @ColumnInfo val type: TypeX
)