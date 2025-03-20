package ru.nikita.snakegame.screens

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
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
import ru.nikita.snakegame.main.Snake
import ru.nikita.snakegame.utils.KEY_SCREEN_COEFFICIENT

class MainActivity : AppCompatActivity() {
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
        val height = displayMetrics.heightPixels

        Log.d("MyLog", "$width ___ $height ")

        var coefficient = 1;

        if (width in 701..1000)
            coefficient = 2
        else if (width > 1000)
            coefficient = 3

        Log.d("MyLog", "$coefficient ")

        Food.setCoef(coefficient)
        Snake.setCoef(coefficient)

        pref.edit() {
            putInt(KEY_SCREEN_COEFFICIENT, coefficient)
        }

    }
}