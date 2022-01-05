package com.example.miagendamvvm.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.miagendamvvm.MainActivity
import com.example.miagendamvvm.R
import com.example.miagendamvvm.config.Constantes
import com.example.miagendamvvm.databinding.ActivityFormularioBinding
import com.example.miagendamvvm.dialogos.BorrarDialogo
import com.example.miagendamvvm.viewmodel.FormularioViewModel

class FormularioActivity : AppCompatActivity(),BorrarDialogo.BorrarListener{

    lateinit var binding:ActivityFormularioBinding
    lateinit var  viewModel:FormularioViewModel
    lateinit var dialogoBorrar: BorrarDialogo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialogoBorrar = BorrarDialogo(this)

        viewModel = ViewModelProvider(this).get()
        viewModel.operacion = intent.getStringExtra(Constantes.OPERACION_KEY)!!
        binding.modelo  = viewModel
        binding.lifecycleOwner = this

        viewModel.operacionExitosa.observe(this, Observer {
            if (it){
                mostrarMensaje("Operacion Exitosa")
                irAlInicio()
            }else{
                mostrarMensaje("Ocurrio un Error")
            }
        })


        if (viewModel.operacion.equals(Constantes.OPERACION_EDITAR)){
            viewModel.id.value = intent.getLongExtra(Constantes.ID_PERSONAL_KEY,0)//siempre viene un valor
            viewModel.cargarDatos()
            binding.linearEditar.visibility = View.VISIBLE
            binding.btnGuardar.visibility = View.GONE
        }else{
            binding.linearEditar.visibility = View.GONE
            binding.btnGuardar.visibility = View.VISIBLE
        }
        binding.btnBorrar.setOnClickListener {
            mostrarDialogo()
        }
    }

    private fun mostrarMensaje(s: String) {
        Toast.makeText(applicationContext,s,Toast.LENGTH_LONG).show()
    }
    private fun irAlInicio(){
    val intent = Intent(applicationContext,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // para no permitir volver a traas al ser un formulario
        startActivity(intent)
    }

    override fun borrarPersonal() {
        viewModel.eliminarPersonal()
    }
    private fun mostrarDialogo(){
        dialogoBorrar.show(supportFragmentManager,"Dialogo Borrar")
    }

}