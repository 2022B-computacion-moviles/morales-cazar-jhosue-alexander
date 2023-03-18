package com.example.proyectofinal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class InicioUsuario : AppCompatActivity() {

    var idItemSeleccionado = 0
    var usuario = Usuario("","","","")
    val db = Firebase.firestore
    val ejercicioRegistrados = db.collection("EjerciciosRegistrados")
    var adaptador: ArrayAdapter<InicioUsuario>? = null


    var resultAddEjercicio = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                usuario = intent.getParcelableExtra<Usuario>("Usuario")!!
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_usuario)
        Log.i("DASJDHASKDAS","${usuario.nombre}")


    }

    override fun onStart() {
        super.onStart()

        usuario = intent.getParcelableExtra<Usuario>("Usuario")!!

        val tv_usuario = findViewById<TextView>(R.id.tv_usuario)
        tv_usuario.setText("Bienvenido ${usuario.nombre}")

        updateEjerciciosList()

        val btn_agregarEjercicio = findViewById<Button>(R.id.btn_ingresarEjercicio)
        btn_agregarEjercicio.setOnClickListener {
            val openAgregarEjercicio = Intent(this, AgregarEjercicio::class.java)
            openAgregarEjercicio.putExtra("Usuario", usuario)
            startActivity(openAgregarEjercicio)
        }
    }

    fun updateEjerciciosList(){

        var layoutEjercicios = findViewById<ViewGroup>(R.id.layout_ejercicios)
        val inflater = LayoutInflater.from(this)
            .inflate(R.layout.ejercicios_view, layoutEjercicios, false)
        layoutEjercicios.addView(inflater)

        val recyclerViewEjercicios = inflater.findViewById<RecyclerView>(R.id.rcv_ejercicios)

        val listaEjercicios = arrayListOf<Ejercicio>()

        ejercicioRegistrados.whereEqualTo("idusuario_ejercicio","${usuario.idUsuario}")
            .get()
            .addOnSuccessListener { documents ->
            for (document in documents) {
                listaEjercicios.add(
                    Ejercicio(
                        document.id.toString(),
                        "${document.get("nombre_ejercicio")}",
                        "${document.get("enlace_ejercicio")}",
                        "${document.get("series_ejercicio")}",
                        "${document.get("repeticiones_ejercicio")}",
                        "${document.get("peso_ejercicio")} KG"))
            }
                iniciarRecyclerView(
                    listaEjercicios,
                    this,
                    recyclerViewEjercicios
                )
        }.addOnFailureListener { exception ->
            Log.w("ñkasdjklasjda", "Error getting documents: ", exception)
        }
    }

    fun iniciarRecyclerView(
        lista: List<Ejercicio>,
        actividad: InicioUsuario,
        recyclerView: RecyclerView
    ){
        val adaptador = EjercicioListAdapter(
            actividad,
            lista,
            recyclerView
        )
        recyclerView.adapter= adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()
    }


    fun abrirNuevaActividad(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}