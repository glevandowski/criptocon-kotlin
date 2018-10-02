package levandowski.gustavo.criptocon.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by glevandowski on 08/07/18.
 */
class Status(){

    @SerializedName("timestamp")
    lateinit var timeStamp:String

    @SerializedName("error_code")
    lateinit var errorCode:String

    @SerializedName("error_message")
    lateinit var errorMessage:String

    @SerializedName("elapsed")
    lateinit var elapsed:String

    @SerializedName("credit_count")
    lateinit var creditCount:String

    constructor(timeStamp:String,
                errorCode:String,
                errorMessage:String,
                elapsed:String,
                creditCount:String):this(){
        this.timeStamp = timeStamp
        this.errorCode = errorCode
        this.errorMessage = errorMessage
        this.elapsed = elapsed
        this.creditCount = creditCount
    }
}