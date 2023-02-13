package com.example.deber_01
//LISTOOOO
import BAnime
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelper(
    contexto: Context?,
): SQLiteOpenHelper(
    contexto,
    "deber",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaAnime =
        """
               CREATE TABLE ANIME(
               idAnime INTEGER PRIMARY KEY AUTOINCREMENT,
               nombreAnime VARCHAR(50),
               anoDeLanzamientoAnime VARCHAR(50),
               generoAnime VARCHAR(50),
               estudioAnime VARCHAR(50)
               ) 
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaAnime)

        val scriptSQLCrearTablaPersonaje =
            """
               CREATE TABLE PERSONAJE(
               idPersonaje INTEGER PRIMARY KEY AUTOINCREMENT,
               idAnime INTEGER,
               nombrePersonaje VARCHAR(50),
               edadPersonaje INTEGER,
               generoPersonaje VARCHAR(50)
               ) 
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaPersonaje)

        val scripIngresarAnimes = """
            INSERT INTO ANIME (nombreAnime, anoDeLanzamientoAnime, generoAnime, estudioAnime)
            VALUES ("Dragon Ball", 1984, "Accion", "Toei Animation"),
            ("One Piece", 1999, "Eiichiro Oda", "Toei Animation"),
            ("Drangon Ball Z", 1989, "Accion", "Toei Animation");
        """.trimIndent()
        db?.execSQL(scripIngresarAnimes)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun crearAnime(
        nombreAnime: String,
        anoDeLanzamientoAnime: String,
        generoAnime: String,
        estudioAnime: String
    ): Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombreAnime", nombreAnime)
        valoresAGuardar.put("anoDeLanzamientoAnime", anoDeLanzamientoAnime)
        valoresAGuardar.put("generoAnime", generoAnime)
        valoresAGuardar.put("estudioAnime", estudioAnime)

        val resultadoGuardar = basedatosEscritura
            .insert(
                "ANIME",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarAnimeFormulario(id: Int): Boolean {
        val conexionEscritura =writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "ANIME",
            "idAnime=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if(resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarAnimeFormulario(
        nombreAnime: String,
        anoDeLanzamientoAnime: String,
        generoAnime: String,
        estudioAnime: String,
        idActualizar: Int
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar =ContentValues()
        valoresAActualizar.put("nombreAnime", nombreAnime)
        valoresAActualizar.put("anoDeLanzamientoAnime", anoDeLanzamientoAnime)
        valoresAActualizar.put("generoAnime", generoAnime)
        valoresAActualizar.put("estudioAnime", estudioAnime)

        val resultadoActualizacion = conexionEscritura
            .update(
                "ANIME",
                valoresAActualizar,
                "idAnime=?",
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return if(resultadoActualizacion == -1) false else true
    }


    fun getAllAnime(): ArrayList<BAnime>{
        var animeList = ArrayList<BAnime>()
        val baseDatosLectura = readableDatabase
        var animesEncontrados= BAnime(0,"","","","")
        val scriptGetAnime = "SELECT * FROM ANIME"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptGetAnime,
            null
        )

        if(resultadoConsultaLectura!=null && resultadoConsultaLectura.count!=0){
            resultadoConsultaLectura.moveToFirst()
            do {

                    val id_Anime = resultadoConsultaLectura.getInt(0)
                    val nombre_Anime = resultadoConsultaLectura.getString(1)
                    val anio_Anime = resultadoConsultaLectura.getString(2)
                    val genero_Anime = resultadoConsultaLectura.getString(3)
                    val estudio_Anime = resultadoConsultaLectura.getString(4)

                if(id_Anime!=null){
                    animesEncontrados.idAnime = id_Anime
                    animesEncontrados.nombreAnime = nombre_Anime
                    animesEncontrados.anoDeLanzamientoAnime = anio_Anime
                    animesEncontrados.generoAnime = genero_Anime
                    animesEncontrados.estudioAnime = estudio_Anime
                }

                animeList.add(animesEncontrados)
                animesEncontrados = BAnime(0,"","","","")

            }while (resultadoConsultaLectura.moveToNext())
        }else{
            animeList=ArrayList<BAnime>()
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return animeList
    }


    //FUNCIONES PERSONAJE
    fun crearPersonaje(
        idAnime: Int,
        nombrePersonaje: String,
        edadPersonaje: Int,
        generoPersonaje: String,
    ): Boolean{
        val basedatosEscritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("idAnime", idAnime)
        valoresAGuardar.put("nombrePersonaje", nombrePersonaje)
        valoresAGuardar.put("edadPersonaje", edadPersonaje)
        valoresAGuardar.put("generoPersonaje", generoPersonaje)

        val resultadoGuardar = basedatosEscritura
            .insert(
                "PERSONAJE",
                null,
                valoresAGuardar
            )
        basedatosEscritura.close()
        return if(resultadoGuardar.toInt() == -1) false else true
    }

    fun eliminarPersonaje(idPersonaje: Int): Boolean{
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "PERSONAJE",
                "idPersonaje=?",
                arrayOf(
                    idPersonaje.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarPersonajeFormulario(
        nombrePersonaje: String,
        edadPersonaje: Int,
        generoPersonaje: String,
        idActualizar: Int
    ):Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombrePersonaje", nombrePersonaje)
        valoresAActualizar.put("edadPersonaje", edadPersonaje)
        valoresAActualizar.put("generoPersonaje", generoPersonaje)
        val resultadoActualizacion = conexionEscritura
            .update(
                "PERSONAJE",
                valoresAActualizar,
                "idPersonaje=?",
                arrayOf(
                    idActualizar.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoActualizacion == -1) false else true
    }

    fun getPersonajeIdAnime(idAnime: Int): ArrayList<BPersonaje>{
        var personajeList = ArrayList<BPersonaje>()
        val baseDatosLectura = readableDatabase
        var personajesEncontrados = BPersonaje(0,0, "",0,"")
        val scriptGetPersonaje = "SELECT * FROM PERSONAJE WHERE idAnime=${idAnime}"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptGetPersonaje,
            null
        )
        if(resultadoConsultaLectura!=null && resultadoConsultaLectura.count!=0){
            resultadoConsultaLectura.moveToFirst()
            do {

                if(idAnime != null){

                        personajesEncontrados.idPersonaje = resultadoConsultaLectura.getInt(0)
                        personajesEncontrados.idAnime = resultadoConsultaLectura.getInt(1)
                        personajesEncontrados.nombrePersonaje = resultadoConsultaLectura.getString(2)
                        personajesEncontrados.edadPersonaje = resultadoConsultaLectura.getInt(3)
                        personajesEncontrados.generoPersonaje = resultadoConsultaLectura.getString(4)

                }
                personajeList.add(personajesEncontrados)
                personajesEncontrados = BPersonaje(0,0, "",0,"")

            }while (resultadoConsultaLectura.moveToNext())
        }else{
            personajeList = ArrayList<BPersonaje>()
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return personajeList
    }
}