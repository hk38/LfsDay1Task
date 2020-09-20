package lit.amida.lfsday1task

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import lit.amida.lfsday1task.model.Character

class Adapter(private val context: Context): RecyclerView.Adapter<Adapter.ViewHolder>() {
    class ViewHolder(v: View): RecyclerView.ViewHolder(v){
        val textName: TextView = v.findViewById(R.id.textName)
        val textCodename: TextView = v.findViewById(R.id.textCodename)
        val textHp: TextView = v.findViewById(R.id.textHp)
        val textErosion: TextView = v.findViewById(R.id.textErosion)
        val textInitiative: TextView = v.findViewById(R.id.textInitiative)
        val textMoney: TextView = v.findViewById(R.id.textMoney)

        val roiceNameList: List<TextView> = listOf(v.findViewById(R.id.textRoice1Name), v.findViewById(R.id.textRoice2Name), v.findViewById(R.id.textRoice3Name), v.findViewById(R.id.textRoice4Name), v.findViewById(R.id.textRoice5Name), v.findViewById(R.id.textRoice6Name), v.findViewById(R.id.textRoice7Name))
        val roicePosNegList: List<TextView> = listOf(v.findViewById(R.id.textRoice1PosNeg), v.findViewById(R.id.textRoice2PosNeg), v.findViewById(R.id.textRoice3PosNeg), v.findViewById(R.id.textRoice4PosNeg), v.findViewById(R.id.textRoice5PosNeg), v.findViewById(R.id.textRoice6PosNeg), v.findViewById(R.id.textRoice7PosNeg))
        val roiceMemoList: List<TextView> = listOf(v.findViewById(R.id.textRoice1Memo), v.findViewById(R.id.textRoice2Memo), v.findViewById(R.id.textRoice3Memo), v.findViewById(R.id.textRoice4Memo), v.findViewById(R.id.textRoice5Memo), v.findViewById(R.id.textRoice6Memo), v.findViewById(R.id.textRoice7Memo))
    }

    val items: MutableList<Character> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item_chara_data, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.textName.text = item.name
        holder.textCodename.text = item.codeName
        holder.textHp.text = "HP:${item.hp}"
        holder.textErosion.text = "侵蝕率:${item.erosion}"
        holder.textInitiative.text = "行動値:${item.initiative}"
        holder.textMoney.text = "財産点:${item.money}"

        for(i in 0..6){
            if(item.roiceName[i].isEmpty()){
                holder.roiceNameList[i].visibility = View.GONE
                holder.roicePosNegList[i].visibility = View.GONE
                holder.roiceMemoList[i].visibility = View.GONE
            }else holder.roiceNameList[i].text = item.roiceName[i]

            if(item.roicePos[i].isEmpty() || item.roiceNeg[i].isEmpty()) holder.roicePosNegList[i].text = "${item.roicePos[i]}${item.roiceNeg[i]}"
            else holder.roicePosNegList[i].text = "${item.roicePos[i]}/${item.roiceNeg[i]}"

            if(item.roiceMemo[i].isEmpty()) holder.roiceMemoList[i].visibility = View.INVISIBLE
            else holder.roiceMemoList[i].text = item.roiceMemo[i]
        }
    }

    fun add(item: Character){
        this.items.add(item)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}