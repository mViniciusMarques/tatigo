package solutions.empire42.tatianego.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.parse.ParseUser
import kotlinx.android.synthetic.main.fragment_meu_perfil.*

import solutions.empire42.tatianego.R
import solutions.empire42.tatianego.view.LoginActivity
import com.parse.ParsePush
import org.json.JSONException
import org.json.JSONObject





class MeuPerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_meu_perfil, container, false)

        return view
    }

    override fun onResume() {
        super.onResume()
        logOutUser()
        getUserInformation()
    }

    private fun logOutUser() {
        btn_logout.setOnClickListener {
    //        ParseUser.logOut()
     //       startActivity(Intent(context, LoginActivity::class.java))

//
            val push = ParsePush()
            push.setChannel("Bolo de Cenoura")
            push.setMessage("Bolo de cenoura pronto !")
            push.sendInBackground()

//            val data = JSONObject()
//// Put data in the JSON object
//            try {
//                data.put("alert", "Back4App Rocks!")
//                data.put("title", "Hello from Device")
//            } catch (e: JSONException) {
//                // should not happen
//                throw IllegalArgumentException("unexpected parsing error", e)
//            }
//
//// Configure the push
//            val push = ParsePush()
//            push.setChannel("Bolo de Cenoura")
//            push.setData(data)
//            push.sendInBackground()
        }
    }

    private fun getUserInformation() {
        val user = ParseUser.getCurrentUser()
        perfil_username.text = user.username
        perfil_email.text = user.email
        perfil_numero.text = "50 Produtos adquiridos"
    }




}
