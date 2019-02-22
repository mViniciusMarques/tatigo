package solutions.empire42.tatianego.fragment


import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import kotlinx.android.synthetic.main.fragment_home.*

import solutions.empire42.tatianego.R
import android.widget.Toast
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog
import com.parse.ParseObject
import com.parse.ParseUser









class HomeFragment : Fragment() {

    var mActivity = Activity();

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onResume() {
        super.onResume()
        this.activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
        )

        encomendarBoloDeCenouro()
        encomendarSaladaDeFrutas()
        encomendarChupChup()

    }

    private fun encomendarBoloDeCenouro() {
        btn_encomendar_bolo.setOnClickListener {
            TTFancyGifDialog.Builder(mActivity)
                .setTitle("Bolo de Cenoura")
                .setMessage("Encomendar uma deliciosa fatia de bolo de cenoura.")
                .setPositiveBtnText("Sim")
                .setPositiveBtnBackground("#22b573")
                .setNegativeBtnText("Não")
                .setNegativeBtnBackground("#c1272d")
                .setGifResource(R.drawable.delivery)      //pass your gif, png or jpg

                .isCancellable(true)
                .OnPositiveClicked { adicionarProduto("Bolo de Cenoura") }
                .OnNegativeClicked { Toast.makeText(mActivity, "Cancel", Toast.LENGTH_SHORT).show() }
                .build()
        }
    }

    private fun encomendarSaladaDeFrutas() {
        btn_encomendar_salada.setOnClickListener {
            TTFancyGifDialog.Builder(mActivity)
                .setTitle("Salada de Fruta")
                .setMessage("Encomendar uma deliciosa salada de frutas fresquinha.")
                .setPositiveBtnText("Sim")
                .setPositiveBtnBackground("#22b573")
                .setNegativeBtnText("Não")
                .setNegativeBtnBackground("#c1272d")
                .setGifResource(R.drawable.delivery)      //pass your gif, png or jpg

                .isCancellable(true)
                .OnPositiveClicked { adicionarProduto("Salada De Frutas") }
                .OnNegativeClicked { Toast.makeText(mActivity, "Cancel", Toast.LENGTH_SHORT).show() }
                .build()
        }
    }

    private fun encomendarChupChup() {
        btn_encomendar_chup.setOnClickListener {
            TTFancyGifDialog.Builder(mActivity)
                .setTitle("Salada de Fruta")
                .setMessage("Encomendar chup-chup com um maravilhoso sabor.")
                .setPositiveBtnText("Sim")
                .setPositiveBtnBackground("#22b573")
                .setNegativeBtnText("Não")
                .setNegativeBtnBackground("#c1272d")
                .setGifResource(R.drawable.delivery)      //pass your gif, png or jpg

                .isCancellable(true)
                .OnPositiveClicked { adicionarProduto("Chup Chup") }
                .OnNegativeClicked { Toast.makeText(mActivity, "Cancel", Toast.LENGTH_SHORT).show() }
                .build()
        }
    }

    override fun onAttach(context: Activity?) {
        super.onAttach(context)
         mActivity = context!!
    }



    private fun adicionarProduto(s: String) {

        val currentUser = ParseUser.getCurrentUser()
        if (currentUser != null) {

            val produto = ParseObject("Encomenda")
            produto.put("tipo_produto", s)
            produto.put("user_id", currentUser )
            produto.put("ativo", true)
            produto.saveInBackground()

        } else {
            // show the signup or login screen
        }



    }


}
