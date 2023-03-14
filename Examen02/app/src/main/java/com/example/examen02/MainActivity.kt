package com.example.examen02

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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val db = Firebase.firestore
    val animes = db.collection("PersonajeAnimes")
    var idItemSeleccionado = -1
    var adaptador: ArrayAdapter<Anime>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        updateAnimeList()

        val btn_add_anime = findViewById<Button>(R.id.btn_AnadirAnime)
        btn_add_anime.setOnClickListener {
            val intent = Intent(this, AnadirAnime::class.java)
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
        inflater.inflate(R.menu.anime_menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        Log.i("context-menu", "ID ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var animeSelected:Anime = adaptador!!.getItem(idItemSeleccionado)!!
        return when (item.itemId) {
            R.id.mi_editar -> {
                Log.i("context-menu", "Edit position: ${animeSelected.idAnime}")
                val openEditAnime = Intent(this, EditarAnime::class.java)
                openEditAnime.putExtra("PersonajeAnime",animeSelected)
                startActivity(openEditAnime)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                animes.document("${animeSelected.idAnime}").delete()
                    .addOnSuccessListener {
                        Log.i("DELETE-ANIME","Success")
                    }
                    .addOnFailureListener{
                        Log.i("DELETE-ANIME","Failure")
                    }
                updateAnimeList()
                return true
            }
            R.id.mi_personajes -> {
                Log.i("context-menu", "Personajes: ${idItemSeleccionado}")
                val openPersonajeList = Intent(this, MainPersonaje::class.java)
                openPersonajeList.putExtra("PersonajeAnime",animeSelected)
                startActivity(openPersonajeList)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun updateAnimeList(){
        val lb_animes = findViewById<ListView>(R.id.lv_Anime)

        animes.get().addOnSuccessListener{ result ->

            var animesList = arrayListOf<Anime>()

            for (document in result) {
                animesList.add(
                    Anime(
                        document.id.toString(),
                        document.get("anime_nombre").toString(),
                        document.get("anime_anio").toString(),
                        document.get("anime_genero").toString(),
                        document.get("anime_estudio").toString()
                    )
                )
            }

            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                animesList
            )

            lb_animes.adapter = adaptador
            adaptador!!.notifyDataSetChanged()

            registerForContextMenu(lb_animes)

        }.addOnFailureListener{
            Log.i("ERROR", "Faiuler retreving animes")
        }
    }
}