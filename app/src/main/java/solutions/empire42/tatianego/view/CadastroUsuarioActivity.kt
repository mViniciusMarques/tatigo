package solutions.empire42.tatianego.view

import android.animation.ObjectAnimator
import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import solutions.empire42.tatianego.R
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_cadastro_usuario.*
import com.parse.ParseUser
import solutions.empire42.tatianego.MainActivity


class CadastroUsuarioActivity : AppCompatActivity() {

    var languages = arrayOf("English", "French", "Spanish", "Hindi", "Russian", "Telugu", "Chinese", "German", "Portuguese", "Arabic", "Dutch", "Urdu", "Italian", "Tamil", "Persian", "Turkish", "Other")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario)
        supportActionBar!!.hide()
        textView4.visibility = View.GONE;


        val handle = Handler()
        handle.postDelayed(    {
            this.doSignUp()
        }  , 3000)


        btn_cadastrar_usuario.setOnClickListener {
            if( cadastro_nome.text.isNotBlank() && cadastro_login.text.isNotBlank() && cadastro_senha.text.isNotBlank() ) {
                this.cadastrarUsuario()
            }
        }

    }


    fun doSignUp() {

        ObjectAnimator.ofFloat(myCardView, "translationY", -160f).apply {
            duration = 2000
            start()
        }
        val handle = Handler()
        handle.postDelayed(    {
            this.doSignUp()
            textView4.visibility = View.VISIBLE;
        }  , 2000)
    }

    fun cadastrarUsuario() {
        val user = ParseUser()
        user.username = cadastro_nome.text.toString()
        user.setPassword(cadastro_senha.text.toString())
        user.email = cadastro_login.text.toString()

        // other fields can be set just like with ParseObject
       // user.put("phone", "650-253-0000")

        user.signUpInBackground { e ->
            if (e == null) {
                // Hooray! Let them use the app now.
                startActivity(Intent(this@CadastroUsuarioActivity, MainActivity::class.java))
            } else {
                Toast.makeText(this@CadastroUsuarioActivity, "Problema ao registrar usuári, verifique sua rede", Toast.LENGTH_SHORT).show()
                // Sign up didn't succeed. Look at the ParseException
                // to figure out what went wrong
            }
        }

    }
}
