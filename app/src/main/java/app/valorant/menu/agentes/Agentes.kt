package app.valorant.menu.agentes

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import app.valorant.Application
import app.valorant.R
import app.valorant.databinding.ActivityAgentesBinding
import app.valorant.presentation.ui.AgenteAdapter
import app.valorant.presentation.viewmodel.InfoViewModel
import app.valorant.presentation.viewmodel.InfoViewModelFactory


class Agentes : AppCompatActivity() {
    private val infoViewModel: InfoViewModel by viewModels {
        InfoViewModelFactory((application as Application).repository)
    }

    lateinit var binding: ActivityAgentesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agentes)

        binding = ActivityAgentesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerAgentes.layoutManager = layoutManager

        infoViewModel.allAgente.observe(this) { agentes ->
            binding.recyclerAgentes.adapter = AgenteAdapter(this, agentes)
        }
    }

}

