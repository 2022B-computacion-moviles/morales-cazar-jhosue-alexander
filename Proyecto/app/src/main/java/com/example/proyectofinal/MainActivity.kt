package com.example.proyectofinal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    val db = Firebase.firestore
    val usuarios = db.collection("Usuarios")
    var adaptador: ArrayAdapter<Usuario>? = null
    var usuario = Usuario("","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_Ingresar = findViewById<Button>(R.id.btn_login)
        btn_Ingresar.setOnClickListener {
            usuario.email = findViewById<EditText>(R.id.input_correoUsuario).text.toString()
            usuario.password = findViewById<EditText>(R.id.input_passwordUsuario).text.toString()

            usuarios.whereEqualTo("email_usuario", "${usuario.email}")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        usuarios.whereEqualTo("email_usuario", "${usuario.email}")
                            .whereEqualTo("password_usuario", "${usuario.password}")
                            .get()
                            .addOnSuccessListener { querySnapshot2 ->
                                if (!querySnapshot2.isEmpty) {
                                    // Ambas condiciones se cumplen, iniciar sesión
                                    val docSnapshot = querySnapshot2.documents[0] // asumimos que solo hay un documento que cumple la consulta
                                    usuario.nombre = docSnapshot.getString("nombre_usuario").toString()
                                    usuario.idUsuario = docSnapshot.id
                                    findViewById<EditText>(R.id.input_correoUsuario).text.clear()
                                    findViewById<EditText>(R.id.input_passwordUsuario).text.clear()
                                    val openUsuarioInicio = Intent(this, InicioUsuario::class.java)
                                    openUsuarioInicio.putExtra("Usuario", usuario)
                                    startActivity(openUsuarioInicio)
                                    Log.i("BB","${usuario.idUsuario}")
                                } else {
                                    Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                                }
                            }
                    } else {
                        Toast.makeText(this, "Usuario no existe", Toast.LENGTH_SHORT).show()
                    }
                }

        }


    }



    fun abrirNuevaActividad(view: View?) {
        val intent = Intent(this, RegistroUsuario::class.java)
        startActivity(intent)
    }
}