package com.example.deber_01
//LISTOOOO
import BAnime
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdaptadorAnime(
    contexto: Context,
    datos: ArrayList<BAnime>
):BaseAdapter() {
    val datosAnime = datos
    val ctx = contexto

    inner class ViewHolder(){
        var nombre: TextView?= null
    }

    override fun getView(position: Int, row: View?, parent: ViewGroup?): View? {
        var rowview = row
        var viewHolder: ViewHolder

        if(rowview==null){
            viewHolder=ViewHolder()
            val inflater=LayoutInflater.from(ctx)
            rowview=inflater.inflate(R.layout.item_anime,parent,false)

            viewHolder.nombre=rowview.findViewById(R.id.row_nombre) as TextView
            rowview.tag=viewHolder
        }else{
            viewHolder=rowview.tag as ViewHolder
        }
        viewHolder.nombre!!.setText(datosAnime.get(position).nombreAnime)

        return rowview
    }

    override fun getCount(): Int {
        return datosAnime.size
    }

    override fun getItem(p0: Int): Any {
        return datosAnime[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }
}