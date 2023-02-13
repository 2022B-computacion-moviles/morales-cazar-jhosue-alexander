package com.example.deber_01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class AnadirPersonaje : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anadir_personaje)

        val bundle = intent.extras
        val id_Anime = bundle?.getString("id_Anime")

        val nombre_Personaje =findViewById<EditText>(R.id.input_nombrePersonaje)
        val edad_Personaje = findViewById<EditText>(R.id.input_edadPersonaje)
        val genero_Personaje = findViewById<EditText>(R.id.input_generoPersonaje)

        val anadirPersonaje = findViewById<Button>(R.id.btn_CrearPersonaje)
        anadirPersonaje
            .setOnClickListener {
                EBaseDeDatos.tablaAnime!!.crearPersonaje(
                    id_Anime.toString().toInt(),
                    nombre_Personaje.text.toString(),
                    edad_Personaje.text.toString().toInt(),
                    genero_Personaje.text.toString()
                )
                abrirDialogo("Se ha ingresado correctamente")
                limpiarEntradaTextoP()
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



    private fun limpiarEntradaTextoP() {


        val nombre_personaje = findViewById<EditText>(R.id.input_nombrePersonaje)
        val edad_personaje = findViewById<EditText>(R.id.input_edadPersonaje)
        val genero_personaje = findViewById<EditText>(R.id.input_generoPersonaje)


        nombre_personaje.setText("")
        edad_personaje.setText("")
        genero_personaje.setText("")


    }
}