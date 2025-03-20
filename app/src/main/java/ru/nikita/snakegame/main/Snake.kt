package ru.nikita.snakegame.main

import android.util.Log

class Snake {

    companion object {

        var snakeCoef = 1
        var headX = 0f
        var headY = 0f
        var bodyParts = mutableListOf(arrayOf(0f, 0f))
        var direction = "right"
       // var alive = false

        fun possibleMove(): Boolean {
            //TODO врезались в стену
            //TODO врезались сами в себя
            Log.d("MyLog", "possibleMove: $snakeCoef")
            if (headX < 0f || headX > 32 *snakeCoef * 10f || headY < 0f || headY > 32 * snakeCoef * 10f) // врезались в край
                return false
            return true
        }

        fun reset() {
            headX = 0f;
            headY = 0f;
            bodyParts = mutableListOf(arrayOf(0f, 0f))
            direction = "right";
        }

        fun setCoef(coef: Int) {
            this.snakeCoef = coef
        }
    }
}