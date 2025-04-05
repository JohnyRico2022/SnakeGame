package ru.nikita.snakegame.models

data class Players(
    val id: Int,
    val icon: String,
    val name: String,
    var score: Int
)