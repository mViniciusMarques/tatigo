package solutions.empire42.tatianego.view

import android.animation.ObjectAnimator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import solutions.empire42.tatianego.R
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_cadastro_usuario.*


class CadastroUsuarioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario)
        supportActionBar!!.hide()
        val handle = Handler()
        handle.postDelayed(    {    this.doSignUp()   }  , 3000)

    }


    fun doSignUp() {
//        val layoutParams = myCardView.getLayoutParams() as ViewGroup.MarginLayoutParams
//        layoutParams.setMargins(0, 0, 0, 400)
//
//        myCardView.requestLayout()
        ObjectAnimator.ofFloat(myCardView, "translationY", -160f).apply {
            duration = 2000
            start()

        }
    }
}
