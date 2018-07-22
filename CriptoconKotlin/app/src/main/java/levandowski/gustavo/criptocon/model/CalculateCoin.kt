package levandowski.gustavo.criptocon.model

/**
 * Created by glevandowski on 02/07/18.
 */
class CalculateCoin(quote:Double = 0.0,priceInput:Double = 0.0) {
    var quote = 0.0
    var priceInput = 0.0

    init {
     this.quote = quote
     this.priceInput = priceInput
    }

    fun formula() = priceInput / quote


}