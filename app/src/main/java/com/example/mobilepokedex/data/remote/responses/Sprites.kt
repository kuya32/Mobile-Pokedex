package com.example.mobilepokedex.data.remote.responses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore

@Entity
data class Sprites(
    @Ignore val back_default: String,
    @Ignore val back_female: Any,
    @Ignore val back_shiny: String,
    @Ignore val back_shiny_female: Any,
    @ColumnInfo val front_default: String,
    @Ignore val front_female: Any,
    @Ignore val front_shiny: String,
    @Ignore val front_shiny_female: Any,
    @Ignore val other: Other,
    @Ignore val versions: Versions
)