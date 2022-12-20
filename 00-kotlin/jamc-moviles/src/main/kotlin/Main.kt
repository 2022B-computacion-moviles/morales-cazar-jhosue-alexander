import java.util.Date
import javax.swing.text.StyledEditorKit.BoldAction

fun main(args: Array<String>) {
    println("Hola Mundo!")

    //  Tipos de variables
    //  Inmutables (no se puede re asignar) =
    val inmutable: String = "Vicente";
    //inmutable = "Adrian";
    // Mutables (re asignar) =
    var mutable:String = "Vicente";
    mutable = "Adrian";
    //  val > var

    //  Duck Typing

    val ejemploVariable = "Ejemplo"
    ejemploVariable.trim()
    val edadEjemplo: Int = 12

    //  Variables primitivas
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'S'
    val mayorEdad = true
    //  Clases
    val fechaNacimiento: Date = Date()  // No se usa "new" en clases

    if (true){

    }else{

    }

    // Switch no existe
    val estadoCivilWhen = "S"
    when (estadoCivilWhen){
        ("S")->{
            println("Soltero")
        }
        "C" -> println("Casado")
        else -> println("Desconocido")
    }
    
    val coqueto = if(estadoCivilWhen == "S") "Si" else "No"

    val sumaUno = Suma(1,2)
    val sumaDos = Suma(1, null)
    val sumaTres = Suma(null, 2)
    val sumaCuatro = Suma(null, null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.historialSumas)

    // ARREGLOS
    //Tipos de arreglos
    //Arreglo estatico
    val arregloEstatico: Array<Int> = arrayOf(1, 2, 3)
    println(arregloEstatico)

    //Arreglo Dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    //OPERADORES -> Sirven para los arreglos estaticos y dinamicos
    // For Each -> Unit
    //Iterar arreglo

    val respuestaForEach: Unit = arregloDinamico
        .forEach{
            valorActual: Int ->
            println("Valor actual: ${valorActual}")
        }
    arregloEstatico
        .forEachIndexed{ indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)

    //MAP -> Muta el arreglo (Cambia el arrelo)
    //1) Enviemos el nuevo valor de la iteracion
    //2) Nos devuelve es un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
        .map{valorActual: Int ->
            return@map valorActual.toDouble() + 100.00
        }

    println(respuestaMap)

    val respuestaMapDos = arregloDinamico.map {it+15}
    println(respuestaMapDos)

    //Filter -> FILTAR EL ARREGLO
    //1) Devolver una expresion (TRUE o FALSE)
    //2) Nuevo arreglo filtrado

    val respuestaFilter: List<Int> = arregloDinamico
        .filter{ valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5 //Expresion condicion
            return@filter mayoresACinco
        }

    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    //OR y AND
    //OR -> ANY (Alguno cumple?)
    //AND -> ALL (Todos cumplen?)

    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny)

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll)


    //REDUCE -> Valor acumulado

    val respuestaReduce: Int = arregloDinamico
        .reduce{
            acumulado: Int, valorActual: Int ->
            return@reduce (acumulado + valorActual)
        }
    println(respuestaReduce)


}

fun imprimirNombre(nombre: String): Unit{
    println("Nombre : ${nombre}")
}

fun calcularSueldo(
    sueldo: Double, //requerido
    tasa: Double = 12.00, //opcional por defecto
    bonoEspecial: Double? = null //opcional nula
):Double{
    if(bonoEspecial != null){
        return sueldo * tasa * bonoEspecial
    }
    return sueldo * tasa

}

abstract class NumerosJava{
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
        uno: Int, // parametro
        dos: Int  // parametro
    ){
       this.numeroUno = uno
       this.numeroDos = dos
       println("Iniciando")
    }
}

abstract class Numeros(
    //uno: Int, //Parametros
    //public var uno:Int, // Propiedad a la clase publica
    protected val numeroUno:Int, //Propiedad
    protected val numeroDos:Int //Propiedad

){
    init { //Bloque codigo constructor primario
        //this.numeroDos // "this" opcional
        //this.numeroUno
        numeroUno
        numeroDos
        println("Iniciando")
    }
}

class Suma( //Constructos primario suma
    uno: Int, //Parametro
    dos: Int, //Parametro
): Numeros( //Heredamos de la clase Numero
    //Super constructor numeros
    uno,
    dos,
){
    init { //Bloque constructor primario
        this.numeroUno
        this.numeroDos
    }
    constructor( //Segundo Constructor
        uno: Int?,
        dos: Int //Parametros
    ): this(
            if(uno == null) 0 else uno,
            dos
    ){}
    constructor( //Terce Constructor
        uno: Int, //Parametros
        dos: Int?  //Parametros
    ): this(
        uno,
        if (dos == null) 0 else dos,
    ){}
    constructor(
        uno: Int?,
        dos: Int?
    ): this(
        if (uno == null) 0 else uno,
        if (dos == null) 0 else dos,
    ){}
    fun sumar(): Int{
        val total = numeroUno+numeroDos
        agregarHistorial(total)
        return total
    }
        companion object{
            val pi = 3.14; //Suma.pi -> 3.14
            val historialSumas = arrayListOf<Int>() //Suma.historialSumas
            fun agregarHistorial(valorNuevaSuma: Int){
                historialSumas.add(valorNuevaSuma)
            }
        }
}

