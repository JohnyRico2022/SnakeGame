package ru.nikita.snakegame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CanvasView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val level = Paint()
    private val food = Paint()
    private val snakeBody = Paint()

    fun setSnakeColor(color: Int) {
        snakeBody.color = context.getColor(color)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        level.color = Color.LTGRAY
        food.color = Color.RED
        // snakeBody.color = Color.GREEN

        //draw level 20 клеток по 20 пикселей
        canvas.drawRect(0f, 0f, 420f, 420f, level)


        for (i in Snake.bodyParts) {
            canvas.drawRect(i[0], i[1], i[0] + 20, i[1] + 20, snakeBody)
        }

        // draw food from array
        // left x, top y, right x+30, bottom y+30
        canvas.drawRect(Food.posX, Food.posY, Food.posX + 20, Food.posY + 20, food)


    }
}