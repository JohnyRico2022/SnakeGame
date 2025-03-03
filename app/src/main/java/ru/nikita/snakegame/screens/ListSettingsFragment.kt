package ru.nikita.snakegame.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.nikita.snakegame.adapter.Adapter
import ru.nikita.snakegame.adapter.OnInteractionListener
import ru.nikita.snakegame.databinding.FragmentListSettingsBinding
import ru.nikita.snakegame.screens.SettingsFragment.Companion.typeSettings
import ru.nikita.snakegame.settings.DataSource
import ru.nikita.snakegame.settings.ItemSettingList
import ru.nikita.snakegame.settings.SettingsType
import ru.nikita.snakegame.utils.KEY_SETTINGS
import ru.nikita.snakegame.utils.KEY_SETTINGS_SNAKE_COLOR
import ru.nikita.snakegame.utils.KEY_SETTINGS_SNAKE_LANGUAGE
import ru.nikita.snakegame.utils.KEY_SETTINGS_SNAKE_LEVEL
import ru.nikita.snakegame.utils.KEY_SETTINGS_SNAKE_ORIENTATION
import ru.nikita.snakegame.utils.KEY_SETTINGS_SNAKE_SPEED
import ru.nikita.snakegame.utils.KEY_SETTINGS_SNAKE_THEME


class ListSettingsFragment : Fragment() {

    private val colorList = DataSource().listColors()
    private val speedList = DataSource().listSpeed()
    private val levelList = DataSource().listLevel()
    private val orientationList = DataSource().listOrientation()
    private val languageList = DataSource().listLanguage()
    private val themeList = DataSource().listTheme()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentListSettingsBinding.inflate(inflater, container, false)
        val settingsType = arguments?.typeSettings

        val pref = this.requireActivity().getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE)

        val colorIndex = pref.getInt(KEY_SETTINGS_SNAKE_COLOR, 0)
        val speedIndex = pref.getInt(KEY_SETTINGS_SNAKE_SPEED, 0)
        val levelIndex = pref.getInt(KEY_SETTINGS_SNAKE_LEVEL, 0)
        val orientationIndex = pref.getInt(KEY_SETTINGS_SNAKE_ORIENTATION, 0)
        val languageIndex = pref.getInt(KEY_SETTINGS_SNAKE_LANGUAGE, 0)
        val themeIndex = pref.getInt(KEY_SETTINGS_SNAKE_THEME, 0)

        colorList[colorIndex].check = true
        speedList[speedIndex].check = true
        levelList[levelIndex].check = true
        orientationList[orientationIndex].check = true
        languageList[languageIndex].check = true
        themeList[themeIndex].check = true

        val adapterSettingsList =
            Adapter(requireContext().resources, object : OnInteractionListener {

                override fun onButtonClicked(item: ItemSettingList) {
                    if (settingsType.equals(SettingsType.COLOR.toString())) {

                        val index = colorList.indexOf(item)
                        for (i: Int in colorList.indices)
                            colorList[i].check = false

                        colorList[index].check = true

                        pref.edit()
                            .putInt(KEY_SETTINGS_SNAKE_COLOR, index)
                            .apply()

                    } else if (settingsType.equals(SettingsType.SPEED.toString())) {

                        val index = speedList.indexOf(item)
                        for (i: Int in speedList.indices)
                            speedList[i].check = false
                        speedList[index].check = true

                        pref.edit()
                            .putInt(KEY_SETTINGS_SNAKE_SPEED, index)
                            .apply()



                    } else if (settingsType.equals(SettingsType.LEVEL.toString())) {

                        val index = levelList.indexOf(item)
                        for (i: Int in levelList.indices)
                            levelList[i].check = false
                        levelList[index].check = true

                        pref.edit()
                            .putInt(KEY_SETTINGS_SNAKE_LEVEL, index)
                            .apply()

                    } else if (settingsType.equals(SettingsType.ORIENTATION.toString())) {

                        val index = orientationList.indexOf(item)
                        for (i: Int in orientationList.indices)
                            orientationList[i].check = false
                        orientationList[index].check = true

                        pref.edit()
                            .putInt(KEY_SETTINGS_SNAKE_ORIENTATION, index)
                            .apply()

                    } else if (settingsType.equals(SettingsType.LANGUAGE.toString())) {

                        val index = languageList.indexOf(item)
                        for (i: Int in languageList.indices)
                            languageList[i].check = false
                        languageList[index].check = true

                        pref.edit()
                            .putInt(KEY_SETTINGS_SNAKE_LANGUAGE, index)
                            .apply()

                    } else if (settingsType.equals(SettingsType.THEME.toString())) {

                        val index = themeList.indexOf(item)
                        for (i: Int in themeList.indices)
                            themeList[i].check = false
                        themeList[index].check = true

                        pref.edit()
                            .putInt(KEY_SETTINGS_SNAKE_THEME, index)
                            .apply()

                        if (themeList[0].check)
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        else if (themeList[1].check)
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        else if (themeList[2].check)
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

                    }

                    binding.recycler.adapter?.notifyDataSetChanged()
                }
            })

        binding.recycler.adapter = adapterSettingsList

        val animation: LayoutAnimationController = AnimationUtils
            .loadLayoutAnimation(requireContext(), ru.nikita.snakegame.R.anim.layout_animation)
        binding.recycler.layoutAnimation = animation

        when (settingsType) {
            SettingsType.COLOR.toString() -> {
                binding.fragmentTitle.text =
                    resources.getText(ru.nikita.snakegame.R.string.settings_title_color)
                adapterSettingsList.submitList(colorList)
            }

            SettingsType.SPEED.toString() -> {
                binding.fragmentTitle.text =
                    resources.getText(ru.nikita.snakegame.R.string.settings_title_speed)
                adapterSettingsList.submitList(speedList)
            }

            SettingsType.LEVEL.toString() -> {
                binding.fragmentTitle.text =
                    resources.getText(ru.nikita.snakegame.R.string.settings_title_level)
                adapterSettingsList.submitList(levelList)
            }

            SettingsType.ORIENTATION.toString() -> {
                binding.fragmentTitle.text =
                    resources.getText(ru.nikita.snakegame.R.string.settings_title_orientation)
                adapterSettingsList.submitList(orientationList)
            }

            SettingsType.LANGUAGE.toString() -> {
                binding.fragmentTitle.text =
                    resources.getText(ru.nikita.snakegame.R.string.settings_title_language)
                adapterSettingsList.submitList(languageList)
            }

            SettingsType.THEME.toString() -> {
                binding.fragmentTitle.text =
                    resources.getText(ru.nikita.snakegame.R.string.settings_title_theme)
                adapterSettingsList.submitList(themeList)
            }
        }

        binding.llBack.setOnClickListener {
            findNavController().popBackStack(ru.nikita.snakegame.R.id.settingsFragment, false)
        }

        return binding.root
    }
}