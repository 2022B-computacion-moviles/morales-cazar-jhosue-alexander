package com.example.deber_01

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AdaptadorPersonaje(
    contexto:Context,
    datos:ArrayList<BPersonaje>
): BaseAdapter() {
    val datos_personaje = datos
    val ctx = contexto

    inner class ViewHolder(){
        var nombre: TextView?=null
    }

    override fun getView(position: Int, row: View?, parent: ViewGroup?): View? {

        var rowview = row
        var viewHolder: ViewHolder

        if (rowview == null) {
            viewHolder = ViewHolder()
            val inflater = LayoutInflater.from(ctx)
            rowview = inflater.inflate(R.layout.item_anime, parent, false)

            viewHolder.nombre = rowview.findViewById(R.id.row_nombre) as TextView
            rowview.tag = viewHolder
        } else {
            viewHolder = rowview.tag as ViewHolder
        }
        viewHolder.nombre!!.setText(datos_personaje.get(position).nombrePersonaje)

        return rowview
    }


    override fun getCount(): Int {
        return datos_personaje.size
    }

    override fun getItem(p0: Int): Any {
        return datos_personaje[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }
}