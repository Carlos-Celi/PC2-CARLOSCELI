package com.example.pc2_carlosceli.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pc2_carlosceli.R
import com.example.pc2_carlosceli.model.EquiposModel
import com.squareup.picasso.Picasso

class EquiposAdapter(private var lstEquipos: List<EquiposModel>): RecyclerView.Adapter<EquiposAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvAño: TextView = itemView.findViewById(R.id.tvAño)
        val tvTitulos: TextView = itemView.findViewById(R.id.tvTitulo)
        val ivUrl: ImageView = itemView.findViewById(R.id.ivUrl)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_equipo, parent, false))
    }

    override fun getItemCount(): Int {
        return lstEquipos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemEquipos = lstEquipos[position]
        holder.tvNombre.text = itemEquipos.nombre
        holder.tvAño.text = itemEquipos.año
        holder.tvTitulos.text = itemEquipos.ntitulos
        Picasso.get().load(itemEquipos.imageUrl).into(holder.ivUrl)


    }
}
