package app.valorant.presentation.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.valorant.items.ArmaItem
import app.valorant.data.entities.Arma
import app.valorant.databinding.RecyclerItemArmasBinding
import app.valorant.presentation.resIdByName


class ArmaAdapter(val context: Context, private val armas: List<Arma>) :
    RecyclerView.Adapter<ArmaAdapter.ViewHolder>() {

    private lateinit var binding: RecyclerItemArmasBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        binding = RecyclerItemArmasBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val info = armas[position]
        holder.bind(info)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ArmaItem::class.java)
            intent.putExtra("id", armas[position].id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = armas.size

    inner class ViewHolder(val binding: RecyclerItemArmasBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(arma: Arma) {
            binding.apply {
                textView1.text = arma.name
                val resId: Int = context.resIdByName(arma.imagen, "drawable")
                imageView.setImageResource(resId)

            }

        }
    }
}