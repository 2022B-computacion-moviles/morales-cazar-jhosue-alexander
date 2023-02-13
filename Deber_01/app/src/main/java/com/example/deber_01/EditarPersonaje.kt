package com.example.deber_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class EditarPersonaje : AppCompatActivity() {

    var id_Anime=""
    var nombre_Anime=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_personaje)

        val bundle=intent.extras

        val id_Personaje =bundle?.getString("id_Personaje")

        id_Anime = bundle?.getString("id_Anime").toString()
        nombre_Anime = bundle?.getString("nombre_Anime").toString()

        val nombre_Personaje = findViewById<EditText>(R.id.input_nombrePersonaje)
        val edad_Personaje = findViewById<EditText>(R.id.input_edadPersonaje)
        val genero_Personaje = findViewById<EditText>(R.id.input_generoPersonaje)

        nombre_Personaje.setText(bundle?.getString("nombre_Personaje"))
        edad_Personaje.setText(bundle?.getString("edad_Personaje"))
        genero_Personaje.setText(bundle?.getString("genero_Personaje"))

        val botonEditarPersonaje =findViewById<Button>(R.id.btn_ActualizarPersonaje)
        botonEditarPersonaje
            .setOnClickListener {
                var res= EBaseDeDatos.tablaAnime!!.actualizarPersonajeFormulario(
                    nombre_Personaje.text.toString(),
                    edad_Personaje.text.toString().toInt(),
                    genero_Personaje.text.toString(),
                    id_Personaje.toString().toInt()
                )
                abrirDialogo("Se ha actualizado correctamente")
            }


    }

    fun abrirDialogo(Titulo:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(Titulo)
        builder.setPositiveButton(
            "Aceptar",
            null
        )

        val dialog = builder.create()
        dialog.show()
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)

    }

}