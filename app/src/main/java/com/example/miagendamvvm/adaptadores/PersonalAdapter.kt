package com.example.miagendamvvm.adaptadores

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.miagendamvvm.R
import com.example.miagendamvvm.config.Constantes
import com.example.miagendamvvm.databinding.ItemListBinding
import com.example.miagendamvvm.models.Personal
import com.example.miagendamvvm.ui.FormularioActivity

class PersonalAdapter(private val dataSet: List<Personal>) :
    RecyclerView.Adapter<PersonalAdapter.ViewHolder>() {



    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = dataSet?.get(position)
        viewHolder.enlazarItem(item!!)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet!!.size

   /**
    * Provide a reference to the type of views that you are using
    * (custom ViewHolder).
    */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
       var binding = ItemListBinding.bind(view)
       var contexto = view.context
       fun enlazarItem(p: Personal) {

           binding.tvNombre.text = "${p.nombre} ${p.apellidos}"
           binding.tvEmail.text = p.email
           binding.tvEdad.text = p.edad.toString()
           binding.tvTelefono.text = p.telefono


           binding.root.setOnClickListener{
               val intent =  Intent(contexto, FormularioActivity::class.java)
               intent.putExtra(Constantes.OPERACION_KEY,Constantes.OPERACION_EDITAR)
               intent.putExtra(Constantes.ID_PERSONAL_KEY,p.idEmpleado)
               contexto.startActivity(intent)
           }
       }

    }
}
