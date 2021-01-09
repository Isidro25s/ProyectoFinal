package app.valorant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import app.valorant.menu.agentes.Agentes
import app.valorant.menu.armas.Armas
import app.valorant.menu.mapas.Mapas

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cardviewAgentes: ConstraintLayout = findViewById(R.id.agentes)
        cardviewAgentes.setOnClickListener {
            startActivity(Intent(this, Agentes::class.java))
        }

        val cardviewMapas: ConstraintLayout = findViewById(R.id.mapas)
        cardviewMapas.setOnClickListener {
            startActivity(Intent(this, Mapas::class.java))
        }

        val cardviewArmas: ConstraintLayout = findViewById(R.id.armas)
        cardviewArmas.setOnClickListener {
            startActivity(Intent(this, Armas::class.java))
        }
    }
}