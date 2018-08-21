package com.example.litwar.examplesrecyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list : RecyclerView = findViewById(R.id.list)
        //cuando se recibe una lambda se puede recibir lambda por fuera
        val adapter = AnimalsAdapter(){ item ,position ->
            mostrarMensaje(item.sound)


        }
        list.adapter = adapter

        list.layoutManager = LinearLayoutManager(this)

        val animals : MutableList<Animal> = AnimalsRepo.animals
        adapter.setAnimals(animals)


        Log.i("Animals","${animals.size}")

        list.adapter = adapter

        AnimalsRepo.requestAnimals(this,
                {animals ->
                    adapter.setAnimals(animals)
                },
                {

                })

    }

    fun mostrarMensaje(sound:String){
        Toast.makeText(this@MainActivity,sound,Toast.LENGTH_SHORT).show()
    }
}
