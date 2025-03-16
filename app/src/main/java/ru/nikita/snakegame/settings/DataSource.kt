package ru.nikita.snakegame.settings

import ru.nikita.snakegame.R

class DataSource {

    fun listColors(): List<ItemSettingList> {
        return listOf(
            ItemSettingList(R.color.green, R.string.setting_color_green, false, 99),
            ItemSettingList(R.color.blue, R.string.setting_color_blue, false, 99),
            ItemSettingList(R.color.yellow, R.string.setting_color_yellow, false, 99),
            ItemSettingList(R.color.orange, R.string.setting_color_orange, false, 99),
            ItemSettingList(R.color.purple, R.string.setting_color_purple, false, 99),
            ItemSettingList(R.color.light_blue, R.string.setting_color_light_blue, false, 99),
        )
    }

    fun listSpeed(): List<ItemSettingList> {
        return listOf(
            ItemSettingList(R.drawable.ic_one_24_black, R.string.setting_speed_very_slowly, false, 400),
            ItemSettingList(R.drawable.ic_two_24_black, R.string.setting_speed_slowly, false, 350),
            ItemSettingList(R.drawable.ic_three_24_black, R.string.setting_speed_normal, false, 300),
            ItemSettingList(R.drawable.ic_four_24_black, R.string.setting_speed_fast, false, 200),
            ItemSettingList(R.drawable.ic_five_24_black, R.string.setting_speed_very_fast, false, 175),
        )
    }

    fun listLevel(): List<ItemSettingList> {
        return listOf(
            ItemSettingList(R.drawable.ic_level_one_24_black, R.string.setting_level_very_easy, false, 0),
            ItemSettingList(R.drawable.ic_level_two_24_black, R.string.setting_level_easy, false, 1),
            ItemSettingList(R.drawable.ic_level_three_24_black, R.string.setting_level_hard, false, 2),
        )
    }

    fun listOrientation(): List<ItemSettingList> {
        return listOf(
            ItemSettingList(R.drawable.ic_portrait_24_black, R.string.setting_orientation_vertical, false, 99),
            ItemSettingList(R.drawable.ic_landscape_24_black, R.string.setting_orientation_horizontal, false, 99),
        )
    }

    fun listLanguage(): List<ItemSettingList> {
        return listOf(
            ItemSettingList(R.drawable.russia_flag, R.string.setting_language_rus, false, 99),
            ItemSettingList(R.drawable.great_britain_flag, R.string.setting_language_eng, false, 99),
            ItemSettingList(R.drawable.italy_flag, R.string.setting_language_it, false, 99),
            ItemSettingList(R.drawable.spain_flag, R.string.setting_language_esp, false, 99),
            ItemSettingList(R.drawable.finland_flag, R.string.setting_language_fin, false, 99),
        )
    }

    fun listTheme(): List<ItemSettingList> {
        return listOf(
            ItemSettingList(R.drawable.ic_light_mode_24_black, R.string.setting_theme_light, false, 1),
            ItemSettingList(R.drawable.ic_dark_mode_24_black, R.string.setting_theme_dark, false, 2),
            ItemSettingList(R.drawable.ic_phone_setting_24_black, R.string.setting_theme_as_system, false, -1),
        )
    }
}