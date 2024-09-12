package com.example.milistado.adapter

import AlumnoViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.milistado.Alumno
import com.example.milistado.R

class AlumnoAdapter(
    private val alumnoList: List<Alumno>,
    private val onDelete: (Alumno) -> Unit,
    private val onEdit: (Alumno) -> Unit,
    private val onViewDetails: (Alumno) -> Unit
) : RecyclerView.Adapter<AlumnoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumnoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AlumnoViewHolder(layoutInflater.inflate(R.layout.item_alumno, parent, false))
    }

    override fun getItemCount(): Int = alumnoList.size

    override fun onBindViewHolder(holder: AlumnoViewHolder, position: Int) {
        val item = alumnoList[position]
        holder.render(item, onDelete, onEdit, onViewDetails)
    }
    
}