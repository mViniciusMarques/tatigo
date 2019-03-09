package solutions.empire42.tatianego.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.fragment_recado.*
import org.jetbrains.anko.doAsync

import solutions.empire42.tatianego.R
import solutions.empire42.tatianego.core.UserSharedPreferenceManager
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import android.view.WindowManager



class RecadoFragment : Fragment() {

    private var userManager: UserSharedPreferenceManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recado, container, false)
    }

    override fun onStart() {
        super.onStart()
        userManager = UserSharedPreferenceManager(context)
        val recadoQuery = ParseQuery.getQuery<ParseObject>("Recado")
            recadoQuery.orderByDescending("createdAt")
        val descricao = recadoQuery.first.getString("descricao")
        if(!descricao.isNullOrBlank()) {
            recado_para_exibir.text = descricao
        }

        acaoSalvarRecado()

//        if(userManager!!.loggedUser.email != "tati@stefanini.com") {
//            layout_input_recado.visibility = View.GONE
//        }

        activity!!.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)


    }

    fun salvarRecado() {
        if(input_recado.text.isNotBlank()) {
            val produto = ParseObject("Recado")
            produto.put("descricao", input_recado.text.toString())
            produto.put("user_id", userManager!!.loggedUser)
            produto.put("ativo", true)
            produto.saveInBackground().onSuccess {
                Toast.makeText(context, "Recado cadastrado com sucesso", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Insira algum recado", Toast.LENGTH_SHORT).show()
        }
    }

    fun acaoSalvarRecado() {
        btn_input_recado.setOnClickListener {
            salvarRecado()
            enviarPush(input_recado.text.toString())
        }
    }

    private fun enviarPush(mensagem: String) {

        doAsync {

            try {
                val jsonResponse: String

                val url = URL("https://onesignal.com/api/v1/notifications")
                val con = url.openConnection() as HttpURLConnection
                con.useCaches = false
                con.doOutput = true
                con.doInput = true

                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
                con.setRequestProperty("Authorization", "Basic MzYzYmViNmUtMGNjOC00YThkLTg2MDQtNGE0ODdkMzQ5MTgw")
                con.requestMethod = "POST"

                val strJsonBody = ("{"
                        + "\"app_id\": \"845cdfda-0af9-4f94-893f-dee4d6e095d4\","
                        + "\"included_segments\": [\"All\"],"
                        + "\"data\": {\"foo\": \"bar\"},"
                        + "\"contents\": {\"en\": \" " + "Olá " +  userManager?.loggedUser?.username + ", "  + mensagem + "\"}"
                        + "}")


                println("strJsonBody:\n$strJsonBody")

                val sendBytes = strJsonBody.toByteArray(charset("UTF-8"))
                con.setFixedLengthStreamingMode(sendBytes.size)

                val outputStream = con.outputStream
                outputStream.write(sendBytes)

                val httpResponse = con.responseCode
                println("httpResponse: $httpResponse")

                if (httpResponse >= HttpURLConnection.HTTP_OK && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                    val scanner = Scanner(con.inputStream, "UTF-8")
                    jsonResponse = if (scanner.useDelimiter("\\A").hasNext()) scanner.next() else ""
                    scanner.close()
                } else {
                    val scanner = Scanner(con.errorStream, "UTF-8")
                    jsonResponse = if (scanner.useDelimiter("\\A").hasNext()) scanner.next() else ""
                    scanner.close()
                }
                println("jsonResponse:\n$jsonResponse")

            } catch (t: Throwable) {
                t.printStackTrace()
            }
        }
    }


}
