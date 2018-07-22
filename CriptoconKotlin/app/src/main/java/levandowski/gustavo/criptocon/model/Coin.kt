package levandowski.gustavo.criptocon.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import levandowski.gustavo.criptocon.model.parameters.QuotesModelParameters
import java.text.NumberFormat

/**
 * Created by glevandowski on 30/06/18.
 */
open class Coin():Parcelable {
    @SerializedName("id")
    var idCoin: String = ""

    @SerializedName("name")
    var name: String = ""

    @SerializedName("symbol")
    var symbol: String = ""

    @SerializedName("website_slug")
    var websiteSlug: String = ""

    @SerializedName("rank")
    var rank: String = ""

    @SerializedName("circulating_supply")
    var circulatingSupply: String = ""

    @SerializedName("total_supply")
    var totalSupply: String = ""

    @SerializedName("max_supply")
    var maxSupply: String = ""

    @SerializedName("quotes")
    var quotes: QuotesModelParameters = QuotesModelParameters()

    @SerializedName("last_updated")
    var lastUpdated: String = ""

    constructor(id: String, name: String, symbol: String, websiteSlug: String, rank: String, circulatingSupply: String, totalSupply: String, maxSupply: String, quotesModelParameters: QuotesModelParameters, lastUpdated: String) : this() {
        this.idCoin = id
        this.name = name
        this.symbol = symbol
        this.websiteSlug = websiteSlug
        this.rank = rank
        this.circulatingSupply = circulatingSupply
        this.totalSupply = totalSupply
        this.maxSupply = maxSupply
        this.quotes = quotesModelParameters
        this.lastUpdated = lastUpdated

    }

    //region Métodos responsáveis por preparar a classe para ser utilizada em bundle
    constructor(parcel: Parcel) : this() {
        idCoin = parcel.readString()
        name = parcel.readString()
        symbol = parcel.readString()
        websiteSlug = parcel.readString()
        rank = parcel.readString()
        circulatingSupply = parcel.readString()
        totalSupply = parcel.readString()
        maxSupply = parcel.readString()
        lastUpdated = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idCoin)
        parcel.writeString(name)
        parcel.writeString(symbol)
        parcel.writeString(websiteSlug)
        parcel.writeString(rank)
        parcel.writeString(circulatingSupply)
        parcel.writeString(totalSupply)
        parcel.writeString(maxSupply)
        parcel.writeString(lastUpdated)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Coin> {
        override fun createFromParcel(parcel: Parcel): Coin {
            return Coin(parcel)
        }

        override fun newArray(size: Int): Array<Coin?> {
            return arrayOfNulls(size)
        }
    }
    //endregion

}