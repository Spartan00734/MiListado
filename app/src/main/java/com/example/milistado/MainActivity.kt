package com.example.milistado

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.milistado.adapter.AlumnoAdapter
import com.example.milistado.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AlumnoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        binding.fabAddAlumno.setOnClickListener {
            showAddAlumnoDialog()
        }
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)

        adapter = AlumnoAdapter(AlumnoProvider.alumnosList)
        binding.recyclerAlumnos.layoutManager = manager
        binding.recyclerAlumnos.adapter = adapter
        binding.recyclerAlumnos.addItemDecoration(decoration)
    }

    private fun showAddAlumnoDialog() {
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.dialog_add_alumno, null)
        val nombreInput = dialogView.findViewById<EditText>(R.id.inputNombre)
        val cuentaInput = dialogView.findViewById<EditText>(R.id.inputCuenta)
        val correoInput = dialogView.findViewById<EditText>(R.id.inputCorreo)
        val imageUrlInput = dialogView.findViewById<EditText>(R.id.inputImageUrl)

        MaterialAlertDialogBuilder(this)
            .setTitle("Agregar Alumno")
            .setView(dialogView)
            .setPositiveButton("Agregar") { dialog, _ ->
                val nombre = nombreInput.text.toString()
                val cuenta = cuentaInput.text.toString()
                val correo = correoInput.text.toString()
                val imageUrl = imageUrlInput.text.toString()

                if (nombre.isNotBlank() && cuenta.isNotBlank() && correo.isNotBlank() && imageUrl.isNotBlank()) {
                    val nuevoAlumno = Alumno(
                        nombre,
                        cuenta,
                        correo,
                        imageUrl
                    )
                    AlumnoProvider.alumnosList.add(nuevoAlumno)
                    adapter.notifyItemInserted(AlumnoProvider.alumnosList.size - 1)
                } else {
                    Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}




