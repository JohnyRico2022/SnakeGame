package ru.nikita.snakegame.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import ru.nikita.snakegame.R
import ru.nikita.snakegame.databinding.FragmentStatsBinding

class StatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStatsBinding.inflate(inflater, container, false)

        binding.fragmentTitle.text = getString(R.string.button_stats)

        //TODO при нулевой статистике кнопка не активна

        binding.llBack.setOnClickListener {
            findNavController().popBackStack(R.id.startFragment, false)
        }


        binding.buttonDeleteStats.setOnClickListener {
            Toast.makeText(requireContext(),"Статистика удалена!", Toast.LENGTH_LONG).show()
        }








        return binding.root
    }
}