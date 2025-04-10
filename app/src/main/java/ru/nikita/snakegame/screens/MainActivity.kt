package ru.nikita.snakegame.screens

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import ru.nikita.snakegame.R
import ru.nikita.snakegame.settings.DataSource
import ru.nikita.snakegame.utils.KEY_SCREEN_HEIGHT
import ru.nikita.snakegame.utils.KEY_SCREEN_WIDTH
import ru.nikita.snakegame.utils.KEY_SETTINGS
import ru.nikita.snakegame.utils.KEY_SETTINGS_SNAKE_THEME
import androidx.core.content.edit
import ru.nikita.snakegame.main.CanvasView
import ru.nikita.snakegame.main.Food
import ru.nikita.snakegame.main.HighScore
import ru.nikita.snakegame.main.Snake
import ru.nikita.snakegame.utils.KEY_HIGH_SCORE
import ru.nikita.snakegame.utils.KEY_SCREEN_COEFFICIENT

class MainActivity : AppCompatActivity() {

    private var press = 0L
    private val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Todo музыка
        //TODO при первом старте тема всегда светлая или как в телефоне
        //Todo режим турнир

        val pref = this.getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE)
        val colorIndex = pref.getInt(KEY_SETTINGS_SNAKE_THEME, 1)

        //maybe when()
        if (colorIndex == DataSource().listLevel()[0].value)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        else if (colorIndex == DataSource().listLevel()[1].value)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else if (colorIndex == DataSource().listLevel()[2].value)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)


        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        val width = displayMetrics.widthPixels

        var coefficient = 1;

        if (width in 701..1000)
            coefficient = 2
        else if (width > 1000)
            coefficient = 3

        Food.setCoef(coefficient)
        Snake.setCoef(coefficient)

        pref.edit() {
            putInt(KEY_SCREEN_COEFFICIENT, coefficient)
        }

        loadHighScore(pref)
    }

   /* override fun onBackPressed() {
        super.onBackPressed()
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (press + 2000L > System.currentTimeMillis())
                    finish()
                else
                    Toast.makeText(context, "Нажмите еще раз для выхода", Toast.LENGTH_SHORT)
                        .show()

                press = System.currentTimeMillis()
            }
        })
    }*/
}

private fun loadHighScore(pref: SharedPreferences) {

    val highScore = pref.getInt(KEY_HIGH_SCORE, 0)
    HighScore.highScore = highScore
}