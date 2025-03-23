package ru.nikita.snakegame.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ru.nikita.snakegame.R
import ru.nikita.snakegame.databinding.FragmentTournamentBinding
import ru.nikita.snakegame.screens.SettingsFragment.Companion.typeSettings
import ru.nikita.snakegame.settings.SettingsType

class TournamentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTournamentBinding.inflate(inflater, container, false)



//TODO сделать правильную навигацию назад
        binding.buttonStartTournament.setOnClickListener {

            findNavController().navigate(
                R.id.action_tournamentFragment_to_playFragment,
                Bundle().apply { typeSettings = "турнир" })

        }

        return binding.root
    }
}