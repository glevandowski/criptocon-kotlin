package levandowski.gustavo.criptocon.model

/**
 * Created by glevandowski on 02/07/18.
 */
class CalculateCoin{

    fun formula(quote: Double, priceInput: Double):Double = ((priceInput*1) / quote)

    fun formattt(format:String):Double {
        if (format.contains(".") && format.contains(",")) {
            val auxFormat:String = Regex("^[R$]$").replace(format.replace("R","").
                    replace("$","").replace(".", ""),"").trim()
           return Regex("^[R$]$").replace(auxFormat.replace("R","").
                   replace("$","").replace(",", "."),"").trim().toDouble()
        } else {
            return Regex("^[R$]$").replace(format.replace("R","").
                    replace("$","").replace(",", "."),"").trim().toDouble()
        }
    }
}//TODO problema com numeros digitados do exemplo R$ 1.000,00