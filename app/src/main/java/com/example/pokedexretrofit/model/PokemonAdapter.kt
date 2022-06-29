package com.example.pokedexretrofit.model


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedexretrofit.R
import kotlinx.android.synthetic.main.item_pokemon.view.*


class PokemonAdapter(val items: List<Pokemon>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val pokeImage = itemView.item_img_pokemon
        private val pokeNum = itemView.item_txt_numero
        private val pokeNome = itemView.item_txt_nome
        private val pokeType1 = itemView.item_txt_type1
        private val pokeType2 = itemView.item_txt_type2

        fun bind(pokemon: Pokemon) {

            val numeroFormatado = pokemon.id.toString().padStart(3, '0')
            val imgThumb = "https://www.serebii.net/pokemongo/pokemon/$numeroFormatado.png"

            pokemon.numeroFormatado = numeroFormatado
            pokemon.imgThumb = imgThumb

            Glide.with(itemView).load(pokemon.imgThumb).into(pokeImage)

            pokeNome.text = pokemon.name.capitalize()
            pokeNum.text = "N° ${pokemon.numeroFormatado}"

            if (pokemon.types.size > 1){
                pokeType1.text = pokemon.types[0].type.name
                pokeType2.text = pokemon.types[1].type.name
            } else {
                pokeType1.text = pokemon.types[0].type.name
                pokeType2.visibility = View.GONE
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return PokemonViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        )

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is PokemonViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount() = items.size
}