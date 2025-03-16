package ru.nikita.snakegame.main

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import ru.nikita.snakegame.settings.DataSource

class CanvasView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

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
        Log.d("MyLog", "setLevel: $level ")
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        field.color = Color.LTGRAY
        food.color = Color.RED
        wall.color = Color.BLACK

        //draw level 20 клеток по 20 пикселей
        canvas.drawRect(0f, 0f, 420f, 420f, field)


        for (i in Snake.bodyParts) {
            canvas.drawRect(i[0], i[1], i[0] + 20, i[1] + 20, snakeBody)
        }

        // draw food from array
        // left x, top y, right x+30, bottom y+30
        canvas.drawRect(Food.posX, Food.posY, Food.posX + 20, Food.posY + 20, food)


        if (level == 1) {
            Log.d("MyLog", "if ")
            Wall.wallParts.clear()
            Wall.generateWAllOne()

            Log.d("MyLog", "${Wall.wallParts} ")
            for (i in Wall.wallParts) {
                canvas.drawRect(i.first, i.second, i.first + 20, i.second + 20, wall)
            }
        } else if (level == 2) {
            Log.d("MyLog", "else ")
            Wall.wallParts.clear()
            Wall.generateWAllOne()
            Wall.generateWAllTwo()

            Log.d("MyLog", "${Wall.wallParts} ")
            for (i in Wall.wallParts) {
                canvas.drawRect(i.first, i.second, i.first + 20, i.second + 20, wall)
            }
        }
    }
}