package com.example.movcompjamc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CIntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cintent_explicito_parametros)
        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getStringExtra("edad", 0)
    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nombreModificado", "Alexander")
        intentDevolverParametros.putExtra("edadModificado", 26)
        //this.setResult
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        //this.finish()
        finish()
    }
}