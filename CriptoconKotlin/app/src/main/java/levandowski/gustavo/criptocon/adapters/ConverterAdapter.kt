package levandowski.gustavo.criptocon.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.Toast
import levandowski.gustavo.criptocon.R
import levandowski.gustavo.criptocon.adapters.holder.ConverterHolder
import levandowski.gustavo.criptocon.listeners.ItemPositionListener
import levandowski.gustavo.criptocon.model.Coin

/**
 * Created by glevandowski on 01/07/18.
 */
class ConverterAdapter(context: Context, coinArrayList:ArrayList<Coin>,listener:ItemPositionListener): RecyclerView.Adapter<ConverterHolder>() , Filterable {
    var coinArrayList:ArrayList<Coin>
    var list: ArrayList<Coin>
    var context: Context
    var listener:ItemPositionListener
    var results:ArrayList<Coin> = ArrayList()

     init {
         this.coinArrayList = coinArrayList
         this.context = context
         this.listener = listener
         this.list = coinArrayList
      }

    override fun getItemCount(): Int {
        return coinArrayList.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConverterHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.card_converter_fragment, parent, false);
        val holderCoins = ConverterHolder(view);
        return holderCoins;
    }

    override fun onBindViewHolder(holder: ConverterHolder, position: Int) {
        setText(holder,position)

        /***
         * pegando a position
         */
        holder.itemView.tag = position
        holder.itemView.setOnClickListener {
                if(position < results.size ) {
                    listener.setPosition(position, results)
                }else{
                    listener.setPosition(position,coinArrayList)
                }
        }
    }

    /***
     * simplificando toast
     */
    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast {
        return Toast.makeText(context, this.toString(), duration).apply { show() }
    }

    fun setText(holder: ConverterHolder, position: Int){
        holder.coinName.setText(coinArrayList.get(position).name)

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val resultFilter = FilterResults()
                results = ArrayList<Coin>()

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