package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AnadirAnime : AppCompatActivity() {

    val db= Firebase.firestore
    val animes = db.collection("PersonajeAnimes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anadir_anime)
    }

    override fun onStart() {
        super.onStart()
        val in_anime_nombre = findViewById<EditText>(R.id.input_nombreAnime)
        val in_anime_anio = findViewById<EditText>(R.id.input_anioAnime)
        val in_anime_genero = findViewById<EditText>(R.id.input_generoAnime)
        val in_anime_estudio = findViewById<EditText>(R.id.input_estudioAnime)


        val btn_confirm_add_anime= findViewById<Button>(R.id.btn_CrearAnime)
        btn_confirm_add_anime.setOnClickListener {
            var anime = hashMapOf(
                "anime_nombre" to in_anime_nombre.text.toString(),
                "anime_anio" to in_anime_anio.text.toString(),
                "anime_genero" to in_anime_genero.text.toString(),
                "anime_estudio" to in_anime_estudio.text.toString()
            )
            animes.add(anime).addOnSuccessListener {
                in_anime_nombre.text.clear()
                in_anime_anio.text.clear()
                in_anime_genero.text.clear()
                in_anime_estudio.text.clear()
                Toast.makeText(this,"Anime registrado exitosamente", Toast.LENGTH_SHORT).show();
                Log.i("Add-Anime","Success")
            }.addOnFailureListener{
                Log.i("Add-Anime","Failed")
            }

        }

    }
}