package com.example.deber_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class AnadirAnime : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anadir_anime)


        val nombre = findViewById<EditText>(R.id.input_nombreAnime)
        val anio = findViewById<EditText>(R.id.input_anioAnime)
        val genero = findViewById<EditText>(R.id.input_generoAnime)
        val estudio = findViewById<EditText>(R.id.input_estudioAnime)


        val anadirAnimeBDD = findViewById<Button>(R.id.btn_CrearAnime)
        anadirAnimeBDD
            .setOnClickListener {

                val resultado = EBaseDeDatos.tablaAnime!!.crearAnime(
                    nombre.text.toString(),
                    anio.text.toString(),
                    genero.text.toString(),
                    estudio.text.toString()
                )

                if(resultado){
                    abrirDialogo("Se ha ingresado correctamente")
                    limpiarEntradaTexto()
                }





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

    private fun limpiarEntradaTexto() {

        val nombre = findViewById<EditText>(R.id.input_nombreAnime)
        val anio = findViewById<EditText>(R.id.input_anioAnime)
        val genero = findViewById<EditText>(R.id.input_generoAnime)
        val estudio = findViewById<EditText>(R.id.input_estudioAnime)
        nombre.setText("")
        anio.setText("")
        genero.setText("")
        estudio.setText("")
    }

}