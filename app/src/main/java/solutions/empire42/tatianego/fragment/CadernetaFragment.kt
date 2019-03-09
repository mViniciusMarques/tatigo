package solutions.empire42.tatianego.fragment


import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mikhaellopez.lazydatepicker.LazyDatePicker
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.android.synthetic.main.fragment_caderneta.*

import solutions.empire42.tatianego.R
import solutions.empire42.tatianego.adapter.BoloCenouraAdapter
import solutions.empire42.tatianego.core.UserSharedPreferenceManager
import solutions.empire42.tatianego.model.Produto
import solutions.empire42.tatianego.model.Usuario
import java.util.*
import kotlin.collections.ArrayList
import android.widget.AutoCompleteTextView
import android.widget.ArrayAdapter
import com.parse.ParseUser
import com.parse.ktx.findAll
import solutions.empire42.tatianego.adapter.ChupChupAdapter
import solutions.empire42.tatianego.adapter.SaladaFrutaAdapter
import solutions.empire42.tatianego.model.Caderneta


class CadernetaFragment : Fragment() {

    private var userManager: UserSharedPreferenceManager? = null
    val  usuarios: MutableCollection<Usuario> = ArrayList()
    val listaUsuarios: MutableCollection<String> = ArrayList()
    var usuarioSelecionado = ""
    var dataSelecionada: Date ?= null

    var quantidadeBoloCenoura = 0
    var quantidadeSaladaFrutas = 0
    var quantidadeChupChup = 0

    var retornoProdutosBoloCenoura: MutableCollection<Produto> = ArrayList()
    var retornoProdutosSaladaFruta: MutableCollection<Produto> = ArrayList()
    var retornoProdutosChupChup: MutableCollection<Produto> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_caderneta, container, false)
    }

    override fun onStart() {
        super.onStart()

        userManager = UserSharedPreferenceManager(context)
        val recyclerView = view!!.findViewById(R.id.recycle_bolo_cenoura) as RecyclerView
        val recyclerViewChup = view!!.findViewById(R.id.recycle_chup_chup) as RecyclerView
        val recyclerViewSalada = view!!.findViewById(R.id.recycle_salada_fruta) as RecyclerView
        obterUsuarios()

        lazyDatePicker.setDateFormat(LazyDatePicker.DateFormat.DD_MM_YYYY)
        //lazyDatePicker.setMinDate(minDate)
        //lazyDatePicker.setMaxDate(maxDate)

        val layout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val layout2 = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val layout3 = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layout
        recyclerViewChup.layoutManager = layout2
        recyclerViewSalada.layoutManager = layout3

        lazyDatePicker.setOnDatePickListener { dateSelected -> dataSelecionada = dateSelected }

        inicializarCardenetas()
        executarRegrasUICadernetas()

        usuarios.forEach { listaUsuarios.add(it.username) }

        val autoComplete = view!!.findViewById(R.id.autoCompleteTextView) as AutoCompleteTextView
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, listaUsuarios. toList())
        autoComplete.setAdapter(adapter)

        obterCadernetaUsuario()
    }



    private fun executarRegrasUICadernetas() {
        card_principal_caderneta.setOnClickListener {
            if (frame_recycle_bolo.visibility == View.GONE) {
                frame_recycle_bolo.visibility = View.VISIBLE
                card_principal_caderneta2.visibility = View.GONE
                card_principal_caderneta3.visibility = View.GONE
                data_user_box.visibility = View.GONE
                btn_buscar_rendimento.text = "Limpar"

            } else {
                frame_recycle_bolo.visibility = View.GONE
                card_principal_caderneta2.visibility = View.VISIBLE
                card_principal_caderneta3.visibility = View.VISIBLE
                data_user_box.visibility = View.VISIBLE
                btn_buscar_rendimento.text = "Buscar"
            }
        }

        card_principal_caderneta2.setOnClickListener {
            if (frame_recycle_bolo2.visibility == View.GONE) {
                frame_recycle_bolo2.visibility = View.VISIBLE
                card_principal_caderneta.visibility = View.GONE
                card_principal_caderneta3.visibility = View.GONE
                data_user_box.visibility = View.GONE
                btn_buscar_rendimento.text = "Limpar"

                ObjectAnimator.ofFloat(card_principal_caderneta2, "translationY", -80f).apply {
                    duration = 500
                    start()
                }
            } else {
                frame_recycle_bolo2.visibility = View.GONE
                card_principal_caderneta.visibility = View.VISIBLE
                card_principal_caderneta3.visibility = View.VISIBLE
                card_principal_caderneta3.visibility = View.VISIBLE
                data_user_box.visibility = View.VISIBLE
                btn_buscar_rendimento.text = "Buscar"

                ObjectAnimator.ofFloat(card_principal_caderneta2, "translationY", 20f).apply {
                    duration = 500
                    start()
                }
            }
        }

        card_principal_caderneta3.setOnClickListener {
            if (frame_recycle_bolo3.visibility == View.GONE) {
                frame_recycle_bolo3.visibility = View.VISIBLE
                card_principal_caderneta.visibility = View.GONE
                card_principal_caderneta2.visibility = View.GONE
                data_user_box.visibility = View.GONE
                btn_buscar_rendimento.text = "Limpar"

                ObjectAnimator.ofFloat(card_principal_caderneta3, "translationY", -160f).apply {
                    duration = 500
                    start()
                }

            } else {
                frame_recycle_bolo3.visibility = View.GONE
                card_principal_caderneta.visibility = View.VISIBLE
                card_principal_caderneta2.visibility = View.VISIBLE
                data_user_box.visibility = View.VISIBLE
                btn_buscar_rendimento.text = "Buscar"

                ObjectAnimator.ofFloat(card_principal_caderneta3, "translationY", 0f).apply {
                    duration = 500
                    start()
                }

            }
        }

        btn_buscar_rendimento.setOnClickListener {
            if (data_user_box.visibility == View.VISIBLE) {
                data_user_box.visibility = View.GONE
                btn_buscar_rendimento.text = "Limpar"


                this.obterCadernetaBoloCenoura()
                this.obterCadernetaSaladaFruta()
                this.obterCadernetaChupChup()
                this.desbloquearResultadoPesquisa()
            } else {
                data_user_box.visibility = View.VISIBLE
                btn_buscar_rendimento.text = "Buscar"
                this.retornoProdutosBoloCenoura = arrayListOf()
                this.retornoProdutosSaladaFruta = arrayListOf()
                this.retornoProdutosChupChup = arrayListOf()
            }
        }
    }

    private fun inicializarCardenetas() {
        card_principal_caderneta.visibility = View.GONE
        card_principal_caderneta2.visibility = View. GONE
        card_principal_caderneta3.visibility = View.GONE
    }

    private fun desbloquearResultadoPesquisa() {
        card_principal_caderneta.visibility = View.VISIBLE
        card_principal_caderneta2.visibility = View. VISIBLE
        card_principal_caderneta3.visibility = View.VISIBLE
    }

    private fun obterCadernetaUsuario() {
        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(context, parent.getItemAtPosition(position).toString() , Toast.LENGTH_SHORT).show()
            this.usuarioSelecionado = parent.getItemAtPosition(position).toString()
        }
    }

    private fun obterUsuarios() {
        if(usuarios.isEmpty()) {
            val userQuery = ParseQuery.getQuery<ParseObject>("Pessoa")
            val useruery = ParseUser.getQuery();

            useruery.findAll().forEach {
                val usuario = Usuario()
                usuario.username = it.getString("username")
                usuario.objectId = it.getString("objectId")
                usuario.email = it.getString("email")

                usuarios.add(usuario)
            }
        }
    }


    private fun obterCadernetaBoloCenoura() {
        val useruery = ParseUser.getQuery();
        val produtoQuery = ParseQuery.getQuery<ParseObject>("Encomenda")

        useruery.whereEqualTo("username", this.usuarioSelecionado)

        produtoQuery.whereEqualTo("user_id",  useruery.first)
        produtoQuery.whereGreaterThan("createdAt", this.dataSelecionada)
        produtoQuery.whereEqualTo("tipo_produto", "Bolo de Cenoura")

        val produtosCadernetas = produtoQuery.find()
        this.quantidadeBoloCenoura = produtosCadernetas.size
        produto_numero.text = produtosCadernetas.size.toString()

        produtosCadernetas.forEach {
            var produto = Produto()
            produto.nome = it.getString("tipo_produto")
            produto.dataBase = it.createdAt
            this.retornoProdutosBoloCenoura.add(produto)
        }

        recycle_bolo_cenoura.adapter = BoloCenouraAdapter(
            retornoProdutosBoloCenoura.toList(),
            context,
            BoloCenouraAdapter.OnBoloCenouraItemClickListener {
                Toast.makeText(context, "Item Clicked", Toast.LENGTH_LONG).show()
            })

    }

    private fun obterCadernetaSaladaFruta() {
        val useruery = ParseUser.getQuery();
        val produtoQuery = ParseQuery.getQuery<ParseObject>("Encomenda")

        useruery.whereEqualTo("username", this.usuarioSelecionado)

        produtoQuery.whereEqualTo("user_id", useruery.first)
        produtoQuery.whereGreaterThan("createdAt", this.dataSelecionada)
        produtoQuery.whereEqualTo("tipo_produto", "Salada De Frutas")

        val produtosCadernetas = produtoQuery.find()
        this.quantidadeSaladaFrutas= produtosCadernetas.size
        produto_numero2.text = produtosCadernetas.size.toString()

        produtosCadernetas.forEach {
            var produto = Produto()
            produto.nome = it.getString("tipo_produto")
            produto.dataBase = it.createdAt
            this.retornoProdutosSaladaFruta.add(produto)
        }

        recycle_salada_fruta.adapter = SaladaFrutaAdapter(
            retornoProdutosSaladaFruta.toList(),
            context,
            SaladaFrutaAdapter.OnSaladaDeFrutaItemClickListener {
                Toast.makeText(context, "Item Clicked", Toast.LENGTH_LONG).show()
            })
    }

    private fun obterCadernetaChupChup() {
        val produtoQuery = ParseQuery.getQuery<ParseObject>("Encomenda")
        val useruery = ParseUser.getQuery();
        useruery.whereEqualTo("username", this.usuarioSelecionado)

        produtoQuery.whereEqualTo("user_id", useruery.first)
        produtoQuery.whereGreaterThan("createdAt", this.dataSelecionada)
        produtoQuery.whereEqualTo("tipo_produto", "Chup Chup")

        val produtosCadernetas = produtoQuery.find()
        this.quantidadeChupChup = produtosCadernetas.size
        produto_numero3.text = produtosCadernetas.size.toString()

        produtosCadernetas.forEach {
            var produto = Produto()
            produto.nome = it.getString("tipo_produto")
            produto.dataBase = it.createdAt
            this.retornoProdutosChupChup.add(produto)
        }

        recycle_chup_chup.adapter = ChupChupAdapter(
            retornoProdutosChupChup.toList(),
            context,
            ChupChupAdapter.OnChupChupItemClickListener {
                Toast.makeText(context, "Item Clicked", Toast.LENGTH_LONG).show()
            })
    }
}





