package ru.nikita.snakegame.screens

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import ru.nikita.snakegame.R
import ru.nikita.snakegame.databinding.FragmentStatsBinding
import ru.nikita.snakegame.main.HighScore
import ru.nikita.snakegame.utils.KEY_HIGH_SCORE
import ru.nikita.snakegame.utils.KEY_SETTINGS
import ru.nikita.snakegame.utils.KEY_SETTINGS_SNAKE_COLOR

class StatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStatsBinding.inflate(inflater, container, false)

        binding.fragmentTitle.text = getString(R.string.button_stats)

        //TODO при нулевой статистике кнопка не активна
        val pref = this.requireContext().getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE)
        val highScore = pref.getInt(KEY_HIGH_SCORE, 0)

        binding.tvHighScore.text = highScore.toString()

        binding.llBack.setOnClickListener {
            findNavController().popBackStack(R.id.startFragment, false)
        }


        //TODO спросить про удаление и предупредить о безвозвратной потере данных
        binding.buttonDeleteStats.setOnClickListener {
            binding.tvHighScore.text = "0"
            pref.edit() {
                putInt(KEY_HIGH_SCORE, 0)
            }
            HighScore.highScore = 0
            Toast.makeText(requireContext(), "Статистика удалена!", Toast.LENGTH_LONG).show()
        }








        return binding.root
    }
}