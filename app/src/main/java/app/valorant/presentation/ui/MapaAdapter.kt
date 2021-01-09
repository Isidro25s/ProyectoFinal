package app.valorant.presentation.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.valorant.data.entities.Mapa
import app.valorant.databinding.RecyclerItemMapasBinding
import app.valorant.items.MapaItem
import app.valorant.presentation.resIdByName


class MapaAdapter(val context: Context, private val mapas: List<Mapa>) :
    RecyclerView.Adapter<MapaAdapter.ViewHolder>() {

    private lateinit var binding: RecyclerItemMapasBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = RecyclerItemMapasBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = mapas[position]
        holder.bind(info)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MapaItem::class.java)
            intent.putExtra("id", mapas[position].id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = mapas.size

    inner class ViewHolder(val binding: RecyclerItemMapasBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(map: Mapa) {
            binding.apply {
                textView1.text = map.name
                val resId: Int = context.resIdByName(map.imagen, "drawable")
                imageView.setImageResource(resId)
            }
        }
    }
}