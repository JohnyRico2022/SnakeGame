package ru.nikita.snakegame.main

class Wall {

    companion object {

        var wallParts = mutableListOf<Pair<Float, Float>>()

         fun generateWAllOne() {
            wallParts.add(0, Pair(40F, 100F))
            wallParts.add(1, Pair(40F, 120F))
            wallParts.add(2, Pair(40F, 140F))
            wallParts.add(3, Pair(40F, 160F))
            wallParts.add(3, Pair(40F, 180F))
            wallParts.add(3, Pair(40F, 200F))
        }

         fun generateWAllTwo() {
            wallParts.add(0, Pair(300F, 260F))
            wallParts.add(1, Pair(300F, 280F))
            wallParts.add(2, Pair(300F, 300F))
            wallParts.add(3, Pair(300F, 310F))
            wallParts.add(3, Pair(300F, 320F))
            wallParts.add(3, Pair(300F, 340F))
        }
    }
}