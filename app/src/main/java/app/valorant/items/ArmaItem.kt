package app.valorant.items

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import app.valorant.R
import app.valorant.data.InfoRoomDatabase
import app.valorant.data.entities.Arma
import app.valorant.databinding.ActivityArmaItemBinding
import app.valorant.presentation.resIdByName
import kotlinx.android.synthetic.main.activity_arma_item.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ArmaItem : AppCompatActivity() {
    private lateinit var database: InfoRoomDatabase
    private lateinit var arma: Arma
    private lateinit var mapLiveData: LiveData<Arma>
    lateinit var binding: ActivityArmaItemBinding

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_arma_item)

        binding = ActivityArmaItemBinding.inflate(layoutInflater)

        database = InfoRoomDatabase.getDatabase(this, applicationScope)

        val idArma = intent.getIntExtra("id", 0)

        mapLiveData = database.infoDao().getArma(idArma)

        mapLiveData.observe(this, {
            arma = it
            val resId: Int = this@ArmaItem.resIdByName(arma.imagen, "drawable")
            imagen_arma.setImageResource(resId)
            nombre_arma.text = arma.name
            tipo_arma.text = arma.tipo
            arma_bio.text = arma.bio
            creditos.text = arma.creditos
            capacidad.text = arma.capacidad
            damage_head.text = arma.damageH
            damage_body.text = arma.damageB
            damage_leg.text = arma.damageL
        })
    }
}