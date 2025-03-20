package ru.nikita.snakegame.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.BuildCompat
import androidx.navigation.fragment.findNavController
import ru.nikita.snakegame.R
import ru.nikita.snakegame.settings.SettingsType
import ru.nikita.snakegame.utils.StringArg
import ru.nikita.snakegame.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.llSnakeColor.setOnClickListener {
            findNavController().navigate(
                R.id.action_settingsFragment_to_listSettingsFragment,
                Bundle().apply { typeSettings = SettingsType.COLOR.toString() })
        }

        binding.llSnakeSpeed.setOnClickListener {
            findNavController().navigate(
                R.id.action_settingsFragment_to_listSettingsFragment,
                Bundle().apply { typeSettings = SettingsType.SPEED.toString() })
        }

        binding.llSnakeLevel.setOnClickListener {
            findNavController().navigate(
                R.id.action_settingsFragment_to_listSettingsFragment,
                Bundle().apply { typeSettings = SettingsType.LEVEL.toString() })
        }

        binding.llOrientationScreen.setOnClickListener {
            findNavController().navigate(
                R.id.action_settingsFragment_to_listSettingsFragment,
                Bundle().apply { typeSettings = SettingsType.ORIENTATION.toString() })
        }

        binding.llLanguage.setOnClickListener {
            findNavController().navigate(
                R.id.action_settingsFragment_to_listSettingsFragment,
                Bundle().apply { typeSettings = SettingsType.LANGUAGE.toString() })
        }

        binding.llBack.setOnClickListener {
            findNavController().popBackStack(R.id.startFragment, false)
        }

        binding.llDarkMode.setOnClickListener {
            findNavController().navigate(
                R.id.action_settingsFragment_to_listSettingsFragment,
                Bundle().apply { typeSettings = SettingsType.THEME.toString() })
        }

        binding.llAbout.setOnClickListener {

            //TODO переделать через buildConfig
            Toast.makeText(requireContext(), "Версия приложения: v.1.0 ", Toast.LENGTH_LONG).show()
        }

        return binding.root
    }

    //todo в разметке повторяющиеся элемент сделать через стили

    companion object {
        var Bundle.typeSettings: String? by StringArg
    }

}