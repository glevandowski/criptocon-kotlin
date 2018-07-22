package levandowski.gustavo.criptocon.adapters

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import levandowski.gustavo.criptocon.R
import levandowski.gustavo.criptocon.adapters.holder.CoinHolder
import levandowski.gustavo.criptocon.model.Coin
import android.widget.Toast
import levandowski.gustavo.criptocon.views.MainActivity
import kotlin.collections.ArrayList


/**
 * Created by glevandowski on 30/06/18.
 */
class CoinAdapter(context: Context, coinArrayList:ArrayList<Coin>): RecyclerView.Adapter<CoinHolder>() ,Filterable{
    var coinArrayList:ArrayList<Coin>
    var list: ArrayList<Coin>
    var context: Context

    init {
        this.coinArrayList = coinArrayList
        this.context = context
        this.list = coinArrayList
    }

    override fun getItemCount(): Int {
        return coinArrayList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinHolder {
       val view:View = LayoutInflater.from(context).inflate(R.layout.card_coins_quotes_fragment, parent, false);
       val holderCoins = CoinHolder(view);
       return holderCoins;
    }

    override fun onBindViewHolder(holder: CoinHolder, position: Int) {
        if(!isEmpty(coin = coinArrayList.get(position),holder = holder)) return
        verifySizePercentsOne(coinArrayList.get(position),holder.coinPriceOneHour,holder.percentsOne)
        verifyPercentsOneDay( coinArrayList.get(position),holder.coinPriceTwentyHour,holder.percentsTwenty)
        setText(holder,coinArrayList.get(position))

        /***
         * pegando a position
         */
        holder.itemView.tag = position
        holder.itemView.setOnClickListener {
//            "ola ${coinArrayList.get(position).name}".toast(context)
        }
    }

    /***
     * simplificando toast
     */
    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }

    fun setText(holder: CoinHolder,coin: Coin){
        if(!isEmpty(coin = coin,holder = holder)) return
            holder.coinName.setText(coin.name)
            holder.coinQuotes.setText(coin.quotes.brl.getFormatPrice())
            holder.coinPriceOneHour.setText(coin.quotes.brl.percentChangeOneHour)
            holder.coinPriceTwentyHour.setText(coin.quotes.brl.percentChangeTwentyHour)

    }

    fun isEmpty(coin:Coin,holder: CoinHolder):Boolean{
        if (coin.name.equals(null)) {
            viewIsEmpty(holder.coinName)
            return false
        } else if(coin.quotes.brl.getFormatPrice().equals(null)){
            viewIsEmpty(holder.coinQuotes)
            return false
        } else if(coin.quotes.brl.percentChangeOneHour.equals(null)){
            viewIsEmpty(holder.coinPriceOneHour,holder.percentsOne)
            return false
        } else if(coin.quotes.brl.percentChangeTwentyHour.equals(null)){
            viewIsEmpty(holder.coinPriceTwentyHour,holder.percentsTwenty)
            return false
        } else{
            return true
        }
     }
    fun verifySizePercentsOne(coin:Coin,value:TextView,aux:TextView) {
        if (coin.quotes.brl.percentChangeOneHour.toDouble() == 0.0) {
            setColor(value, aux,Color.BLACK)

        } else if (coin.quotes.brl.percentChangeOneHour.toDouble() > 0.0) {

            setColor(value,aux,Color.GREEN)

        } else if (coin.quotes.brl.percentChangeOneHour.toDouble() < 0.0) {

           setColor(value,aux,Color.RED)
        }
    }

    fun verifyPercentsOneDay(coin:Coin,value: TextView,aux: TextView){

        if (coin.quotes.brl.percentChangeTwentyHour.toDouble() == 0.0) {

           setColor(value,aux,Color.BLACK)

        } else if(coin.quotes.brl.percentChangeTwentyHour.toDouble() > 0.0){

            setColor(value,aux,Color.GREEN)
        } else if(coin.quotes.brl.percentChangeTwentyHour.toDouble() < 0.0){

             setColor(value,aux,Color.RED)
        }
    }

    fun setColor(value:TextView,aux:TextView,color:Int){
        value.setTextColor(color)
        aux.setTextColor(color)
    }

    fun viewIsEmpty(value:TextView, aux:TextView ?= null){
        value.setText("Sem informação")
        value.setTextColor(Color.BLACK)
        if(aux != null){
         aux.visibility = View.INVISIBLE
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val resultFilter = FilterResults()
                val results = ArrayList<Coin>()

                if (constraint != null) {

                    if (list.size > 0) {
                        for (index in list) {
                            if (index.name.toLowerCase().contains(constraint.toString())
                                || index.name.contains(constraint.toString()))
                                results.add(index)
                        }
                    }
                    resultFilter.values = results
                }

                return resultFilter
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                coinArrayList = results.values as ArrayList<Coin>
                notifyDataSetChanged()
            }

        }
    }
}

