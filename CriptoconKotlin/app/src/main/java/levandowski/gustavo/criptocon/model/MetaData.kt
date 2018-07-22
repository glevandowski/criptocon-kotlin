package levandowski.gustavo.criptocon.model

import com.google.gson.annotations.SerializedName

/**
 * Created by glevandowski on 08/07/18.
 */
class MetaData(){

    @SerializedName("timestamp")
    lateinit var timeStamp:String

    @SerializedName("error")
    lateinit var error:String

    constructor(timeStamp:String,error:String):this(){
        this.timeStamp = timeStamp
        this.error = error
    }
}