package com.example.litwar.examplesrecyclerview

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

const val URL_ANIMALS = "http://5b7b66aef583510014298c87.mockapi.io/animals"

object AnimalsRepo {

    var animals : MutableList<Animal> = mutableListOf()
    /*get(){
        if(field.isEmpty())
            field.addAll(dummyAnimals())

        return field
    }*/

    fun requestAnimals(context : Context, success : ((MutableList<Animal>)->Unit),
                       error : (()->Unit)){
        if(animals.isEmpty()){
            val request = JsonArrayRequest(Request.Method.GET, URL_ANIMALS,null,
                    {response ->
                        animals = parseAnimals(response)
                        success.invoke(animals)
                    },
                    {errorVolley ->
                        error.invoke()
                    })

            Volley.newRequestQueue(context).add(request)
        }else{
            success.invoke(animals)
        }

    }

    private fun parseAnimals(jsonArray : JSONArray) : MutableList<Animal>{
        val animals = mutableListOf<Animal>()
        for (index in 0..(jsonArray.length()-1)){
            val animal = parseAnimal(jsonArray.getJSONObject(index))
            animals.add(animal)
        }

        return animals
    }

    private fun parseAnimal(jsonAnimal : JSONObject) : Animal{
        return Animal(jsonAnimal.getString("name"),
                jsonAnimal.getString("sound")
        )
    }

    private fun dummyAnimals() : MutableList<Animal>{

        var list : MutableList<Animal> = mutableListOf()

        for(index in 1..10){
            var animal : Animal = Animal(
                    name = "animal{$index}",
                    sound = "sound{$index}"
            )

            list.add(animal)

        }

        return list

    }

}