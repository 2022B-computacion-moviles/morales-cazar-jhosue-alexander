package com.example.deber_01

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*

class MainPersonaje : AppCompatActivity() {

    var id_Anime = ""
    var nombre_Anime = ""
    var idItemSeleccionado = 0
    var arreglo = ArrayList<BPersonaje>()

    var id_Personaje = ""
    var nombre_Personaje = ""
    var edad_Personaje = ""
    var genero_Personaje = ""
    var id_Anime_Personaje = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_personaje)

        val bundle=intent.extras
        id_Anime= bundle?.getString("id_Anime").toString()
        nombre_Anime=bundle?.getString("nombre_Anime").toString()


        val botoncrearPersonaje= findViewById<Button>(R.id.btn_AnadirPersonaje)
        botoncrearPersonaje.setOnClickListener {
            parametroIDP(AnadirPersonaje::class.java)

        }
    }

    override fun onResume() {
        super.onResume()
        val txttitulo = findViewById<TextView>(R.id.tv_PersonajesdeAnime)
        txttitulo.setText("Personajes de ${nombre_Anime}")

        arreglo = EBaseDeDatos.tablaAnime!!.getPersonajeIdAnime(id_Anime.toInt())

        val listView=findViewById<ListView>(R.id.lv_Personaje)

        val adaptador=AdaptadorPersonaje(this,arreglo)
        listView.adapter=adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)


        val ir_animes=findViewById<Button>(R.id.btn_regresarPaises)
        ir_animes.setOnClickListener {

            irActividad(MainActivity::class.java)

        }
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ){
        super.onCreateContextMenu(menu, v, menuInfo)
        //LLenamos las opciones del menu
        val inflater=menuInflater
        inflater.inflate(R.menu.menu2, menu)
        //Obtener el id del ArrayListSeleccionada
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado=id

    }

    var personajeSeleccionado = BPersonaje(0, 0, "", 0,"")

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){

            R.id.mi_eliminarPersonaje->{

                val listView_personaje=findViewById<ListView>(R.id.lv_Personaje)
                var itemselect= listView_personaje.getItemAtPosition(idItemSeleccionado)
                var objetoSeleccionado= itemselect as BPersonaje
                personajeSeleccionado=objetoSeleccionado
                EBaseDeDatos.tablaAnime!!.eliminarPersonaje(objetoSeleccionado.idPersonaje)
                onResume()


                return true
            }
            R.id.mi_editarPersonaje->{

                val listView_personaje=findViewById<ListView>(R.id.lv_Personaje)
                var itemselect= listView_personaje.getItemAtPosition(idItemSeleccionado)
                var objetoSeleccionado= itemselect as BPersonaje

                personajeSeleccionado=objetoSeleccionado

                id_Personaje=personajeSeleccionado.idPersonaje.toString()
                id_Anime_Personaje = personajeSeleccionado.idAnime.toString()
                nombre_Personaje= personajeSeleccionado.nombrePersonaje
                edad_Personaje= personajeSeleccionado.edadPersonaje.toString()
                genero_Personaje = personajeSeleccionado.generoPersonaje
                parametrosPersonajeSeleccionado(EditarPersonaje::class.java)


                return true
            }

            else->super.onContextItemSelected(item)

        }
    }


    fun parametroIDP(
        clase: Class<*>
    ) {
        val intenPersonajesDeAnime=Intent(this,clase)
        intenPersonajesDeAnime.putExtra("id_Anime",id_Anime)
        intenPersonajesDeAnime.putExtra("nombre_Anime",nombre_Anime)
        startActivity(intenPersonajesDeAnime)
    }



    fun parametrosPersonajeSeleccionado(
        clase: Class<*>
    ){
        val intentPaisSeleccionado=Intent(this, clase)
        intentPaisSeleccionado.putExtra("id_Personaje", id_Personaje )
        intentPaisSeleccionado.putExtra("nombre_Personaje", nombre_Personaje )
        intentPaisSeleccionado.putExtra("edad_Personaje", edad_Personaje)
        intentPaisSeleccionado.putExtra("genero_Personaje", genero_Personaje )
        intentPaisSeleccionado.putExtra("id_Anime_Personaje", id_Anime_Personaje)

        startActivity(intentPaisSeleccionado)
    }

    fun irActividad(
        clase: Class<*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)

    }
}