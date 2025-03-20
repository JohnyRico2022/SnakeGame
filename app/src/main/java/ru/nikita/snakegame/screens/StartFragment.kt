package ru.nikita.snakegame.screens

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.nikita.snakegame.R
import ru.nikita.snakegame.databinding.FragmentStartBinding
import ru.nikita.snakegame.utils.KEY_SCREEN_HEIGHT
import ru.nikita.snakegame.utils.KEY_SCREEN_WIDTH
import ru.nikita.snakegame.utils.KEY_SETTINGS
import ru.nikita.snakegame.utils.KEY_SETTINGS_SNAKE_THEME

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStartBinding.inflate(inflater, container, false)

        //TODO Выход  из приложения по второму нажатию
        val pref = this.requireActivity().getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE)
        val w = pref.getInt(KEY_SCREEN_WIDTH, 1)
        val h = pref.getInt(KEY_SCREEN_HEIGHT, 1)
        //binding.screen.text = w.toString() + " x " + h.toString()

        //TODO Кастомный тост

        binding.btnPlay.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_playFragment)
        }

        binding.btnSettings.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_settingsFragment)
        }

        binding.btnStatistics.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_statsFragment)
        }

        binding.btnTournament.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Данный раздел находится в разработке!",
                Toast.LENGTH_SHORT
            ).show()
        }

        return binding.root
    }
}