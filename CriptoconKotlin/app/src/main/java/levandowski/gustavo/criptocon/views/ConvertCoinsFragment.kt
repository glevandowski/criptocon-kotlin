package levandowski.gustavo.criptocon.views

import android.content.Context
import android.os.Bundle
import android.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import levandowski.gustavo.criptocon.R
import levandowski.gustavo.criptocon.adapters.ConverterAdapter
import levandowski.gustavo.criptocon.listeners.ItemPositionListener
import levandowski.gustavo.criptocon.model.CalculateCoin
import levandowski.gustavo.criptocon.model.Coin
import levandowski.gustavo.criptocon.util.ManageKeyboard
import android.widget.EditText
import levandowski.gustavo.criptocon.listeners.DataDialogListener
import levandowski.gustavo.criptocon.util.MaskPrice
import kotlin.collections.ArrayList


class ConvertCoinsFragment : Fragment(),ItemPositionListener , SearchView.OnQueryTextListener,DataDialogListener{

    lateinit var reciclerView: RecyclerView;
    lateinit var adapterCoin: ConverterAdapter
    lateinit var etQuotes:EditText
    lateinit var etPriceInput:EditText
    lateinit var textSelectCoin:TextView
    lateinit var searchCoins:SearchView
    lateinit var buttonCalculate:Button
    lateinit var manageKeyboard:ManageKeyboard
    lateinit var coinArrayList:ArrayList<Coin>
    lateinit var calculateCoin:CalculateCoin

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fragment_converter_coins, container, false)
        this.findViews(view)
        this.init()
        this.receivingBundle()
        this.setupAdapter()
        this.setupRecyclerViewLayout()
        this.setupSearchView()
        this.actionEvents()
        this.setMask()
        return view
    }

    private fun receivingBundle() {
        val receivingBundle:ArrayList<Coin> = arguments?.getParcelableArrayList<Coin>("KEY") ?:ArrayList()
        receivingBundle.filterIndexed { index, value -> coinArrayList.add(receivingBundle.get(index)) }
    }

    fun setMask() {
        this.requestFocusEditText(etPriceInput)
        etPriceInput.addTextChangedListener(MaskPrice(etPriceInput))
    }

    override fun setPosition(position: Int,coinArrayList:ArrayList<Coin>) {
       etQuotes.setText(coinArrayList.get(position).quotes.brl.getFormatPrice())
       textSelectCoin.setText("${coinArrayList.get(position).name}")
       textSelectCoin.visibility = View.VISIBLE
       requestFocusEditText(etPriceInput)

        calculateCoin.quote = replaceStrings(coinArrayList.get(position).quotes.brl.getFormatPrice()).toDouble()
    }

    override fun setTextDialog(textAnswer: TextView) {
        textAnswer.setText("${calculateCoin.formula()} de Criptomoedas")
    }

    fun findViews(view:View){
        reciclerView = RecyclerView(view.context)
        reciclerView = view.findViewById(R.id.reciclerViewConverterFragment)
        etQuotes = view.findViewById(R.id.third_edit_cotacao)
        textSelectCoin = view.findViewById(R.id.select_coin_text_converter_fragment)
        searchCoins = view.findViewById(R.id.searchViewConversor)
        etPriceInput = view.findViewById(R.id.third_edit_valor)
        buttonCalculate = view.findViewById(R.id.third_btn_calcular)
    }

    fun init(){
        calculateCoin = CalculateCoin()
        coinArrayList = ArrayList()
        manageKeyboard = ManageKeyboard(activity)
    }

    fun actionEvents() = buttonCalculate.setOnClickListener {
        if (isEmptyData()) {
            "Ops... selecione uma moeda e informe o valor a ser investido".toast(activity).show()
        }else {
            calculateCoin.priceInput = replaceStrings(etPriceInput.text.toString()).toDouble()

            val dialog = DialogResponseConvert(this)
            dialog.show(fragmentManager,"dad")
        }
    }

    fun setupAdapter() {
        adapterCoin = ConverterAdapter(activity, coinArrayList,this);
        reciclerView.setAdapter(adapterCoin);
    }

    fun setupRecyclerViewLayout() {
        reciclerView.setHasFixedSize(true);
        var layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity);
        reciclerView.setLayoutManager(layoutManager);
    }

    fun setupSearchView() {
        searchCoins.setIconifiedByDefault(false);
        searchCoins.setOnQueryTextListener(this);
        searchCoins.setSubmitButtonEnabled(true);
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        adapterCoin.getFilter().filter(query)
        return true}

    override fun onQueryTextChange(newText: String?): Boolean {
        adapterCoin.getFilter().filter(newText)
        return true
    }

    fun requestFocusEditText(editText:EditText) {
        if(editText.getText().toString().isEmpty()){
            editText.requestFocus()
            manageKeyboard.showKeyBoard()
        }
    }

    /***
     * simplificando toast
     */
    fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT): Toast =
            Toast.makeText(context, this.toString(), duration).apply { show() }

    fun replaceStrings(format:String):String {
        if (format.contains(".") && format.contains(",")) {
            return Regex("[^0-9,]").replace(format.replace(",", ""),"").trim()
        } else {
            return Regex("[^0-9,]").replace(format.replace(",", "."),"").trim()
        }
    }

    fun isEmptyData() = (etPriceInput.text.toString().isEmpty() ||
            calculateCoin.quote == 0.0 || etPriceInput.text.toString().equals(activity?.getString(R.string.third_hint_valor)))
}
