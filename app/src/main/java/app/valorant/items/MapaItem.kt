package app.valorant.items

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import app.valorant.R
import app.valorant.data.InfoRoomDatabase
import app.valorant.data.entities.Mapa
import app.valorant.databinding.ActivityMapaItemBinding
import app.valorant.presentation.resIdByName
import kotlinx.android.synthetic.main.activity_mapa_item.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MapaItem : AppCompatActivity() {
    private lateinit var database: InfoRoomDatabase
    private lateinit var mapa: Mapa
    private lateinit var mapLiveData: LiveData<Mapa>
    lateinit var binding: ActivityMapaItemBinding

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa_item)

        binding = ActivityMapaItemBinding.inflate(layoutInflater)

        database = InfoRoomDatabase.getDatabase(this, applicationScope)

        val idMapa = intent.getIntExtra("id", 0)

        mapLiveData = database.infoDao().getMapa(idMapa)

        mapLiveData.observe(this, {
            mapa = it
            var resId: Int = this@MapaItem.resIdByName(mapa.imagen, "drawable")
            imagen_mapa.setImageResource(resId)
            nombre_mapa.text = mapa.name
            mapa_bio.text = mapa.bio
            resId = this@MapaItem.resIdByName(mapa.minimap, "drawable")
            minimap.setImageResource(resId)
            resId = this@MapaItem.resIdByName(mapa.map1, "drawable")
            imagen_mapa_1.setImageResource(resId)
            resId = this@MapaItem.resIdByName(mapa.map2, "drawable")
            imagen_mapa_2.setImageResource(resId)
            resId = this@MapaItem.resIdByName(mapa.map3, "drawable")
            imagen_mapa_3.setImageResource(resId)
            resId = this@MapaItem.resIdByName(mapa.map4, "drawable")
            imagen_mapa_4.setImageResource(resId)
        })
    }
}