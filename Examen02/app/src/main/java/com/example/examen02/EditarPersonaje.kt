package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarPersonaje : AppCompatActivity() {

    var anime_papa = Anime("","","","","")
    var current_personaje = Personaje("","","","","")
    val db = Firebase.firestore
    val animes = db.collection("PersonajeAnimes")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_personaje)
    }

    override fun onStart() {
        super.onStart()

        anime_papa = intent.getParcelableExtra<Anime>("PersonajeAnime")!!
        current_personaje = intent.getParcelableExtra<Personaje>("Personaje")!!

        val in_personaje_nombre = findViewById<EditText>(R.id.input_nombrePersonaje)
        in_personaje_nombre.setText("${current_personaje.nombrePersonaje}")

        val in_personaje_edad = findViewById<EditText>(R.id.input_edadPersonaje)
        in_personaje_edad.setText("${current_personaje.edadPersonaje}")

        val in_personaje_genero = findViewById<EditText>(R.id.input_generoPersonaje)
        in_personaje_genero.setText("${current_personaje.generoPersonaje}")


        val btn_confirm_update_personaje = findViewById<Button>(R.id.btn_ActualizarPersonaje)
        btn_confirm_update_personaje.setOnClickListener {
            animes.document("${anime_papa.idAnime}")
                .collection("Personajes")
                .document("${current_personaje.idPersonaje}")
                .update(
                    "personaje_nombre" ,in_personaje_nombre.text.toString(),
                "personaje_edad" ,in_personaje_edad.text.toString(),
                    "personaje_genero" ,in_personaje_genero.text.toString()
                )
            Toast.makeText(this,"Personaje actualizado exitosamente", Toast.LENGTH_SHORT).show()
        }

    }


}