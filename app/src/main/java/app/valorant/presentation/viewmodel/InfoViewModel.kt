package app.valorant.presentation.viewmodel

import androidx.lifecycle.*
import app.valorant.data.entities.Agente
import app.valorant.data.entities.Arma
import app.valorant.data.entities.Mapa
import app.valorant.data.repositories.InfoRepository

class InfoViewModel(repository: InfoRepository) : ViewModel() {
    val allAgente: LiveData<List<Agente>> = repository.allAgente.asLiveData()
    val allMapa: LiveData<List<Mapa>> = repository.allMapa.asLiveData()
    val allArma: LiveData<List<Arma>> = repository.allArma.asLiveData()
}

class InfoViewModelFactory(private val repository: InfoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InfoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return InfoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}