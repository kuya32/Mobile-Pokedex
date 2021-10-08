package com.example.mobilepokedex.data.remote.responses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

@Entity
data class TypeX(
    @ColumnInfo val name: String,
    @Ignore val url: String
)