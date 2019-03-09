package solutions.empire42.tatianego.fragment


import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.NotificationCompat
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
import kotlinx.android.synthetic.main.fragment_encomenda.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import solutions.empire42.tatianego.MainActivity
import solutions.empire42.tatianego.R
import solutions.empire42.tatianego.adapter.EncomendaAdapter
import solutions.empire42.tatianego.core.UserSharedPreferenceManager
import solutions.empire42.tatianego.model.Historico
import tgio.parselivequery.BaseQuery
import tgio.parselivequery.LiveQueryClient
import tgio.parselivequery.LiveQueryEvent
import java.util.*
import kotlin.collections.ArrayList


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

        val historicos: ArrayList<Historico> = arrayListOf(
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
            loadingblocks2.visibility = View.GONE

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


        // notificar()
       // creme()
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
                      creme()
                    }
                }
            }
        }
    }

    @SuppressLint("NewApi")
    fun creme() {
        val padrao = longArrayOf(0, 100, 1000, 100, 1000)
        val manager = activity!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        var id = "my_channel_01";
        var importance = NotificationManager.IMPORTANCE_LOW;
        var mChannel =  NotificationChannel(id, "name",importance);
        mChannel.enableLights(true);
        manager. createNotificationChannel(mChannel);

        var notification =  Notification.Builder(activity , id)
            .setContentTitle("Title")
            .setChannelId(id)
            .setSmallIcon(R.mipmap.ic_launcher)
            .build()
        // notification.vibrate = padrao
        manager.notify(2, notification);
    }

//    fun notificar() {
//        val padrao = longArrayOf(0, 100, 1000, 100, 1000)
//        val builder = NotificationCompat.Builder(context)
//            .setSmallIcon(R.drawable.cup_cake_icon)
//            .setVibrate(padrao)
//            .setContentTitle("Atenção")
//            .setContentText("Alteração no status do banheiro masculino")
//
//        val notificationIntent = Intent(context, MainActivity::class.java)
//        val contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT)
//        builder.setContentIntent(contentIntent)
//
//        // Add as notification
//        val manager = activity!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        manager.notify(0, builder.build())
//    }






}
