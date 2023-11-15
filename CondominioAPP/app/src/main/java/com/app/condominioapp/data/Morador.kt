package com.app.condominioapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Moradores")
data class Morador (
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var docId : String,
    var nome : String,
    var Bloco : String,
    var Apartamento : String,
    var Garagem : String,
    var Contato : String,
    var Pet : Boolean

) {
    constructor() : this(0,"", "", "", "","","",false)
}

