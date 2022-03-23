package mx.tecnm.ittepic.ladm_u2_practica2_loteria

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapater(texto: MutableList<String>, imagenesMain: MutableList<Int>) : RecyclerView.Adapter<CustomAdapater.ViewHolder>() {
    private var testo = texto
    private var imagenes = imagenesMain

    override fun onCreateViewHolder(viewGroup: ViewGroup, iterable: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout,viewGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.Imagen.setImageResource(imagenes[position])
        viewHolder.Titulo.text = testo[position]
    }

    override fun getItemCount(): Int {
        return testo.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var Imagen: ImageView
        var Titulo: TextView

        init {
            Imagen =  itemView.findViewById(R.id.itemImagen)
            Titulo = itemView.findViewById(R.id.itemTitle)
        }
    }


}