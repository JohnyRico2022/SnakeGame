package ru.nikita.snakegame

class Food {

    companion object {
        var posX = 400f
        var posY = 400f

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