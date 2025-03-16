package ru.nikita.snakegame.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.nikita.snakegame.R
import ru.nikita.snakegame.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStartBinding.inflate(inflater, container, false)

        //TODO Выход  из приложения по второму нажатию


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

        binding.btnAbout.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Данный раздел находится в разработке!",
                Toast.LENGTH_SHORT
            ).show()
        }

        return binding.root
    }
}