package ru.nikita.snakegame.main

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class CanvasView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var canvasCoef = 1
    private val field = Paint()
    private val food = Paint()
    private val wall = Paint()
    private val snakeBody = Paint()
    private var level = 0;

    fun setSnakeColor(color: Int) {
        snakeBody.color = context.getColor(color)
    }

    fun setLevel(level: Int) {
        this.level = level
    }

    fun setCoef(coef: Int) {
        this.canvasCoef = coef
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        field.color = Color.LTGRAY
        food.color = Color.RED
        wall.color = Color.BLACK

        //draw level 32 клетки по 20/30 пикселей
        canvas.drawRect(0f, 0f, 32 * canvasCoef * 10f, 32 * canvasCoef * 10f, field)


        for (i in Snake.bodyParts) {
            canvas.drawRect(i[0], i[1], i[0] + canvasCoef * 10, i[1] + canvasCoef * 10, snakeBody)
        }

        // draw food from array
        canvas.drawRect(
            Food.posX,
            Food.posY,
            Food.posX + canvasCoef * 10,
            Food.posY + canvasCoef * 10,
            food
        )

        if (level == 1) {
            Wall.wallParts.clear()
            Wall.generateWAllOne()

            for (i in Wall.wallParts) {
                canvas.drawRect(i.first, i.second, i.first + 20, i.second + 20, wall)
            }

        } else if (level == 2) {

            Wall.wallParts.clear()
            Wall.generateWAllOne()
            Wall.generateWAllTwo()

            for (i in Wall.wallParts) {
                canvas.drawRect(i.first, i.second, i.first + 20, i.second + 20, wall)
            }
        }
    }
}