package com.example.petstoreapplication.models.Pet

class Pet {

    var id :Long = 0
    var category = ElseJson()
    var name = ""
    var photoUrls = ArrayList<String>()
    var tags = ArrayList<ElseJson>()
    var status = ""

    constructor()

    constructor(
        id: Long,
        category: ElseJson,
        name: String,
        photoUrls: ArrayList<String>,
        tags: ArrayList<ElseJson>,
        status: String
    ) {
        this.id = id
        this.category = category
        this.name = name
        this.photoUrls = photoUrls
        this.tags = tags
        this.status = status
    }

    override fun toString(): String {
        return "Pet(id=$id, category=$category, name='$name', photoUrls=$photoUrls, tags=$tags, status='$status')"
    }


}