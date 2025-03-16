package ru.nikita.snakegame.main

class Food {

    companion object {
        var posX = 200f
        var posY = 200f

        fun generate() {
            posX = (1..20).random().toFloat() * 20
            posY = (1..20).random().toFloat() * 20
        }

        fun resetFood() {
            posX = 200f
            posY = 200f
        }
    }
}