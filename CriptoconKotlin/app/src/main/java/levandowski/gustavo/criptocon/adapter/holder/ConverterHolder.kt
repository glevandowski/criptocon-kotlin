package levandowski.gustavo.criptocon.adapter.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import levandowski.gustavo.criptocon.R

/**
 * Created by glevandowski on 01/07/18.
 */
open class ConverterHolder(holder: View): RecyclerView.ViewHolder(holder) {
    var coinName: TextView = holder.findViewById(R.id.coin_answer_view_name_converter_fragment);
}