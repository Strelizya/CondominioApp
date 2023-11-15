package com.app.condominioapp.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoradorRepositorySqlite @Inject constructor(val moradorDAO: MoradorDAO){
    val moradores : Flow<List<Morador>> get() = moradorDAO.listar()

    suspend fun salvar(morador: Morador){
        if (morador.id == 0){
            moradorDAO.inserir(morador)
        } else {
            moradorDAO.atualizar(morador)
        }
    }


    suspend fun excluir(morador: Morador){
        moradorDAO.excluir(morador)
    }


//    companion object{
//        fun moradores(): MutableList<Morador>{
//            val lista = mutableListOf(
//                Morador(
//                    1,
//                    "Batata",
//                    "2",
//                    "103",
//                    "02"
//                )
//            )
//            return lista
//        }
//    }
}