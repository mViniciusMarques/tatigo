package solutions.empire42.tatianego.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser
import solutions.empire42.tatianego.R
import solutions.empire42.tatianego.adapter.HistoricoAdapter
import solutions.empire42.tatianego.model.Historico
import java.util.*


class HistoricoFragment : Fragment() {

    var user: ParseUser? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_historico, container, false)
        user = ParseUser.getCurrentUser()

        return v
    }

    override fun onStart() {
        super.onStart()

        val recyclerView = view!!.findViewById(R.id.recycle_historico) as RecyclerView

        val historicos: List<Historico> = arrayListOf(
                                                        Historico("Bolo cenoura", user?.username, Date(), "Sim"),
                                                        Historico("ChupChup", user?.username, Date(), "Sim"),
                                                        Historico("Salada de Frutas", user?.username, Date(), "Sim")
        )

        recyclerView.adapter = HistoricoAdapter(historicos, context)


        val layout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layout

        //abrirAMente()

    }

    private fun abrirAMente() {
        val encomendaQuery = ParseQuery.getQuery<ParseObject>("Encomenda")

        encomendaQuery.find()
    }


}
