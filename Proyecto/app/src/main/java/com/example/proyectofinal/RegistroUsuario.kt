package com.example.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistroUsuario : AppCompatActivity() {

    val db = Firebase.firestore
    val usuarios = db.collection("Usuarios")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_usuario)
    }

    override fun onStart() {
        super.onStart()
        val in_nombreusuario = findViewById<EditText>(R.id.input_nombreRegistro)
        val in_correousuario = findViewById<EditText>(R.id.input_correoRegistro)
        val in_passwordusuario = findViewById<EditText>(R.id.input_passwordRegistro)

        val btn_registrar = findViewById<Button>(R.id.btn_crearUsuario)
        btn_registrar.setOnClickListener {
            var usuario = hashMapOf(
                "nombre_usuario" to in_nombreusuario.text.toString(),
                "email_usuario" to in_correousuario.text.toString(),
                "password_usuario" to in_passwordusuario.text.toString()
            )
            usuarios.add(usuario).addOnSuccessListener {
                in_nombreusuario.text.clear()
                in_correousuario.text.clear()
                in_passwordusuario.text.clear()
                Toast.makeText(this,"Usuario registrado exitosamente", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Log.i("Add-User","Failed")
            }
        }
    }
}