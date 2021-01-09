package app.valorant.menu.armas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.valorant.Application
import app.valorant.R
import app.valorant.databinding.ActivityAgentesBinding
import app.valorant.databinding.ActivityArmasBinding
import app.valorant.presentation.ui.AgenteAdapter
import app.valorant.presentation.ui.ArmaAdapter
import app.valorant.presentation.viewmodel.InfoViewModel
import app.valorant.presentation.viewmodel.InfoViewModelFactory

class Armas : AppCompatActivity() {

    private val infoViewModel: InfoViewModel by viewModels {
        InfoViewModelFactory((application as Application).repository)
    }

    lateinit var binding: ActivityArmasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_armas)

        binding = ActivityArmasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerArmas.layoutManager = layoutManager

        infoViewModel.allArma.observe(this) { armas ->
            binding.recyclerArmas.adapter = ArmaAdapter(this,armas)
        }
    }
}