package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarAnime : AppCompatActivity() {

    var anime_update = Anime("","","","","")
    val db = Firebase.firestore
    val animes = db.collection("PersonajeAnimes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_anime)
    }

    override fun onStart() {
        super.onStart()

        anime_update = intent.getParcelableExtra<Anime>("PersonajeAnime")!!

        val in_anime_nombre = findViewById<EditText>(R.id.input_nombreAnime)
        in_anime_nombre.setText(anime_update.nombreAnime.toString())

        val in_anime_anio = findViewById<EditText>(R.id.input_anioAnime)
        in_anime_anio.setText(anime_update.anoDeLanzamientoAnime.toString())

        val in_anime_genero = findViewById<EditText>(R.id.input_generoAnime)
        in_anime_genero.setText(anime_update.generoAnime.toString())

        val in_anime_estudio = findViewById<EditText>(R.id.input_estudioAnime)
        in_anime_estudio.setText(anime_update.estudioAnime.toString())

        val btn_confirm_update = findViewById<Button>(R.id.btn_ActualizarAnime)
        btn_confirm_update.setOnClickListener {
            animes.document("${anime_update.idAnime}").update(
                "anime_nombre", in_anime_nombre.text.toString(),
                "anime_anio" , in_anime_anio.text.toString(),
                "anime_genero", in_anime_genero.text.toString(),
                "anime_estudio", in_anime_estudio.text.toString()
            )
            Toast.makeText(this,"Info. actualizada exitosamente", Toast.LENGTH_SHORT).show()
        }

    }
}