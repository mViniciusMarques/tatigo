package solutions.empire42.tatianego.fragment


import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import solutions.empire42.tatianego.R
import solutions.empire42.tatianego.adapter.EncomendaAdapter
import solutions.empire42.tatianego.core.UserSharedPreferenceManager
import solutions.empire42.tatianego.model.Historico
import tgio.parselivequery.BaseQuery
import tgio.parselivequery.LiveQueryClient
import tgio.parselivequery.LiveQueryEvent
import java.util.*


class EncomendaFragment : Fragment() {

    private var userManager: UserSharedPreferenceManager? = null
    private var notificationManager: NotificationManager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_encomenda, container, false)
    }

    override fun onStart() {
        super.onStart()

        userManager = UserSharedPreferenceManager(context)
        val recyclerView = view!!.findViewById(R.id.recycle_encomenda) as RecyclerView

        val historicos: MutableCollection<Historico> = arrayListOf(
            Historico("DOCE A", userManager?.loggedUser?.username, Date(), true, 0),
            Historico("DOCE B", userManager?.loggedUser?.username, Date(), false, 0)
        )

        val encomendaQuery = ParseQuery.getQuery<ParseObject>("Encomenda")

        encomendaQuery.whereEqualTo("user_id", ParseUser.getCurrentUser())
        encomendaQuery.orderByDescending("createdAt")
        encomendaQuery.limit = 5
        encomendaQuery.findInBackground { objects, e ->
            val his: MutableCollection<Historico> = arrayListOf()
            objects.forEach {
                val historico = Historico()
                historico.objectId = it.objectId
                historico.produto = it.get("tipo_produto").toString()
                historico.dataHora = it.createdAt
                historico.ativo = it.get("ativo") as Boolean?
                historico.usuario = userManager!!.loggedUser.username


                his.add(historico)
//                Log.w("marcus", historico.usuario)
//                Log.w("fanboy", it.objectId)
            }

            recyclerView.adapter = EncomendaAdapter(his.toList(), context, EncomendaAdapter.OnEncomendaItemClickListener {
                it.ativo = !it.ativo

                Toast.makeText(context, "Item Clicked", Toast.LENGTH_LONG).show()
            },
                EncomendaAdapter.OnEncomendaCheckClickListener {
                    Toast.makeText(context, "Item Clicked " + it.produto + " " + it.ativo, Toast.LENGTH_LONG).show()
                }
            )

        }

//        recyclerView.adapter =
//                EncomendaAdapter(historicos.toList(), context, EncomendaAdapter.OnEncomendaItemClickListener {
//                    it.ativo = !it.ativo
//
//                    Toast.makeText(context, "Item Clicked", Toast.LENGTH_LONG).show()
//                },
//                    EncomendaAdapter.OnEncomendaCheckClickListener {
//                        Toast.makeText(context, "Item Clicked " + it.produto + " " + it.ativo, Toast.LENGTH_LONG).show()
//                    }
//                )

        val layout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layout


       // teste();
    }

    private fun teste() {
        val sub = BaseQuery.Builder("Encomenda")
            .where("ativo", "false")
            //.addField("content")
            .build()
            .subscribe()

        sub.on(LiveQueryEvent.CREATE) { `object` ->
            kotlin.run {
                sub.doAsync {
                    uiThread {
                        Log.w("MOTORAMA", `object`.toString())
                        notificar()
                    }
                }
            }
        }
    }

    fun notificar() {
        notificationManager =
                context!!.getSystemService(
                    Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(
            "solutions.empire42.tatianego",
            "NotifyDemo News",
            "Example News Channel")
    }

    @SuppressLint("NewApi")
    private fun createNotificationChannel(id: String, name: String,
                                          description: String) {

        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(id, name, importance)

        channel.description = description
        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        channel.vibrationPattern =
                longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        notificationManager?.createNotificationChannel(channel)
    }

}
