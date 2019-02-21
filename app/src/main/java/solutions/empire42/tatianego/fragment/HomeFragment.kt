package solutions.empire42.tatianego.fragment


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import kotlinx.android.synthetic.main.fragment_home.*

import solutions.empire42.tatianego.R
import android.widget.Toast
import solutions.empire42.tatianego.MainActivity
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialogListener
import com.bestsoft32.tt_fancy_gif_dialog_lib.TTFancyGifDialog





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

        button4.setOnClickListener {
            TTFancyGifDialog.Builder(mActivity)
                .setTitle("Bolo de Cenoura")
                .setMessage("Encomendar uma deliciosa fatia de bolo de cenoura.")
                .setPositiveBtnText("Sim")
                .setPositiveBtnBackground("#22b573")
                .setNegativeBtnText("Não")
                .setNegativeBtnBackground("#c1272d")
                .setGifResource(R.drawable.delivery)      //pass your gif, png or jpg

                .isCancellable(true)
                .OnPositiveClicked { Toast.makeText(mActivity, "Ok", Toast.LENGTH_SHORT).show() }
                .OnNegativeClicked { Toast.makeText(mActivity, "Cancel", Toast.LENGTH_SHORT).show() }
                .build()
        }

    }

    override fun onAttach(context: Activity?) {
        super.onAttach(context)
         mActivity = context!!
    }






}
