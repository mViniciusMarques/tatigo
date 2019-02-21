package solutions.empire42.tatianego.view

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.parse.ParseUser
import kotlinx.android.synthetic.main.activity_login.*
import solutions.empire42.tatianego.MainActivity
import solutions.empire42.tatianego.R
import com.parse.LogInCallback
import com.parse.ParseException


class LoginActivity : AppCompatActivity() {

    private val MY_CAMERA_REQUEST_CODE = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar!!.hide()
        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        btn_entrar.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        //  doUserLogin();
        }

        btn_cadastrar.setOnClickListener {
            startActivity(Intent(this, CadastroUsuarioActivity::class.java))
        }


//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
//
//            } else {
//                ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf(Manifest.permission.CAMERA),
//                    MY_CAMERA_REQUEST_CODE
//                )
//                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                // app-defined int constant. The callback method gets the
//                // result of the request.
//            }
//        }
    }

    private fun doUserLogin() {
        if(input_txt_email.text != null && input_txt_senha.text.isNotBlank()) {

            if(!input_txt_email.text.contains("@")) {
                Toast.makeText(this, "Email inválido!", Toast.LENGTH_SHORT).show();
            } else {
                ParseUser.logInInBackground(input_txt_email.text.toString(), input_txt_senha.text.toString()
                ) { user, e ->
                    if (user != null) {
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    } else {
                        Toast.makeText(this@LoginActivity, "Usuário inválido!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } else {
            Toast.makeText(this, "Preencha os campos Login/ Senha !!", Toast.LENGTH_SHORT).show();
        }
    }




}
