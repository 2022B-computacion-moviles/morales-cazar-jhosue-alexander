package com.example.deber_01
//LISTOOOO
import BAnime
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    var idItemSeleccionado = 0
    var arreglo = ArrayList<BAnime>()

    var id_Anime=""
    var nombre_Anime=""
    var anio_Anime=""
    var genero_Anime=""
    var estudio_Anime=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EBaseDeDatos.tablaAnime = ESqliteHelper(this)



        val btnAnadirAnime = findViewById<Button>(R.id.btn_AnadirAnime)
        btnAnadirAnime.setOnClickListener {
            irActividad(AnadirAnime::class.java)
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        //Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }

    var animeSeleccionado=BAnime(0,"","","","")

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mi_personajes -> {
                val listView_anime=findViewById<ListView>(R.id.lv_Anime)
                var itemselect= listView_anime.getItemAtPosition(idItemSeleccionado)
                var objetoSeleccionado= itemselect as BAnime
                animeSeleccionado=objetoSeleccionado
                id_Anime=animeSeleccionado.idAnime.toString()
                nombre_Anime= animeSeleccionado.nombreAnime.toString()
                parametroID(MainPersonaje::class.java)
                return true
            }
            R.id.mi_editar -> {
                val listView_anime=findViewById<ListView>(R.id.lv_Anime)
                var itemselect = listView_anime.getItemAtPosition(idItemSeleccionado)
                var objetoSeleccionado = itemselect as BAnime

                animeSeleccionado = objetoSeleccionado

                id_Anime = animeSeleccionado.idAnime.toString()
                nombre_Anime = animeSeleccionado.nombreAnime.toString()
                anio_Anime = animeSeleccionado.anoDeLanzamientoAnime.toString()
                genero_Anime = animeSeleccionado.generoAnime.toString()
                estudio_Anime = animeSeleccionado.estudioAnime.toString()

                parametrosAnimeSeleccionado(EditarAnime::class.java)
                return true
            }
            R.id.mi_eliminar -> {
                val listView_anime = findViewById<ListView>(R.id.lv_Anime)
                var itemselect =listView_anime.getItemAtPosition(idItemSeleccionado)
                var objetoSeleccionado = itemselect as BAnime

                animeSeleccionado = objetoSeleccionado
                EBaseDeDatos.tablaAnime!!.eliminarAnimeFormulario(objetoSeleccionado.idAnime)
                onResume()

                return true
            }

            else -> super.onContextItemSelected(item)
        }
    }



    override fun onResume() {
        super.onResume()

        arreglo = EBaseDeDatos.tablaAnime!!.getAllAnime()

        val listView=findViewById<ListView>(R.id.lv_Anime)

        val adaptador=AdaptadorAnime(this,arreglo)
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)


    }

    fun parametroID(
        clase: Class<*>
    ) {
        val intenPersonajesdeAnime=Intent(this,clase)
        intenPersonajesdeAnime.putExtra("id_Anime",id_Anime)
        intenPersonajesdeAnime.putExtra("nombre_Anime", nombre_Anime)
        startActivity(intenPersonajesdeAnime)
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)

    }

    fun parametrosAnimeSeleccionado(clase: Class<*>){
        val intentAnimeSeleccionado = Intent(this, clase)
        intentAnimeSeleccionado.putExtra("id_Anime", id_Anime)
        intentAnimeSeleccionado.putExtra("nombre_Anime",nombre_Anime)
        intentAnimeSeleccionado.putExtra("anio_Anime",anio_Anime)
        intentAnimeSeleccionado.putExtra("genero_Anime", genero_Anime)
        intentAnimeSeleccionado.putExtra("estudio_Anime", estudio_Anime)
        startActivity(intentAnimeSeleccionado)
    }

    fun Context.showToast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()



}

