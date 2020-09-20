package lit.amida.lfsday1task.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import lit.amida.lfsday1task.CharaListViewModel
import lit.amida.lfsday1task.R
import lit.amida.lfsday1task.view.adapter.Adapter

class MainActivity : AppCompatActivity() {
    private val viewModel by lazy { ViewModelProvider(this).get(CharaListViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = Adapter(applicationContext)
        recyclerView.adapter = adapter

        viewModel.liveData.observe(this, {
            adapter.setItems(it)
        })

        buttonGet.setOnClickListener {
            viewModel.loadCharacter(editUrl.text.toString())
        }

        fab.setOnClickListener {
            viewModel.loadCharacters()
        }
    }
}