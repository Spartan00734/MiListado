
package com.example.milistado

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.milistado.databinding.ActivityAlumnoDetailsBinding

class AlumnoDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlumnoDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlumnoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recuperar los datos del alumno del intent
        val nombre = intent.getStringExtra("nombre")
        val cuenta = intent.getStringExtra("cuenta")
        val correo = intent.getStringExtra("correo")
        val imageUrl = intent.getStringExtra("imageUrl")

        // Mostrar los datos en la vista
        binding.tvAlumnoDetailName.text = nombre
        binding.tvAlumnoDetailCuenta.text = cuenta
        binding.tvAlumnoDetailCorreo.text = correo
        Glide.with(this).load(imageUrl).into(binding.ivAlumnoDetailImage)

        // Configurar el botón de regreso
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
