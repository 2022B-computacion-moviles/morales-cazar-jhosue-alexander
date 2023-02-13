package com.example.deber_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class EditarAnime : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_anime)

        val bundle =intent.extras

        val nombre_Anime= findViewById<EditText>(R.id.input_nombreAnime)
        val anio_Anime = findViewById<EditText>(R.id.input_anioAnime)
        val genero_Anime = findViewById<EditText>(R.id.input_generoAnime)
        val estudio_Anime = findViewById<EditText>(R.id.input_estudioAnime)

        val id_Anime= bundle?.getString("id_Anime")
        nombre_Anime.setText(bundle?.getString("nombre_Anime"))
        anio_Anime.setText(bundle?.getString("anio_Anime"))
        genero_Anime.setText(bundle?.getString("genero_Anime"))
        estudio_Anime.setText(bundle?.getString("estudio_Anime"))

        val botonEditarAnime = findViewById<Button>(R.id.btn_ActualizarAnime)
        botonEditarAnime.setOnClickListener {
            EBaseDeDatos.tablaAnime!!.actualizarAnimeFormulario(
                nombre_Anime.text.toString(),
                anio_Anime.text.toString(),
                genero_Anime.text.toString(),
                estudio_Anime.text.toString(),
                id_Anime.toString().toInt()
            )
            abrirDialogo("Se actualizado correctamente")
        }
    }

    fun abrirDialogo(Titulo:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(Titulo)
        builder.setPositiveButton(
            "Aceptar"
        ){_,_-> irActividad(MainActivity::class.java)}
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