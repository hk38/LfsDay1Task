package lit.amida.lfsday1task.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {
    companion object Factory{
        val instance: Repository
            @Synchronized get(){
                return Repository()
            }
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://charasheet.vampire-blood.net")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val charaService = retrofit.create(CharaService::class.java)

    suspend fun getCharacter(id: String): CharaData = charaService.getCharacter(id)

    suspend fun getCharacterList(ids: List<Int>): MutableList<CharaData>{
        val charaList: MutableList<CharaData> = mutableListOf()

        ids.forEach {
            charaList.add(charaService.getCharacter(it.toString()))
        }

        return charaList
    }
}