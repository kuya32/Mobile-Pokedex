package com.github.kuya32.mobilepokedex.data.remote.responses

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)