package com.app.condominioapp.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.lang.reflect.Constructor
import javax.inject.Inject

class MoradorRepositoryFirebase @Inject constructor(val moradoresRef : CollectionReference): MoradorRepository {

    private var _moradores = MutableStateFlow(listOf<Morador>())

    override val moradores: Flow<List<Morador>> get() = _moradores.asStateFlow()

    init {
        moradoresRef.addSnapshotListener { snapshot, _ ->
            if (snapshot == null){
                _moradores = MutableStateFlow(listOf())
            }else {
                var moradores = mutableListOf<Morador>()
                snapshot.documents.forEach{ doc ->
                    val morador = doc.toObject<Morador>()
                    if (morador != null){
                        morador.docId = doc.id
                        moradores.add(morador)
                    }
                }
                _moradores.value = moradores
            }
        }
    }

    override suspend fun salvar(morador: Morador) {

        if (morador.docId.isNullOrEmpty()){
            var doc = moradoresRef.document()
            morador.docId = doc.id
            doc.set(morador)
        }else{
            moradoresRef.document(morador.docId).set(morador)
        }
    }

    override suspend fun excluir(morador: Morador) {
        moradoresRef.document(morador.docId).delete()
    }
}
