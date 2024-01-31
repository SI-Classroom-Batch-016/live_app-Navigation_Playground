package com.example.navigationvertiefung.data

object DataSource {


    fun getData(): List<String> {

        val data: MutableList<String> = mutableListOf()
        for(i in 1..50) {
            data.add("$i")
        }

        return data
    }


}