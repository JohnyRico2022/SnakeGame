package ru.nikita.snakegame.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import ru.nikita.snakegame.R
import ru.nikita.snakegame.adapter.PlayersAdapter
import ru.nikita.snakegame.databinding.FragmentTournamentBinding
import ru.nikita.snakegame.models.Players
import ru.nikita.snakegame.screens.SettingsFragment.Companion.typeSettings
import ru.nikita.snakegame.settings.DataSource
import ru.nikita.snakegame.settings.SettingsType

class TournamentFragment : Fragment() {

    private var uid = 0
    private var playersList = ArrayList<Players>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentTournamentBinding.inflate(inflater, container, false)

        val adapterPlayers = PlayersAdapter()
        val recycler = binding.recyclerPlayers
        adapterPlayers.submitList(playersList)
        recycler.adapter = adapterPlayers

        val listIcon = DataSource().listIcon()

        binding.buttonAddPlayer.setOnClickListener {

            val name = binding.editTextAddPlayer.text.toString().trim()

            if(name.isEmpty()){
                Toast.makeText(requireContext(), "имя неможет быть пустым!", Toast.LENGTH_LONG).show()
               return@setOnClickListener
            }


            val newPlayer = Players(uid, listIcon[uid], name, 0)

            playersList.add(newPlayer)

            adapterPlayers.notifyDataSetChanged()

            uid++
            binding.editTextAddPlayer.text.clear()
            checkPlayersQuantity(binding.llAddPlayers)
        }


        //TODO сделать правильную навигацию назад
        binding.buttonStartTournament.setOnClickListener {

            if (playersList.size < 2) {
                Toast.makeText(requireContext(), "минимум должно быть 2 игрока", Toast.LENGTH_SHORT)
                    .show()
            } else {

                Log.d("MyLog", "$playersList ")

                findNavController().navigate(
                    R.id.action_tournamentFragment_to_playFragment,
                    Bundle().apply { typeSettings = "турнир" })

            }
        }








        return binding.root
    }


    private fun checkPlayersQuantity(ll: LinearLayout) {

        if (playersList.size == 5) {
            ll.visibility = View.GONE
        }

    }
}