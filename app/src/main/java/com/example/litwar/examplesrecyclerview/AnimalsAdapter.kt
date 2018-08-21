package com.example.litwar.examplesrecyclerview


import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

class AnimalsAdapter : RecyclerView.Adapter<AnimalsAdapter.AnimalViewHolder>{

    constructor() : super(){
        itemClickListener = null
    }

    constructor(itemClickListener: ((Animal, Int)-> Unit)) : super(){
        this.itemClickListener = itemClickListener
    }

    private val items = mutableListOf<Animal>()
    private val itemClickListener : ((Animal,Int) -> Unit)?


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.
                from(parent.context).inflate(R.layout.item_animal,parent,false)

        return AnimalViewHolder(view)

    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val item = items[position]

        holder.animal = item
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setAnimals(animals : MutableList<Animal>){
        items.clear()
        items.addAll(animals)
        notifyDataSetChanged()
    }

    inner class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var animal : Animal? = null
            set(value){
                itemView.findViewById<TextView>(R.id.label_name).text = value?.name
                field = value
            }

        init{
            itemView.setOnClickListener {
                animal?.let{
                    itemClickListener?.invoke(animal as Animal, adapterPosition)

                }
            }

        }
    }
}