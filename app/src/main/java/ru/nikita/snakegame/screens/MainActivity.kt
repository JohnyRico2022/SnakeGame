package ru.nikita.snakegame.screens

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import ru.nikita.snakegame.R
import ru.nikita.snakegame.settings.DataSource
import ru.nikita.snakegame.utils.KEY_SETTINGS
import ru.nikita.snakegame.utils.KEY_SETTINGS_SNAKE_COLOR
import ru.nikita.snakegame.utils.KEY_SETTINGS_SNAKE_THEME

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref = this.getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE)
        var colorIndex = pref.getInt(KEY_SETTINGS_SNAKE_THEME, 1)

//maybe when()
        if (colorIndex == DataSource().listLevel()[0].value)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        else if (colorIndex == DataSource().listLevel()[1].value)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else if (colorIndex == DataSource().listLevel()[2].value)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

    }
}