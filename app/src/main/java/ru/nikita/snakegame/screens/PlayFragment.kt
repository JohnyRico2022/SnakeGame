package ru.nikita.snakegame.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.nikita.snakegame.main.Food
import ru.nikita.snakegame.main.Snake
import ru.nikita.snakegame.databinding.FragmentPlayBinding
import ru.nikita.snakegame.settings.DataSource
import ru.nikita.snakegame.utils.KEY_SETTINGS
import ru.nikita.snakegame.utils.KEY_SETTINGS_SNAKE_COLOR
import ru.nikita.snakegame.utils.KEY_SETTINGS_SNAKE_LEVEL
import ru.nikita.snakegame.utils.KEY_SETTINGS_SNAKE_SPEED

class PlayFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPlayBinding.inflate(inflater, container, false)

        val colorList = DataSource().listColors()
        val speedList = DataSource().listSpeed()
        val levelList = DataSource().listLevel()

        val pref = this.requireContext().getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE)
        val colorIndex = pref.getInt(KEY_SETTINGS_SNAKE_COLOR, 0)
        val speedIndex = pref.getInt(KEY_SETTINGS_SNAKE_SPEED, 300)
        val levelIndex = pref.getInt(KEY_SETTINGS_SNAKE_LEVEL, 0)

        binding.field.setSnakeColor(colorList[colorIndex].icon)
        binding.field.setLevel(levelList[levelIndex].value)

        CoroutineScope(Dispatchers.IO).launch {
            while (true) {
                while (Snake.alive) {
                    when (Snake.direction) {
                        "up" -> {
                            Snake.headY -= 20
                            if (!Snake.possibleMove())
                                endGame()
                        }

                        "down" -> {
                            Snake.headY += 20
                            if (!Snake.possibleMove())
                                endGame()
                        }

                        "left" -> {
                            Snake.headX -= 20
                            if (!Snake.possibleMove())
                                endGame()
                        }

                        "right" -> {
                            Snake.headX += 20
                            if (!Snake.possibleMove())
                                endGame()
                        }
                    }
                    Snake.bodyParts.add(arrayOf(Snake.headX, Snake.headY))

                    if (Snake.headX == Food.posX && Snake.headY == Food.posY) {
                        Food.generate()
                    } else
                        Snake.bodyParts.removeAt(0)

                    binding.field.invalidate()
                    delay(speedList[speedIndex].value.toLong())
                }
            }
        }


        binding.buttonUp.setOnClickListener {
            Snake.alive = true
            if (Snake.direction != "down")
                Snake.direction = "up"
        }
        binding.buttonDown.setOnClickListener {
            Snake.alive = true
            if (Snake.direction != "up")
                Snake.direction = "down"
        }
        binding.buttonLeft.setOnClickListener {
            Snake.alive = true
            if (Snake.direction != "right")
                Snake.direction = "left"
        }
        binding.buttonRight.setOnClickListener {
            Snake.alive = true
            if (Snake.direction != "left")
                Snake.direction = "right"
        }
        return binding.root
    }

    private fun endGame() {

        //todo пересечение змеи с телом
        Snake.alive = false
        Snake.reset()
        Food.resetFood()
    }

}