package com.example.milistado

import android.content.Intent
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
            showAddAlumnoDialog() // Mostrar diálogo para agregar un alumno
        }
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, manager.orientation)

        adapter = AlumnoAdapter(
            AlumnoProvider.alumnosList,
            onDelete = { alumno -> deleteAlumno(alumno) },
            onEdit = { alumno -> showEditAlumnoDialog(alumno) },
            onViewDetails = { alumno -> viewAlumnoDetails(alumno) }
        )
        binding.recyclerAlumnos.layoutManager = manager
        binding.recyclerAlumnos.adapter = adapter
        binding.recyclerAlumnos.addItemDecoration(decoration)
    }

    private fun deleteAlumno(alumno: Alumno) {
        val position = AlumnoProvider.alumnosList.indexOf(alumno)
        if (position != -1) {
            AlumnoProvider.alumnosList.removeAt(position)
            adapter.notifyItemRemoved(position)
            Toast.makeText(this, "Alumno eliminado", Toast.LENGTH_SHORT).show()
        }
    }

    // Nueva función para ver detalles del alumno
    private fun viewAlumnoDetails(alumno: Alumno) {
        val intent = Intent(this, AlumnoDetailsActivity::class.java).apply {
            putExtra("nombre", alumno.nombre)
            putExtra("cuenta", alumno.cuenta)
            putExtra("correo", alumno.correo)
            putExtra("imageUrl", alumno.image)
        }
        startActivity(intent)
    }

    private fun showAddAlumnoDialog() {
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.dialog_add_alumno, null)
        val nombreInput = dialogView.findViewById<EditText>(R.id.inputNombre)
        val cuentaInput = dialogView.findViewById<EditText>(R.id.inputCuenta)
        val correoInput = dialogView.findViewById<EditText>(R.id.inputCorreo)
        val imagenInput = dialogView.findViewById<EditText>(R.id.inputImageUrl)

        MaterialAlertDialogBuilder(this)
            .setTitle("Agregar Alumno")
            .setView(dialogView)
            .setPositiveButton("Agregar") { dialog, _ ->
                val nombre = nombreInput.text.toString()
                val cuenta = cuentaInput.text.toString()
                val correo = correoInput.text.toString()
                val imagen = imagenInput.text.toString()

                if (nombre.isNotBlank() && cuenta.isNotBlank() && correo.isNotBlank() && imagen.isNotBlank()) {
                    val nuevoAlumno = Alumno(
                        nombre,
                        cuenta,
                        correo,
                        imagen
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

    private fun showEditAlumnoDialog(alumno: Alumno) {
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.dialog_add_alumno, null)
        val nombreInput = dialogView.findViewById<EditText>(R.id.inputNombre)
        val cuentaInput = dialogView.findViewById<EditText>(R.id.inputCuenta)
        val correoInput = dialogView.findViewById<EditText>(R.id.inputCorreo)
        val imagenInput = dialogView.findViewById<EditText>(R.id.inputImageUrl)

        // Prellenar con los datos del alumno a editar
        nombreInput.setText(alumno.nombre)
        cuentaInput.setText(alumno.cuenta)
        correoInput.setText(alumno.correo)
        imagenInput.setText(alumno.image)

        MaterialAlertDialogBuilder(this)
            .setTitle("Modificar Alumno")
            .setView(dialogView)
            .setPositiveButton("Modificar") { dialog, _ ->
                val nuevoNombre = nombreInput.text.toString()
                val nuevaCuenta = cuentaInput.text.toString()
                val nuevoCorreo = correoInput.text.toString()
                val nuevaImagen = imagenInput.text.toString()

                if (nuevoNombre.isNotBlank() && nuevaCuenta.isNotBlank() && nuevoCorreo.isNotBlank() && nuevaImagen.isNotBlank()) {
                    val position = AlumnoProvider.alumnosList.indexOf(alumno)
                    if (position != -1) {
                        val alumnoEditado = Alumno(nuevoNombre, nuevaCuenta, nuevoCorreo, nuevaImagen)
                        AlumnoProvider.alumnosList[position] = alumnoEditado
                        adapter.notifyItemChanged(position)
                        Toast.makeText(this, "Alumno modificado", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }
            .show()
    }


}





