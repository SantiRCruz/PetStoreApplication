package com.example.petstoreapplication.models.Pet

class ElseJson {
    var id = 0
    var name = ""


    constructor(id: Int, name: String) {
        this.id = id
        this.name = name
    }

    constructor()

    override fun toString(): String {
        return "ElseJson(id=$id, name='$name')"
    }

}