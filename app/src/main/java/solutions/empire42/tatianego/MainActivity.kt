package solutions.empire42.tatianego

import android.Manifest
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.support.annotation.NonNull
import android.view.WindowManager
import android.widget.Spinner
import com.parse.ParseUser
import solutions.empire42.tatianego.core.UserSharedPreferenceManager
import solutions.empire42.tatianego.fragment.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fm: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fm = supportFragmentManager

        val fh = HomeFragment()
        loadFrag(fh, "Home", fm)


        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
        )

        val userShared = UserSharedPreferenceManager(this)
        userShared.setUserOnSharedPreference(ParseUser.getCurrentUser())

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_camera -> {
                loadFrag(CadastroProdutoFragment(), "Cadastrar Produto", fm)
            }
            R.id.nav_gallery -> {

                loadFrag(HomeFragment(), "Home", fm)
            }
            R.id.nav_slideshow -> {
                loadFrag(EncomendaFragment(), "Encomendas Gerais", fm)
            }
            R.id.nav_manage -> {
                loadFrag(MeuPerfilFragment(), "Meu Perfil", fm)
            }
            R.id.nav_alert -> {
                loadFrag(RecadoFragment(), "Recado da Tati", fm)
            }
            R.id.nav_history -> {
                loadFrag(HistoricoFragment(), "Histórico de Compras", fm)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadFrag(f1: Fragment, name: String, fm: FragmentManager) {
        val handle = Handler()
        handle.post {
            val ft = fm.beginTransaction()
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ft.replace(R.id.fragment, f1, name)
            ft.commit()

            supportActionBar!!.title = name
        }
    }



}
