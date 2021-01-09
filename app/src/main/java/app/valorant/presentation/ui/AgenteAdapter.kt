package app.valorant.presentation.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.valorant.items.AgenteItem
import app.valorant.data.entities.Agente
import app.valorant.databinding.RecyclerItemAgentesBinding
import app.valorant.presentation.resIdByName

class AgenteAdapter(val context: Context, private val agentes: List<Agente>) :
    RecyclerView.Adapter<AgenteAdapter.ViewHolder>() {

    private lateinit var binding: RecyclerItemAgentesBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = RecyclerItemAgentesBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = agentes[position]
        holder.bind(info)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, AgenteItem::class.java)
            intent.putExtra("id", agentes[position].id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = agentes.size

    inner class ViewHolder(val binding: RecyclerItemAgentesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(agente: Agente) {
            binding.apply {
                textView1.text = agente.name
                val resId: Int = context.resIdByName(agente.imagen, "drawable")
                imageView.setImageResource(resId)
            }
        }
    }
}