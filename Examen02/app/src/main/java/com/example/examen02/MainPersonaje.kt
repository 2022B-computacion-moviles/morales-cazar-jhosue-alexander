package com.example.examen02

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainPersonaje : AppCompatActivity() {

    var idItemSeleccionado = 0
    var anime_papa = Anime("","","","","")
    val db = Firebase.firestore
    val animes = db.collection("PersonajeAnimes")
    var adaptador: ArrayAdapter<Personaje>? = null

    var resultAddNewPersonaje = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                anime_papa = intent.getParcelableExtra<Anime>("PersonajeAnime")!!
            }
        }
    }

    var resultEditPersonaje = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                anime_papa = intent.getParcelableExtra<Anime>("PersonajeAnime")!!
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_personaje)
    }

    override fun onStart() {
        super.onStart()

        anime_papa = intent.getParcelableExtra<Anime>("PersonajeAnime")!!

        updatePersonajeList()

        val tv_anime_papa = findViewById<TextView>(R.id.tv_anime_papa)
        tv_anime_papa.setText("Anime: ${anime_papa.nombreAnime}")

        val btn_add_personaje = findViewById<Button>(R.id.btn_AnadirPersonaje)
        btn_add_personaje.setOnClickListener {
            val openAddPersonaje = Intent(this, AnadirPersonaje::class.java)
            openAddPersonaje.putExtra("PersonajeAnime",anime_papa)
            resultAddNewPersonaje.launch(openAddPersonaje)
        }

        val btn_back_to_animes = findViewById<Button>(R.id.btn_regresarAnimes)
        btn_back_to_animes.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.personaje_menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        Log.i("id animeXpersonaje", "ID ${idItemSeleccionado}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var personajeSelected:Personaje = adaptador!!.getItem(idItemSeleccionado)!!
        return when (item.itemId) {
            R.id.mi_editarPersonaje -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                val openEditPersonaje = Intent(this, EditarPersonaje::class.java)
                openEditPersonaje.putExtra("PersonajeAnime",anime_papa)
                openEditPersonaje.putExtra("Personaje",personajeSelected)
                resultEditPersonaje.launch(openEditPersonaje)
                return true
            }
            R.id.mi_eliminarPersonaje -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                val animeSubcollection = animes.document("${anime_papa.idAnime}")
                    .collection("Personajes")
                    .document("${personajeSelected.idPersonaje}")
                    .delete()
                    .addOnSuccessListener {
                        Log.i("DELETE-PERSONAJE","Success")
                    }
                    .addOnFailureListener{
                        Log.i("DELETE-PERSONAJE","Failure")
                    }
                updatePersonajeList()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun updatePersonajeList(){

        val animeSubcollection = animes.document("${anime_papa.idAnime}")
            .collection("Personajes")
            .whereEqualTo("personaje_idanime","${anime_papa.idAnime}")



        val lv_personaje_list = findViewById<ListView>(R.id.lv_Personaje)

        animeSubcollection.get().addOnSuccessListener { result ->

            var personajesList = arrayListOf<Personaje>()

            for (document in result){
                personajesList.add(
                    Personaje(
                        document.id.toString(),
                        document.data.get("personaje_idanime").toString(),
                        document.data.get("personaje_nombre").toString(),
                        document.data.get("personaje_edad").toString(),
                        document.data.get("personaje_genero").toString(),
                    )
                )
            }

            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                personajesList
            )

            lv_personaje_list.adapter = adaptador
            adaptador!!.notifyDataSetChanged()

            registerForContextMenu(lv_personaje_list)

        }

    }
}