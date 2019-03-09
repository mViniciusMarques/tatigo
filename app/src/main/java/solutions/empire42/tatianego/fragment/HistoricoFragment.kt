package solutions.empire42.tatianego.fragment


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
import kotlinx.android.synthetic.main.fragment_historico.*
import solutions.empire42.tatianego.R
import solutions.empire42.tatianego.adapter.HistoricoAdapter
import solutions.empire42.tatianego.core.UserSharedPreferenceManager
import solutions.empire42.tatianego.model.Historico


class HistoricoFragment : Fragment() {

    var user: ParseUser? = null
    private var userManager: UserSharedPreferenceManager? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_historico, container, false)

        return v
    }


    override fun onStart() {
        super.onStart()
        userManager = UserSharedPreferenceManager(context)
        val recyclerView = view!!.findViewById(R.id.recycle_historico) as RecyclerView

        val historicos: MutableCollection<Historico> = arrayListOf(
//            Historico("Bolo cenoura", user?.username, Date(), "Sim"),
//            Historico("ChupChup", user?.username, Date(), "Sim"),
//            Historico("Salada de Frutas", user?.username, Date(), "Sim")
        )
        recyclerView.adapter = HistoricoAdapter(historicos.toList(), context, HistoricoAdapter.OnHistoricoItemClickListener {
            Toast.makeText(context, "Item Clicked", Toast.LENGTH_LONG).show()
        })

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
                    historico.usuario = userManager!!.loggedUser.username

                    when {
                        historico.produto.equals("Bolo de Cenoura", true) -> historico.imagem = R.drawable.cenoura
                        historico.produto.equals("Chup Chup", true) -> historico.imagem = R.drawable.picole
                        historico.produto.equals("Salada de Frutas", true) -> historico.imagem = R.drawable.frutas_total
                        else -> historico.imagem = R.drawable.cup_cake_icon
                    }

                    his.add(historico)
                    Log.w("marcus", historico.usuario)
                    Log.w("fanboy", it.objectId)
                }
                 loadingblocks4.visibility = View.GONE
                    recyclerView.adapter!!.notifyDataSetChanged()
                    recyclerView.adapter = HistoricoAdapter(his.toList(), context, HistoricoAdapter.OnHistoricoItemClickListener {
                            Toast.makeText(context, "Item clicado " + it.produto , Toast.LENGTH_LONG).show()
                    })


            }

        val layout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layout

    }

}
