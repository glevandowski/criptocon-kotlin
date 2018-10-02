package levandowski.gustavo.criptocon.listeners

import levandowski.gustavo.criptocon.network.model.Coin

/**
 * Created by glevandowski on 01/07/18.
 */
interface ItemPositionListener {
    fun setPosition(position:Int,coinArrayList:ArrayList<Coin>)
}