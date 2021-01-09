package app.valorant

import android.app.Application
import app.valorant.data.InfoRoomDatabase
import app.valorant.data.repositories.InfoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Application : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    /*
    Usando by lazy la base de datos y el repositorio solo se creen cuando se necesitan
    en lugar de cuando se inicia la aplicación
     */
    val database by lazy { InfoRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { InfoRepository(database.infoDao()) }
}
