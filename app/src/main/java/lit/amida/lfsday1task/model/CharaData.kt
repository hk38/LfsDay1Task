package lit.amida.lfsday1task.model

import com.google.gson.annotations.SerializedName

data class CharaData(
    val game: String,
    @SerializedName("pc_name") val name: String,
    @SerializedName("pc_codename") val codeName: String,
    @SerializedName("NP5") val hp: Int,
    @SerializedName("NP6") val erosion: Int,
    @SerializedName("NP7") val initiative: Int,
    val money: Int,
    @SerializedName("roice_name") val roiceName: List<String>,
    @SerializedName("roice_pos") val roicePos: List<String>,
    @SerializedName("roice_neg") val roiceNeg: List<String>,
    @SerializedName("roice_memo") val roiceMemo: List<String>,
    @SerializedName("data_id") val id:Int
    )