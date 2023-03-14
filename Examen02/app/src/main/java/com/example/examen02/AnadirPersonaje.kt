package com.example.examen02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AnadirPersonaje : AppCompatActivity() {

    var anime_papa = Anime("","","","","")
    val db = Firebase.firestore
    val animes = db.collection("PersonajeAnimes")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anadir_personaje)
    }

    override fun onStart() {
        super.onStart()

        anime_papa = intent.getParcelableExtra<Anime>("PersonajeAnime")!!
        val animeSubcollection = animes.document("${anime_papa.idAnime}").collection("Personajes")

        val in_personaje_nombre = findViewById<EditText>(R.id.input_nombrePersonaje)
        val in_personaje_edad = findViewById<EditText>(R.id.input_edadPersonaje)
        val in_personaje_genero = findViewById<EditText>(R.id.input_generoPersonaje)

        val btn_confirm_add_personaje = findViewById<Button>(R.id.btn_CrearPersonaje)
        val btn_cancel_add_personaje = findViewById<Button>(R.id.btn_CancelPersonaje)
        btn_cancel_add_personaje.setOnClickListener {
            devolverRespuesta()
        }



            btn_confirm_add_personaje.setOnClickListener {

                var personaje = hashMapOf(
                    "personaje_idanime" to anime_papa.idAnime.toString(),
                    "personaje_nombre" to in_personaje_nombre.text.toString(),
                    "personaje_edad" to in_personaje_edad.text.toString(),
                    "personaje_genero" to in_personaje_genero.text.toString()
                )
                animeSubcollection.add(personaje).addOnSuccessListener {
                    in_personaje_nombre.text.clear()
                    in_personaje_edad.text.clear()
                    in_personaje_genero.text.clear()
                    Toast.makeText(this,"Personaje registrado exitosamente", Toast.LENGTH_SHORT).show();
                    Log.i("Add-Personaje","Success")
                }.addOnFailureListener{
                    Log.i("Add-Personaje","Failed")
                }
            }



    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("PersonajeAnime",anime_papa)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }
}