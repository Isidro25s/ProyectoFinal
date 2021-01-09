package app.valorant.data.repositories

import app.valorant.data.DAO.InfoDAO
import app.valorant.data.entities.Agente
import app.valorant.data.entities.Arma
import app.valorant.data.entities.Mapa
import kotlinx.coroutines.flow.Flow

class InfoRepository(private val infoDAO: InfoDAO) {
    val allAgente: Flow<List<Agente>> = infoDAO.getAgentes()
    val allMapa: Flow<List<Mapa>> = infoDAO.getMapas()
    val allArma: Flow<List<Arma>> = infoDAO.getArmas()
}