package br.com.cotemig.exercicio2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class AnimalAdapter(val contexto: Context, val lista: ArrayList<AnimalModel>) : BaseAdapter() {
    override fun getCount(): Int {
        return lista.size
    }

    override fun getItem(position: Int): Any {
        return lista[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(contexto).inflate(R.layout.animal_item_list, parent, false)

        val animal = lista[position]

        view.findViewById<TextView>(R.id.tvNome).text = animal.nome
        view.findViewById<TextView>(R.id.tvIdade).text = animal.idade
        view.findViewById<TextView>(R.id.tvRaca).text = animal.raca

        val imageView = view.findViewById<ImageView>(R.id.imageView)

        Glide
            .with(contexto)
            .load(animal.foto)
            .into(imageView);

        return view
    }
}