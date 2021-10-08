package com.example.mobilepokedex.data.remote.responses

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class Pokemon(
    @Ignore val abilities: List<Ability>,
    @Ignore val base_experience: Int,
    @Ignore val forms: List<Form>,
    @Ignore val game_indices: List<GameIndice>,
    @ColumnInfo val height: Int,
    @Ignore val held_items: List<Any>,
    @PrimaryKey val id: Int,
    @Ignore val is_default: Boolean,
    @Ignore val location_area_encounters: String,
    @Ignore val moves: List<Move>,
    @ColumnInfo val name: String,
    @Ignore val order: Int,
    @Ignore val past_types: List<Any>,
    @Ignore val species: Species,
    @ColumnInfo val sprites: Sprites,
    @ColumnInfo val stats: List<Stat>,
    @ColumnInfo val types: List<Type>,
    @ColumnInfo val weight: Int
)