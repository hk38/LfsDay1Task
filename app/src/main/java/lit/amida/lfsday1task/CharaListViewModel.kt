package lit.amida.lfsday1task

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import lit.amida.lfsday1task.model.CharaData
import lit.amida.lfsday1task.model.Repository
import java.lang.Exception

class CharaListViewModel(app: Application): AndroidViewModel(app) {
    private val repository = Repository.instance
    val liveData: MutableLiveData<MutableList<CharaData>> = MutableLiveData()

    fun loadCharacter(id: String) = viewModelScope.launch {
        try{
            val response = repository.getCharacter(id)

            if(response.game != "dx3") return@launch

            val list: MutableList<CharaData> = liveData.value ?: mutableListOf()
            list.add(response)
            liveData.postValue(list)
            Log.d("loadCharacter", "${response.id}, ${response.name}")
        }catch (e: Exception){ e.printStackTrace() }
    }

    fun loadCharacters() = viewModelScope.launch {
        try{
            val result = liveData.value
            val idSet = mutableSetOf<Int>()

            result?.forEach{ data ->
                idSet.add(data.id)
            }

            Log.d("loadCharacters", "$idSet")
            liveData.postValue(repository.getCharacterList(idSet.toList()))
        }catch (e: Exception){}
    }
}