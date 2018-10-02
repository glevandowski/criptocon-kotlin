package levandowski.gustavo.criptocon.network

import android.app.Activity
import android.content.Intent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import android.util.Log
import levandowski.gustavo.criptocon.ui.activity.FailureActivity

class HttpRequest {
    companion object {

        fun interceptorAPI(activity:Activity) : OkHttpClient {
            val intent = Intent(activity, FailureActivity::class.java)
            val flagBundle = "Fail"
            var failMessage:String

            val okHttpClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
                        val request = chain.request()
                        val response = chain.proceed(request)

                when (response.code()) {
                    400-> {
                        failMessage = "Pedido incorreto"
                        intent.putExtra(flagBundle,failMessage)
                        activity.finish()
                        activity.startActivity(intent)
                        Log.d("HttpRequest",failMessage)
                        return@Interceptor response
                    }
                    401 -> {
                        failMessage = "Acesso não autorizado no servidor"
                        intent.putExtra(flagBundle,failMessage)
                        activity.finish()
                        activity.startActivity(intent)
                        Log.d("HttpRequest",failMessage)
                        return@Interceptor response
                    }
                    403 -> {
                        failMessage = "Acesso negado"
                        intent.putExtra(flagBundle,failMessage)
                        activity.finish()
                        activity.startActivity(intent)
                        Log.d("HttpRequest", failMessage)
                        return@Interceptor response
                    }
                    404 -> {
                        failMessage = "Não foi possivel realizar acesso ao servidor"
                        intent.putExtra(flagBundle,failMessage)
                        activity.finish()
                        activity.startActivity(intent)
                        Log.d("HttpRequest", failMessage)
                        return@Interceptor response
                    }
                    429 -> {
                        failMessage = "Requisições acima do limite"
                        intent.putExtra(flagBundle,failMessage)
                        activity.finish()
                        activity.startActivity(intent)
                        Log.d("HttpRequest",failMessage)
                        return@Interceptor response
                    }
                    500 -> {
                        failMessage = "Erro interno no servidor"
                        intent.putExtra(flagBundle,failMessage)
                        activity.finish()
                        activity.startActivity(intent)
                        Log.d("HttpRequest",failMessage)
                        return@Interceptor response
                    }
                }
                response
                    }).build()

             return okHttpClient;
        }
    }
}
