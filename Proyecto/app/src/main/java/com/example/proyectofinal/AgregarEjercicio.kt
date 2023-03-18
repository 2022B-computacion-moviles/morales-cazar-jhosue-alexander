package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.utils.setImage

class AgregarEjercicio : AppCompatActivity() {

    var usuario = Usuario("","","","")
    val db = Firebase.firestore
    val listEjercicios = db.collection("ListaEjercicios")
    val ejerciciosReg = db.collection("EjerciciosRegistrados")
    var idEjercicioSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_ejercicio)
    }

    override fun onStart() {
        super.onStart()
        usuario = intent.getParcelableExtra<Usuario>("Usuario")!!
        val sp_ejercicios = findViewById<Spinner>(R.id.sp_ejercicio)
        val img_ejercicio = findViewById<ImageView>(R.id.img_ejercicio)

        val btn_agregarEjercicio = findViewById<Button>(R.id.btn_guardarEjercicio)

        val in_series = findViewById<EditText>(R.id.input_numeroSeries)
        val in_repeticiones = findViewById<EditText>(R.id.input_numeroRepeticiones)
        val in_peso = findViewById<EditText>(R.id.input_peso)

        listEjercicios.get().addOnSuccessListener { result ->
            val nombre_ejercicioList = arrayListOf<String>()
            val enlace_ejercicioList = arrayListOf<String>()

            for (document in result){
                nombre_ejercicioList.add(document.get("nombre_ejerciciolista").toString())
                enlace_ejercicioList.add(document.get("enlace_ejerciciolista").toString())
                Log.i("SIUUUUUUU","${nombre_ejercicioList}")
            }
            val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombre_ejercicioList)
            sp_ejercicios.adapter = adaptador

            sp_ejercicios.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, posicionEjercicio: Int, p3: Long) {
                    idEjercicioSeleccionado = posicionEjercicio
                    img_ejercicio.setImage(CarouselItem("${enlace_ejercicioList.elementAt(idEjercicioSeleccionado)}"))
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

            btn_agregarEjercicio.setOnClickListener {
                var ejercicioAgregado = hashMapOf(
                    "idusuario_ejercicio" to usuario.idUsuario.toString(),
                    "nombre_ejercicio" to nombre_ejercicioList.elementAt(idEjercicioSeleccionado).toString(),
                    "enlace_ejercicio" to enlace_ejercicioList.elementAt(idEjercicioSeleccionado).toString(),
                    "series_ejercicio" to in_series.text.toString(),
                    "repeticiones_ejercicio" to in_repeticiones.text.toString(),
                    "peso_ejercicio" to in_peso.text.toString()
                )
                ejerciciosReg.add(ejercicioAgregado).addOnSuccessListener {
                    in_series.text.clear()
                    in_peso.text.clear()
                    in_repeticiones.text.clear()
                    Toast.makeText(this,"Se registro el ejercicio", Toast.LENGTH_SHORT).show()
                    val openInicioUser = Intent(this, InicioUsuario::class.java)
                    openInicioUser.putExtra("Usuario", usuario)
                    startActivity(openInicioUser)
                }.addOnFailureListener{
                    Log.i("NOOOOO","Failed")
                }
            }
        }
    }
}