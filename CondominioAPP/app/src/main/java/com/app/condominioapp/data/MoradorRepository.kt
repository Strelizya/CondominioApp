package com.app.condominioapp.data

import android.security.identity.AccessControlProfileId
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface MoradorRepository {

    val moradores : Flow<List<Morador>>
    suspend fun salvar(morador: Morador)
    suspend fun excluir(morador: Morador)

}

