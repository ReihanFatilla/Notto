package com.reihan.notto.data

import androidx.lifecycle.LiveData
import com.reihan.notto.data.local.Notto
import com.reihan.notto.data.local.NottoDAO

class NottoRepository(private val dao: NottoDAO) {

    val getDataNottoRepo: LiveData<List<Notto>> = dao.getAllNotto()

    suspend fun insertNottoRepo(notto: Notto){
        dao.insertNotto(notto)
    }

    suspend fun updateNottoRepo(notto: Notto){
        dao.updateNotto(notto)
    }

    suspend fun deleteNottoRepo(notto: Notto){
        dao.deleteNotto(notto)
    }

    fun searchNottoByQueryRepo(query: String) : LiveData<List<Notto>> {
        return dao.searchNottoByQuery(query)
    }

    suspend fun deleteAllNottoRepo(){
        dao.deleteAllNotto()
    }
}