package ru.nikita.snakegame.main

class Snake {

    companion object {
        var headX = 0f
        var headY = 0f
        var bodyParts =
            mutableListOf(arrayOf(0f, 0f))
        var direction = "right";
        var alive = false;

        fun possibleMove(): Boolean {
            //TODO врезались в стену
            //TODO врезались сами в себя

            if (headX < 0f || headX > 400f || headY < 0f || headY > 400) // врезались в край
                return false
            return true
        }

        fun reset() {
            headX = 0f;
            headY = 0f;
            bodyParts = mutableListOf(arrayOf(0f, 0f))
            direction = "right";
        }
    }
}