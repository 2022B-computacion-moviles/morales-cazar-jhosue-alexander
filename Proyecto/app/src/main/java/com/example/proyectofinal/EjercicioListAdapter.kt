package com.example.proyectofinal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.imaginativeworld.whynotimagecarousel.utils.setImage

class EjercicioListAdapter(
    private val context: InicioUsuario,
    private val ejercicios: List<Ejercicio>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<EjercicioListAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var enlace= ""
        var imagen: ImageView
        var nombre: TextView
        var numeroSeries: TextView
        var numeroRepeticiones: TextView
        var peso: TextView
        init {
            imagen = view.findViewById(R.id.imagen_ejercicio)
            nombre = view.findViewById(R.id.nombre_ejercicio)
            numeroSeries = view.findViewById(R.id.series_ejercicio)
            numeroRepeticiones = view.findViewById(R.id.repeticiones_ejercicio)
            peso = view.findViewById(R.id.peso_ejercicio)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.item_ejercicio, //Definimos la vista de nuestro recyvler
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val img = ejercicios[position]
        holder.enlace = img.enlace.toString()
        holder.imagen.setImage(CarouselItem(holder.enlace))
        holder.nombre.text = img.nombre
        holder.numeroSeries.text= img.numeroSeries.toString()
        holder.numeroRepeticiones.text = img.numeroRepeticiones.toString()
        holder.peso.text = img.peso.toString()
    }

    override fun getItemCount(): Int {
        return ejercicios.size
    }

}