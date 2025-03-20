package ru.nikita.snakegame.main

class Food {

    companion object {

        var foodCoef = 1
        var posX = 300f
        var posY = 300f

        fun generateFood() {
            posX = (1..31).random().toFloat() * foodCoef * 10
            posY = (1..31).random().toFloat() * foodCoef * 10
        }

        fun resetFood() {
            posX = 16 * foodCoef * 10f
            posY = 16 * foodCoef * 10f
        }

        fun setCoef(coef: Int) {
            this.foodCoef = coef
        }
    }
}