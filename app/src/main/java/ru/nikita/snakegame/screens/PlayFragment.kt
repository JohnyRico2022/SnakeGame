package ru.nikita.snakegame.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.nikita.snakegame.databinding.FragmentPlayBinding
import ru.nikita.snakegame.main.Food
import ru.nikita.snakegame.main.HighScore
import ru.nikita.snakegame.main.Snake
import ru.nikita.snakegame.settings.DataSource
import ru.nikita.snakegame.utils.KEY_HIGH_SCORE
import ru.nikita.snakegame.utils.KEY_SCREEN_COEFFICIENT
import ru.nikita.snakegame.utils.KEY_SETTINGS
import ru.nikita.snakegame.utils.KEY_SETTINGS_SNAKE_COLOR
import ru.nikita.snakegame.utils.KEY_SETTINGS_SNAKE_LEVEL
import ru.nikita.snakegame.utils.KEY_SETTINGS_SNAKE_SPEED

class PlayFragment : Fragment() {

    private val TAG = "MyLog"
    private var score = 0
    private var play = false
    private var coef = 1
    private lateinit var binding: FragmentPlayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayBinding.inflate(inflater, container, false)

        val colorList = DataSource().listColors()
        val speedList = DataSource().listSpeed()
        val levelList = DataSource().listLevel()

        val pref = this.requireContext().getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE)
        val colorIndex = pref.getInt(KEY_SETTINGS_SNAKE_COLOR, 0)
        //TODO по умолчанию сделать 3
        val speedIndex: Int = pref.getInt(KEY_SETTINGS_SNAKE_SPEED, 3)
        val levelIndex = pref.getInt(KEY_SETTINGS_SNAKE_LEVEL, 0)
        coef = pref.getInt(KEY_SCREEN_COEFFICIENT, 1)


        val layoutParams: ViewGroup.LayoutParams = binding.field.layoutParams
        layoutParams.width = 32 * coef * 10
        layoutParams.height = 32 * coef * 10

        binding.field.layoutParams = layoutParams

        binding.field.setSnakeColor(colorList[colorIndex].icon)
        binding.field.setLevel(levelList[levelIndex].value)
        binding.field.setCoef(coef)

        binding.tvScore.text = score.toString()

        binding.buttonStart.setOnClickListener {
            play = true
            play(speedList[speedIndex].value.toLong())
        }

        Log.d(TAG, "coef: $coef")
        Log.d(TAG, "width: ${layoutParams.width}")

        binding.buttonUp.setOnClickListener {
            if (Snake.direction != "down")
                Snake.direction = "up"
        }
        binding.buttonDown.setOnClickListener {
            if (Snake.direction != "up")
                Snake.direction = "down"
        }
        binding.buttonLeft.setOnClickListener {
            if (Snake.direction != "right")
                Snake.direction = "left"
        }
        binding.buttonRight.setOnClickListener {
            if (Snake.direction != "left")
                Snake.direction = "right"
        }

        binding.testTv.text = "коэффициент" + coef.toString()

        return binding.root
    }


    private fun play(speed: Long) {
        Log.d(TAG, "play: 1")
        GlobalScope.launch(Dispatchers.Main) {
            Log.d(TAG, "play: 2")

            while (play) {
                Log.d(TAG, "play: 3")

                when (Snake.direction) {
                    "up" -> {
                        Snake.headY -= coef * 10
                        if (!Snake.possibleMove())
                            endGame()
                    }

                    "down" -> {
                        Snake.headY += coef * 10
                        if (!Snake.possibleMove())
                            endGame()
                    }

                    "left" -> {
                        Snake.headX -= coef * 10
                        if (!Snake.possibleMove())
                            endGame()
                    }

                    "right" -> {
                        Snake.headX += coef * 10
                        if (!Snake.possibleMove())
                            endGame()
                    }
                }

                Snake.bodyParts.add(arrayOf(Snake.headX, Snake.headY))

                if (Snake.headX == Food.posX && Snake.headY == Food.posY) {
                    score++
                    Food.generateFood()
                } else
                    Snake.bodyParts.removeAt(0)

                binding.field.invalidate()
                delay(speed)
                binding.tvScore.text = score.toString()
            }
        }
    }

    //todo пересечение змеи с телом
    private fun endGame() {
        // Snake.alive = false
        play = false
        checkHighScore(score)

        score = 0
        Snake.reset()
        Food.resetFood()
    }

    private fun checkHighScore(score: Int) {

        if (score > HighScore.highScore) {
            HighScore.highScore = score
            val pref =
                this.requireContext().getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE)
            pref.edit() {
                putInt(KEY_HIGH_SCORE, HighScore.highScore)
            }
            Toast.makeText(
                requireContext(),
                "У вас новый лучший счет: ${HighScore.highScore}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}