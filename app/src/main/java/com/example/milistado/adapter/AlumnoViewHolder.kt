import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.milistado.Alumno
import com.example.milistado.R
import com.example.milistado.databinding.ItemAlumnoBinding

class AlumnoViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemAlumnoBinding.bind(view)

    fun render(alumnoModel: Alumno, onDelete: (Alumno) -> Unit, onEdit: (Alumno) -> Unit, onViewDetails: (Alumno) -> Unit) {
        binding.tvAlumnoName.text = alumnoModel.nombre
        binding.tvAlumnoCuenta.text = alumnoModel.cuenta
        binding.tvAlumnoCorreo.text = alumnoModel.correo

        // Cargar la imagen
        Glide.with(binding.ivAlumno.context).load(alumnoModel.image).into(binding.ivAlumno)

        // Configurar el menú desplegable (PopupMenu)
        binding.menuOptions.setOnClickListener {
            val popupMenu = PopupMenu(it.context, it)
            val inflater: MenuInflater = popupMenu.menuInflater
            inflater.inflate(R.menu.menu_item, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.action_view_details -> {
                        onViewDetails(alumnoModel)
                        true
                    }
                    R.id.action_edit -> {
                        onEdit(alumnoModel)
                        true
                    }
                    R.id.action_delete -> {
                        onDelete(alumnoModel)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }
}
