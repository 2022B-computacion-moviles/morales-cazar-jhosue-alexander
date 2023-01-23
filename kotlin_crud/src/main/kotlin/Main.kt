import java.io.File
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.exitProcess

fun main(){
    AnimeMenu()
}


fun AnimeMenu() {
    var opcion: Int = 0
    println("\nLista de animes")
    printArray("AnimesList", "Anime", "", null)
    println()

    while (opcion != 6) {
        println("\nMenu de Anime\n1) Añadir nuevo anime")
        println("2) Ver informacion de un Anime")
        println("3) Actualizar Anime")
        println("4) Borrar Anime")
        println("5) Personajes")
        println("6) Salir")
        print("\nEscoja -> ")
        opcion = Integer.parseInt(readLine())
        when (opcion) {
            1 -> {
                println("Complete la siguiente informacion sobre el Anime")
                println("Nombre del Anime")
                var nombre = ""
                nombre = readLine().toString()
                println("Creador del Anime")
                var creador = ""
                creador = readLine().toString()
                println("Año de Lanzamiento")
                var anoDeLanzamiento = ""
                anoDeLanzamiento = readLine().toString()
                println("Genero del Anime")
                var genero = ""
                genero = readLine().toString()
                println("Estudio del Anime")
                var estudio = ""
                estudio = readLine().toString()
                val animeToAdd = CrudAnime(nombre, creador, anoDeLanzamiento, genero, estudio)

                animeToAdd.addAnimeInfo()

                println("Anime agregado exitosamente!!")
                println("\n\nLISTA DE ANIMES")
                printArray("AnimesList", "Anime", "", null)
                println()
            }

            2 -> {
                println("\nUsa el numero del Anime para ver mas informacion")
                print("Escoja -> ")
                var animePosition: Int = Integer.parseInt(readLine())
                printArray("AnimesInfo", "Anime", "", animePosition)
            }

            3 -> {
                println("\nEscoge el numero del Anime para actualizar la informacion")
                print("Escoja -> ")
                var animePosition: Int = Integer.parseInt(readLine())
                printArray("AnimesInfo", "Anime", "", animePosition)
                println("\n\nCompleta la siguiente informacion sobre el Anime")
                println("Nombre del Anime")
                var nombre = ""
                nombre = readLine().toString()
                println("Creador del Anime")
                var creador = ""
                creador = readLine().toString()
                println("Año de Lanzamiento")
                var anoDeLanzamiento = ""
                anoDeLanzamiento = readLine().toString()
                println("Genero del Anime")
                var genero = ""
                genero = readLine().toString()
                println("Estudio del Anime")
                var estudio = ""
                estudio = readLine().toString()
                val animeToUpdate = CrudAnime(nombre, creador, anoDeLanzamiento, genero, estudio)

                animeToUpdate.updateInfoAnime(animePosition)

                println("\n\nLista de Animes")
                printArray("AnimesList", "Anime", "", null)
                println()

            }
            4 ->{
                println("\nEscoja el numero del anime que desea borrar la informacion")
                print("Escoja -> ")
                var animePosition: Int = Integer.parseInt(readLine())
                print("Todos los personajes de [")
                printArray("nombreAnime","Anime","",animePosition)
                print("] se borraran tambien")
                println("\nPresiona [y] o [n]")
                var confirmInput: String = readLine().toString()
                if (confirmInput.equals("y")){
                    val animeToDelete = CrudAnime(animePosition)
                    animeToDelete.deleteAnime(animePosition)
                    println("\n\nLista de Animes")
                    printArray("AnimesList","Anime","",null)
                    println()
                }else if (confirmInput.equals("n")){
                    println("\n\nLista de Animes")
                    printArray("AnimesList","Anime","",null)
                    println()
                }
            }
            5 -> {
                println("\nEscoja el numero del anime de la cual desea ver los personajes")
                print("Escoja -> ")
                var animePosition: Int = Integer.parseInt(readLine())
                var animeName: String = printArray("nombreAnime","Anime","",animePosition)
                PersonajesMenu(animeName)
            }
            6 -> {
                exitProcess(0)
            }

        }

    }
}

fun PersonajesMenu(nombreAnime: String){
    var opcion: Int = 0
    println("\n\nLista de Personajes")
    printArray("PersonajeList","Personajes",nombreAnime,null)
    println()

    while (opcion != 5){
        println("\n"+nombreAnime+" Menu Personajes\n1) Agregar nuevo personaje")
        println("2) Ver informacion sobre un personaje")
        println("3) Actualizar personaje")
        println("4) Borrar personaje")
        println("5) Regresar")
        print("\nEscoja -> ")
        opcion = Integer.parseInt(readLine())

        when(opcion){
            1 -> {
                println("Llena la siguiente informacion acerca del personaje")
                println("Nombre del personaje")
                var nombre: String = ""
                nombre = readLine().toString()
                println("Anime al que pertenece")
                var anime: String = ""
                anime = readLine().toString()
                println("Edad del personaje")
                var edad: String = ""
                edad = readLine().toString()
                println("Genero del personaje")
                var genero : String =""
                genero = readLine().toString()
                println("Raza del personaje")
                var raza: String = ""
                raza = readLine().toString()
                val personajeToAdd = CrudPersonaje(nombre, anime, edad, genero, raza)

                personajeToAdd.addPersonaje()

                println("Personaje agregado con exito!!")
                println("\n\nLista de Personajes")
                printArray("PersonajeList","Personajes",nombreAnime,null)
                println()
            }
            2 -> {
                println("\nUsa el numero del personaje para ver mas informacion")
                print("Escoja -> ")
                var personajePosition: Int = Integer.parseInt(readLine())
                printArray("PersonajeInfo","Personajes",nombreAnime,personajePosition)
            }
            3 -> {
                println("\nUsa el numero del personaje para ver mas informacion")
                print("Escoja -> ")
                var personajePosition: Int = Integer.parseInt(readLine())
                printArray("PersonajeInfo","Personajes",nombreAnime,personajePosition)
                println("Llena la siguiente informacion acerca del personaje")
                println("Nombre del personaje")
                var nombre: String = ""
                nombre = readLine().toString()
                println("Anime al que pertenece")
                var anime: String = ""
                anime = readLine().toString()
                println("Edad del personaje")
                var edad: String = ""
                edad = readLine().toString()
                println("Genero del personaje")
                var genero : String =""
                genero = readLine().toString()
                println("Raza del personaje")
                var raza: String = ""
                raza = readLine().toString()
                val personajeToUpdate = CrudPersonaje(nombre, anime, edad, genero, raza)

                personajeToUpdate.updatePersonaje(personajePosition)

                println("\n\nLista de Personajes")
                printArray("PersonajeList","Personajes",nombreAnime,null)
                println()
            }
            4 -> {
                println("\nUse el numero del personaje que desea borrar")
                print("Escoja -> ")
                var personajePosition: Int = Integer.parseInt(readLine())
                print("Desea borrar el personaje?")
                println("\nPresiona [y] o [n]")
                var confirmInput: String = readLine().toString()
                if (confirmInput.equals("y")){
                    val personajeToDelete = CrudPersonaje(personajePosition)
                    personajeToDelete.deletePersonaje(personajePosition)
                    println("\n\nLista de Personajes")
                    printArray("PersonajeList","Personajes",nombreAnime,null)
                    println()
                }else if (confirmInput.equals("n")){
                    println("\n\nLista de Personajes")
                    printArray("PersonajeList","Personajes",nombreAnime,null)
                    println()
                }
            }
            5 -> {
                AnimeMenu()
            }
        }

    }

}

    abstract class Anime(
        protected var nombreAnime: String,
        protected var creadorAnime: String,
        protected var anoDeLanzamientoAnime: String,
        protected var generoAnime: String,
        protected var estudioAnime: String,
    ) {
        init {
            println("Inicializar")
        }
    }

    class CrudAnime(
        nombre: String,
        creador: String,
        anoDeLanzamiento: String,
        genero: String,
        estudio: String
    ) : Anime(
        nombre,
        creador,
        anoDeLanzamiento,
        genero,
        estudio
    ) {
        init {
            this.nombreAnime
            this.creadorAnime
            this.anoDeLanzamientoAnime
            this.generoAnime
            this.estudioAnime
        }

        constructor(
            position: Int
        ) : this(
            "", "", "", "", ""
        ) {

        }

        fun addAnimeInfo() {
            var existingAnimesInfo = ArrayList<String>()
            existingAnimesInfo = existingRegister()
            var auxAnime: String =
                nombreAnime + "," + creadorAnime + "," + anoDeLanzamientoAnime + "," + generoAnime + "," + estudioAnime
            existingAnimesInfo.add(auxAnime)

            val dataAnimeFile =
                "D:/Jhosue/2022-B/Aplicaciones Moviles/morales-cazar-jhosue-alexander/kotlin_crud/AnimeInfo.txt"
            val myfile = File(dataAnimeFile)
            myfile.printWriter().use { out ->
                existingAnimesInfo.forEach {
                    out.println(it)
                }
            }
        }


        fun updateInfoAnime(position: Int) {
            var existingAnimesInfo = ArrayList<String>()
            existingAnimesInfo = existingRegister()
            var auxPos = position - 1
            var auxAnime: String = nombreAnime + "," + creadorAnime + "," + anoDeLanzamientoAnime + "," + generoAnime + "," + estudioAnime
            existingAnimesInfo.forEachIndexed { index: Int, actualValue: String ->
                if (index == auxPos) {
                    existingAnimesInfo[index] = auxAnime
                }
            }
            val dataAnimeFile = "D:/Jhosue/2022-B/Aplicaciones Moviles/morales-cazar-jhosue-alexander/kotlin_crud/AnimeInfo.txt"
            val myfile = File(dataAnimeFile)
            myfile.printWriter().use { out ->
                existingAnimesInfo.forEach {
                    out.println(it)
                }
            }
        }

        fun deleteAnime(position: Int) {
            var existingAnimesInfo = ArrayList<String>()
            var existingAnimesInfoAux = ArrayList<String>()
            existingAnimesInfo = existingRegister()
            var auxPos = position - 1
            existingAnimesInfo.forEachIndexed { index: Int, actualValue: String ->
                if (index != auxPos) {
                    existingAnimesInfoAux.add(existingAnimesInfo[index])
                }
            }

            val dataAnimeFile =
                "D:/Jhosue/2022-B/Aplicaciones Moviles/morales-cazar-jhosue-alexander/kotlin_crud/AnimeInfo.txt"
            val myfile = File(dataAnimeFile)
            myfile.printWriter().use { out ->
                existingAnimesInfoAux.forEach {
                    out.println(it)
                }
            }
        }

        companion object {

            var existingPersonajes = ArrayList<String>()

            fun existingRegister(): ArrayList<String> {
                var existingAnimesList = ArrayList<String>()
                val inputStream: InputStream =
                    File("D:/Jhosue/2022-B/Aplicaciones Moviles/morales-cazar-jhosue-alexander/kotlin_crud/AnimeInfo.txt").inputStream()
                val lineList = mutableListOf<String>()
                inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
                lineList.forEach {
                    existingAnimesList.add(it)
                }
                return existingAnimesList
            }
        }
    }




abstract class Personaje(
    protected var personajeNombre: String,
    protected var personajeAnime: String,
    protected var personajeEdad: String,
    protected var personajeGenero: String,
    protected var personajeRaza: String,
) {
    init {
        println("Inicializar")
    }
}

class CrudPersonaje(
    nombre: String,
    anime: String,
    edad: String,
    genero: String,
    raza: String
): Personaje(
    nombre,
    anime,
    edad,
    genero,
    raza
){
    init {
        this.personajeNombre
        this.personajeAnime
        this.personajeEdad
        this.personajeGenero
        this.personajeRaza
    }
    constructor(
        position: Int
    ) : this(
        "","","","",""
    ){

    }

    fun addPersonaje(){
        var existingPersonajesInfo = ArrayList<String>()
        existingPersonajesInfo = existingRegisterPersonajes()
        var auxPersonaje: String = personajeNombre+","+personajeAnime+","+personajeEdad+","+personajeGenero+","+personajeRaza
        existingPersonajesInfo.add(auxPersonaje)

        val dataPersonajeFile = "D:/Jhosue/2022-B/Aplicaciones Moviles/morales-cazar-jhosue-alexander/kotlin_crud/PersonajesInfo.txt"
        val myfile = File(dataPersonajeFile)
        myfile.printWriter().use { out ->
            existingPersonajesInfo.forEach{
                out.println(it)
            }
        }
    }

    fun updatePersonaje(position: Int){
        var existingPersonajesInfo = ArrayList<String>()
        existingPersonajesInfo = existingRegisterPersonajes()
        var auxPos = position-1
        var auxPersonaje: String = personajeNombre+","+personajeAnime+","+personajeEdad+","+personajeGenero+","+personajeRaza
        existingPersonajesInfo.forEachIndexed { index: Int, actualValue: String ->
            if (index == auxPos){
                existingPersonajesInfo[index] = auxPersonaje
            }
        }
        val dataPersonajeFile = "D:/Jhosue/2022-B/Aplicaciones Moviles/morales-cazar-jhosue-alexander/kotlin_crud/PersonajesInfo.txt"
        val myfile = File(dataPersonajeFile)
        myfile.printWriter().use { out ->
            existingPersonajesInfo.forEach {
                out.println(it)
            }
        }
    }

    fun deletePersonaje(position: Int){
        var existingPersonajesInfo = ArrayList<String>()
        var existingPersonajesInfoAux = ArrayList<String>()
        existingPersonajesInfo = existingRegisterPersonajes()
        var auxPos = position
        existingPersonajesInfo.forEachIndexed { index: Int, actualValue: String ->
            if (index == auxPos){
                existingPersonajesInfoAux.add(existingPersonajesInfo[index])
            }
        }
        val dataPersonajeFile = "D:/Jhosue/2022-B/Aplicaciones Moviles/morales-cazar-jhosue-alexander/kotlin_crud/PersonajesInfo.txt"
        val myfile = File(dataPersonajeFile)
        myfile.printWriter().use { out ->
            existingPersonajesInfoAux.forEach {
                out.println(it)
            }
        }
    }

    companion object{
        fun existingRegisterPersonajes(): ArrayList<String>{
            var existingPersonajesList = ArrayList<String>()
            val inputStream: InputStream = File("D:/Jhosue/2022-B/Aplicaciones Moviles/morales-cazar-jhosue-alexander/kotlin_crud/PersonajesInfo.txt").inputStream()
            val lineList = mutableListOf<String>()
            inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it) } }
            lineList.forEach{
                existingPersonajesList.add(it)
            }
            return existingPersonajesList
        }
    }
}




    fun printArray(whatToPrint: String, entity: String, personajeAnime: String, position: Int?): String {
        var returnValue = ""
        var phatFile = "D:/Jhosue/2022-B/Aplicaciones Moviles/morales-cazar-jhosue-alexander/kotlin_crud/"
        if (entity.equals("Anime")) {
            phatFile = phatFile.plus("AnimeInfo.txt")
        } else if (entity.equals("Personajes")) {
            phatFile = phatFile.plus("PersonajesInfo.txt")
        }
        var inputStream: InputStream = File(phatFile).inputStream()
        val lineList = mutableListOf<String>()
        var auxPos = position?.minus(1)

        inputStream.bufferedReader().useLines { lines ->
            lines.forEach {
                lineList.add(it)
            }
        }
        if (position != null && personajeAnime.equals("")) {
            lineList.forEachIndexed { index: Int, actualValue: String ->
                if (auxPos == index) {
                    returnValue = tokenizer(index, actualValue, whatToPrint, "")
                }
            }
        } else if (!personajeAnime.equals("") && position == null) {
            lineList.forEachIndexed { index: Int, actualValue: String ->
                tokenizer(index, actualValue, whatToPrint, personajeAnime)
            }
        } else if (!personajeAnime.equals("") && position != null) {
            lineList.forEachIndexed { index: Int, actualValue: String ->
                if (auxPos == index) {
                    returnValue = tokenizer(index, actualValue, whatToPrint, personajeAnime)
                }
            }
        } else {
            lineList.forEachIndexed { index: Int, actualValue: String ->
                tokenizer(index, actualValue, whatToPrint, "")
            }
        }
        return returnValue
    }

    fun tokenizer(index: Int, animeInfo: String, whatToPrint: String, personajeAnime: String): String {
        var returnValue: String = ""
        var listAnime = ArrayList<String>()
        val st = StringTokenizer(animeInfo, ",")
        val auxIndex = index + 1
        while (st.hasMoreTokens()) {
            listAnime.add(st.nextToken())
        }
        if (whatToPrint.equals("AnimesInfo")) {
            print(auxIndex)
            print("\t")
            listAnime.forEach {
                print(it + "\t")
            }
            println("\n")
        } else if (whatToPrint.equals("AnimesList")) {
            print(auxIndex)
            print("\t")
            print(listAnime[0])
            println()
        } else if (whatToPrint.equals("nombreAnime")) {
            returnValue = listAnime[0]
            print(listAnime[0])
        } else if (whatToPrint.equals("PersonajeInfo")) {
            if (listAnime[1].equals(personajeAnime)) {
                print(auxIndex)
                print("\t")
                listAnime.forEach {
                    print(it + "\t")
                }
                println("\n")
            }
        } else if (whatToPrint.equals("PersonajeList")) {
            if (listAnime[1].equals(personajeAnime)) {
                print(auxIndex)
                print("\t")
                print(listAnime[0])
                println()
            }
        } else if (whatToPrint.equals("PersonajeNombre")) {
            if (listAnime[1].equals(personajeAnime)) {
                returnValue = listAnime[0]
                print(listAnime[0])
            }
        }
        return returnValue
    }