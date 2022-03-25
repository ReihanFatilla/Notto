package com.reihan.notto.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.reihan.notto.data.NottoRepository
import com.reihan.notto.data.local.Notto
import com.reihan.notto.data.local.NottoDAO
import com.reihan.notto.data.local.NottoDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NottoViewModel(application: Application): AndroidViewModel(application) {
    private val nottoDAO: NottoDAO = NottoDB.invoke(application).nottoDao
    private val repository: NottoRepository = NottoRepository(nottoDAO)

    val getDataNotto: LiveData<List<Notto>> = repository.getDataNottoRepo

    fun insertNotto(notto: Notto){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNottoRepo(notto)
        }
    }

    fun deleteNotto(notto: Notto){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNottoRepo(notto)
        }
    }

    fun  updateNotto(notto: Notto){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateNottoRepo(notto)
        }
    }

    fun deleteAllNotto(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllNottoRepo()
        }
    }
}