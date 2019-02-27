package solutions.empire42.tatianego.fragment


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog
import com.parse.ParseObject
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.doAsync
import solutions.empire42.tatianego.R
import solutions.empire42.tatianego.core.UserSharedPreferenceManager
import solutions.empire42.tatianego.view.LoginActivity
import java.net.HttpURLConnection
import java.net.URL
import java.util.*


class HomeFragment : Fragment() {

    var mActivity = Activity()
    private var userManager: UserSharedPreferenceManager?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()
        userManager = UserSharedPreferenceManager(context)
    }

    override fun onResume() {
        super.onResume()
        this.activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
        )

        encomendarBoloDeCenouro()
        encomendarSaladaDeFrutas()
        encomendarChupChup()

    }

    private fun encomendarBoloDeCenouro() {
        btn_encomendar_bolo.setOnClickListener {
            TTFancyGifDialog.Builder(mActivity)
                .setTitle("Bolo de Cenoura")
                .setMessage("Encomendar uma deliciosa fatia de bolo de cenoura.")
                .setPositiveBtnText("Sim")
                .setPositiveBtnBackground("#22b573")
                .setNegativeBtnText("Não")
                .setNegativeBtnBackground("#c1272d")
                .setGifResource(R.drawable.delivery)      //pass your gif, png or jpg

                .isCancellable(true)
                .OnPositiveClicked { adicionarProduto("Bolo de Cenoura") }
                .OnNegativeClicked { Toast.makeText(mActivity, "Cancel", Toast.LENGTH_SHORT).show() }
                .build()
        }
    }

    private fun encomendarSaladaDeFrutas() {
        btn_encomendar_salada.setOnClickListener {
            TTFancyGifDialog.Builder(mActivity)
                .setTitle("Salada de Fruta")
                .setMessage("Encomendar uma deliciosa salada de frutas fresquinha.")
                .setPositiveBtnText("Sim")
                .setPositiveBtnBackground("#22b573")
                .setNegativeBtnText("Não")
                .setNegativeBtnBackground("#c1272d")
                .setGifResource(R.drawable.delivery)      //pass your gif, png or jpg

                .isCancellable(true)
                .OnPositiveClicked { adicionarProduto("Salada De Frutas") }
                .OnNegativeClicked { Toast.makeText(mActivity, "Cancel", Toast.LENGTH_SHORT).show() }
                .build()
        }
    }

    private fun encomendarChupChup() {
        btn_encomendar_chup.setOnClickListener {
            TTFancyGifDialog.Builder(mActivity)
                .setTitle("Salada de Fruta")
                .setMessage("Encomendar chup-chup com um maravilhoso sabor.")
                .setPositiveBtnText("Sim")
                .setPositiveBtnBackground("#22b573")
                .setNegativeBtnText("Não")
                .setNegativeBtnBackground("#c1272d")
                .setGifResource(R.drawable.delivery)      //pass your gif, png or jpg

                .isCancellable(true)
                .OnPositiveClicked { adicionarProduto("Chup Chup") }
                .OnNegativeClicked { Toast.makeText(mActivity, "Cancel", Toast.LENGTH_SHORT).show() }
                .build()
        }
    }

    override fun onAttach(context: Activity?) {
        super.onAttach(context)
         mActivity = context!!
    }

    private fun adicionarProduto(s: String) {
        if (userManager!!.loggedUser != null) {
            enviarPush(s)
            val produto = ParseObject("Encomenda")
            produto.put("tipo_produto", s)
            produto.put("user_id", userManager!!.loggedUser )
            produto.put("ativo", false)
            produto.saveInBackground()
            Toast.makeText(context, "Encomenda realizada com sucesso!", Toast.LENGTH_SHORT).show()
        } else {
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    private fun enviarPush(produto: String) {

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
                        + "\"included_segments\": [\"filterByEmail\"],"
                        + "\"data\": {\"foo\": \"bar\"},"
                        + "\"contents\": {\"en\": \" " + "Olá, " +  userManager?.loggedUser?.username + " está querendo um "  + produto + "\"}"
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
