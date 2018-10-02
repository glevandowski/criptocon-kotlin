package levandowski.gustavo.criptocon.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import levandowski.gustavo.criptocon.R
import levandowski.gustavo.criptocon.adapter.ConverterAdapter
import levandowski.gustavo.criptocon.listeners.ItemPositionListener
import levandowski.gustavo.criptocon.network.model.CalculateCoin
import levandowski.gustavo.criptocon.network.model.Coin
import android.widget.EditText
import levandowski.gustavo.criptocon.listeners.DataDialogListener
import levandowski.gustavo.criptocon.util.MaskPrice
import levandowski.gustavo.criptocon.ui.BaseFragment
import kotlin.collections.ArrayList

class ConvertCoinsFragment : BaseFragment(),ItemPositionListener , SearchView.OnQueryTextListener,DataDialogListener{

    lateinit var reciclerView: RecyclerView;
    lateinit var adapterCoin: ConverterAdapter
    lateinit var etQuotes:EditText
    lateinit var etPriceInput:EditText
    lateinit var textSelectCoin:TextView
    lateinit var searchCoins:SearchView
    lateinit var buttonCalculate:Button
    lateinit var calculateCoin:CalculateCoin
    var quote = 00.00
    var input = 00.00

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view:View = inflater.inflate(R.layout.fragment_converter_coins, container, false)
        this.findViews(view)
        this.init()
        this.setupAdapter()
        this.setupRecyclerViewLayout()
        this.setupSearchView()
        this.actionEvents()
        this.setMask()
        return view
    }

    fun setMask() = etPriceInput.addTextChangedListener(MaskPrice(etPriceInput))

    override fun setPosition(position: Int,coinArrayList:ArrayList<Coin>) {
       etQuotes.setText(coinArrayList.get(position).quotes.brl.getFormatPrice())
       textSelectCoin.setText("${coinArrayList.get(position).name}")
       textSelectCoin.visibility = View.VISIBLE

        quote = coinArrayList.get(position).quotes.brl.price.toDouble()
    }

    override fun setTextDialog(textAnswer: TextView) {
        textAnswer.setText("${calculateCoin.formula(quote,input)} de Criptomoedas")
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
    }

    fun actionEvents() = buttonCalculate.setOnClickListener {
        if (isEmptyData()) {
            appDialogs.showDialog("Ops...faltou alguns dados","1. Selecione uma moeda\n2. Informe o valor a ser investido","Fechar")
        }else {
            input = CalculateCoin().formattt(etPriceInput.text.toString())

            val dialog = DialogResponseConvert(this)
            dialog.show(fragmentManager,"dialogAlert()")
        }
    }

    fun setupAdapter() {
        adapterCoin = ConverterAdapter(activity, coinArrayList,this);
        reciclerView.setAdapter(adapterCoin);
    }

    fun setupRecyclerViewLayout() {
        reciclerView.setHasFixedSize(true);
        reciclerView.setLayoutManager( LinearLayoutManager(activity));
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

    fun isEmptyData() = (etPriceInput.text.toString().isEmpty() || quote == 0.0 || etPriceInput.text.toString().equals(activity?.getString(R.string.third_hint_valor)))
}
