package com.example.milistado.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.milistado.Alumno
import com.example.milistado.databinding.ItemAlumnoBinding

class AlumnoViewHolder (view: View):ViewHolder(view) {

    val binding = ItemAlumnoBinding.bind(view)


    fun render(alumnoModel: Alumno){
        binding.tvAlumnoName.text = alumnoModel.nombre
        binding.tvAlumnoCuenta.text = alumnoModel.cuenta
        binding.tvAlumnoCorreo.text = alumnoModel.correo
        Glide.with(binding.ivAlumno.context).load(alumnoModel.image).into(binding.ivAlumno)
        binding.ivAlumno.setOnClickListener{
            Toast.makeText(
                binding.ivAlumno.context,
                alumnoModel.nombre,
                Toast.LENGTH_SHORT
            ).show()}
    }
}