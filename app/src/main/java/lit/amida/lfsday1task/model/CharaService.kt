package lit.amida.lfsday1task.model

import retrofit2.http.GET
import retrofit2.http.Path

interface CharaService {
    @GET("{id}.js")
    suspend fun getCharacter(@Path("id") id: String): CharaData
}