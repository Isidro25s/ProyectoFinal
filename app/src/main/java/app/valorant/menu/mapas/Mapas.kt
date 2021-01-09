package app.valorant.menu.mapas

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import app.valorant.Application
import app.valorant.R
import app.valorant.databinding.ActivityMapasBinding
import app.valorant.presentation.ui.MapaAdapter
import app.valorant.presentation.viewmodel.InfoViewModel
import app.valorant.presentation.viewmodel.InfoViewModelFactory

class Mapas : AppCompatActivity() {

    private val infoViewModel: InfoViewModel by viewModels {
        InfoViewModelFactory((application as Application).repository)
    }

    lateinit var binding: ActivityMapasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapas)

        binding = ActivityMapasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerMapas.layoutManager = layoutManager

        infoViewModel.allMapa.observe(this) { mapas ->
            binding.recyclerMapas.adapter = MapaAdapter(this, mapas)
        }
    }
}
