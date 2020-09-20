package lit.amida.lfsday1task.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import lit.amida.lfsday1task.R
import lit.amida.lfsday1task.databinding.ItemCharaDataBinding
import lit.amida.lfsday1task.model.CharaData

class Adapter(private val context: Context): RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(val binding: ItemCharaDataBinding): RecyclerView.ViewHolder(binding.root)

    private var items: List<CharaData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_chara_data, parent, false) as ItemCharaDataBinding
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.model = items?.get(position)
        holder.binding.executePendingBindings()
    }

    fun setItems(items: List<CharaData>){
        Log.d("setItem", "call!!")
        if(this.items == null){
            this.items = items
            notifyItemRangeChanged(0, items.size)
        }else{
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback(){
                override fun getOldListSize(): Int {
                    return requireNotNull(this@Adapter.items).size
                }

                override fun getNewListSize(): Int {
                    return items.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldItems = this@Adapter.items
                    return oldItems?.get(oldItemPosition)?.id == items[newItemPosition].id
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldItems = this@Adapter.items
                    return oldItems?.get(oldItemPosition)?.id == items[newItemPosition].id
                }
            })
            this.items = items
            result.dispatchUpdatesTo(this)
        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }
}