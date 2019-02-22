package solutions.empire42.tatianego.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.parse.ParseUser
import solutions.empire42.tatianego.MainActivity
import solutions.empire42.tatianego.R



class SplashScreenActivity: AppCompatActivity() {

    var currentUser: ParseUser? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar!!.hide()
        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        addTimerToChangePage()

    }


    private fun addTimerToChangePage() {
        val handle = Handler()
        handle.postDelayed({ startActivity(Intent(this, LoginActivity::class.java)) }, 5000)
    }


    private fun verifyUserLogin() {
        val currentUser = ParseUser.getCurrentUser()
        if (currentUser != null) {
            this.currentUser = currentUser
        } else {
            // show the signup or login screen
        }
    }

}
