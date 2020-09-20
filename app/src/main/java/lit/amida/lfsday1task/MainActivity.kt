package lit.amida.lfsday1task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import lit.amida.lfsday1task.model.CharaService
import lit.amida.lfsday1task.model.Character
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val idList: MutableList<String> = mutableListOf()
    var service: CharaService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://charasheet.vampire-blood.net")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        service = retrofit.create(CharaService::class.java)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = Adapter(this)
        recyclerView.adapter = adapter

        buttonGet.setOnClickListener {
            val id = editUrl.text.toString()
            val data = getData(id) ?: return@setOnClickListener
            editUrl.text.clear()
            
            if(data.game == "dx3"){
                adapter.add(data)
                idList.add(id)
            }
        }

        fab.setOnClickListener {
            adapter.items.clear()
            idList.forEach{
                val data = getData(it)
                if(data != null) adapter.add(data)
            }
        }
    }

    fun getData(id: String): Character?{
        runBlocking(Dispatchers.IO){
            kotlin.runCatching { service?.getCharacter(id) }
        }.onSuccess { return it }

        return null
    }
}