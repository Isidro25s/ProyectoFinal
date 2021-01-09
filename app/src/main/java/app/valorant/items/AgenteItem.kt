package app.valorant.items

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import app.valorant.DialogFragment
import app.valorant.R
import app.valorant.data.InfoRoomDatabase
import app.valorant.data.InfoRoomDatabase.Companion.getDatabase
import app.valorant.data.entities.Agente
import app.valorant.databinding.ActivityAgenteItemBinding
import app.valorant.presentation.resIdByName
import kotlinx.android.synthetic.main.activity_agente_item.*
import kotlinx.android.synthetic.main.activity_mapa_item.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class AgenteItem : AppCompatActivity() {

    private lateinit var database: InfoRoomDatabase
    private lateinit var agente: Agente
    private lateinit var agenteLiveData: LiveData<Agente>
    lateinit var binding: ActivityAgenteItemBinding
    val applicationScope = CoroutineScope(SupervisorJob())

    lateinit var nombre: String
    lateinit var desc: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agente_item)

        card_q.setOnClickListener {
            nombre = agente.nombre_q
            desc = agente.desc_q
            val dialog = DialogFragment(nombre, desc)
            dialog.show(supportFragmentManager, "HabilidadFragment")
        }

        card_e.setOnClickListener {
            nombre = agente.nombre_e
            desc = agente.desc_e
            val dialog = DialogFragment(nombre, desc)
            dialog.show(supportFragmentManager, "HabilidadFragment")
        }

        card_c.setOnClickListener {
            nombre = agente.nombre_c
            desc = agente.desc_c
            val dialog = DialogFragment(nombre, desc)
            dialog.show(supportFragmentManager, "HabilidadFragment")
        }

        card_x.setOnClickListener {
            nombre = agente.nombre_x
            desc = agente.desc_x
            val dialog = DialogFragment(nombre, desc)
            dialog.show(supportFragmentManager, "HabilidadFragment")
        }

        binding = ActivityAgenteItemBinding.inflate(layoutInflater)

        database = getDatabase(this, applicationScope)

        val idAgente = intent.getIntExtra("id", 0)

        agenteLiveData = database.infoDao().getAgente(idAgente)

        agenteLiveData.observe(this, {
            agente = it
            var resId: Int = this@AgenteItem.resIdByName(agente.imagen, "drawable")
            imagen_agente.setImageResource(resId)
            nombre_agente.text = agente.name
            rol_container.text = agente.rol
            agente_bio.text = agente.bio
            resId = this@AgenteItem.resIdByName(agente.icon_q, "drawable")
            icon_habilidad_q.setImageResource(resId)
            nombre_habilidad_q.text = agente.nombre_q
            resId = this@AgenteItem.resIdByName(agente.icon_e, "drawable")
            icon_habilidad_e.setImageResource(resId)
            nombre_habilidad_e.text = agente.nombre_e
            resId = this@AgenteItem.resIdByName(agente.icon_c, "drawable")
            icon_habilidad_c.setImageResource(resId)
            nombre_habilidad_c.text = agente.nombre_c
            resId = this@AgenteItem.resIdByName(agente.icon_x, "drawable")
            icon_habilidad_x.setImageResource(resId)
            nombre_habilidad_x.text = agente.nombre_x
        })
    }
}