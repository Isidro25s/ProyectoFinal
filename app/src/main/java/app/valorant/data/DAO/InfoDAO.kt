package app.valorant.data.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import app.valorant.data.entities.Agente
import app.valorant.data.entities.Arma
import app.valorant.data.entities.Mapa
import kotlinx.coroutines.flow.Flow

@Dao
interface InfoDAO {
    @Query("SELECT * FROM agente_table ORDER BY id ASC")
    fun getAgentes(): Flow<List<Agente>>

    @Query("SELECT * FROM mapas_table ORDER BY id ASC")
    fun getMapas(): Flow<List<Mapa>>

    @Query("SELECT * FROM armas_table ORDER BY id ASC")
    fun getArmas(): Flow<List<Arma>>

    @Query("SELECT * FROM agente_table WHERE id = :id")
    fun getAgente(id: Int): LiveData<Agente>

    @Query("SELECT * FROM mapas_table WHERE id = :id")
    fun getMapa(id: Int): LiveData<Mapa>

    @Query("SELECT * FROM armas_table WHERE id = :id")
    fun getArma(id: Int): LiveData<Arma>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAgente(agente: Agente)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMapa(mapa: Mapa)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArma(arma: Arma)

    @Query("DELETE FROM agente_table")
    suspend fun deleteAllAgentes()

    @Query("DELETE FROM mapas_table")
    suspend fun deleteAllMapas()

    @Query("DELETE FROM armas_table")
    suspend fun deleteAllArmas()

}