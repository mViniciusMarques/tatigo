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
import solutions.empire42.tatianego.core.UserSharedPreferenceManager


class MeuPerfilFragment : Fragment() {

    private var userManager: UserSharedPreferenceManager  ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_meu_perfil, container, false)

        return view
    }


    override fun onStart() {
        super.onStart()
        userManager = UserSharedPreferenceManager(context)
        logOutUser()
        getUserInformation()
    }

    private fun logOutUser() {
        btn_logout.setOnClickListener {
            ParseUser.logOut()
            userManager?.cleanUpSharedBucked()
            startActivity(Intent(context, LoginActivity::class.java))
        }
    }

    private fun getUserInformation() {
        //val user = ParseUser.getCurrentUser()
        perfil_username.text = userManager?.loggedUser?.username
        perfil_email.text = userManager?.loggedUser?.email
        perfil_numero.text = "50 Produtos adquiridos"
    }




}
