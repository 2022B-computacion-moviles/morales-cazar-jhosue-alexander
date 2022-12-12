import java.util.Date

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

