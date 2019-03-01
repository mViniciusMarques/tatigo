package solutions.empire42.tatianego.fragment


import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mikhaellopez.lazydatepicker.LazyDatePicker
import kotlinx.android.synthetic.main.fragment_caderneta.*

import solutions.empire42.tatianego.R


class CadernetaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_caderneta, container, false)
    }

    override fun onStart() {
        super.onStart()

        lazyDatePicker.setDateFormat(LazyDatePicker.DateFormat.DD_MM_YYYY)
        //lazyDatePicker.setMinDate(minDate)
        //lazyDatePicker.setMaxDate(maxDate)


        lazyDatePicker.setOnDatePickListener { dateSelected ->
            //...

        }

        card_principal_caderneta.setOnClickListener {
            if( frame_recycle_bolo.visibility == View.GONE){
                frame_recycle_bolo.visibility = View.VISIBLE
                card_principal_caderneta2.visibility = View.GONE
                card_principal_caderneta3.visibility = View.GONE
            } else {
                frame_recycle_bolo.visibility = View.GONE
                card_principal_caderneta2.visibility = View.VISIBLE
                card_principal_caderneta3.visibility = View.VISIBLE
            }
        }

      card_principal_caderneta2.setOnClickListener {
          if( frame_recycle_bolo2.visibility == View.GONE){
              frame_recycle_bolo2.visibility = View.VISIBLE
              card_principal_caderneta.visibility = View.GONE
              card_principal_caderneta3.visibility = View.GONE

              ObjectAnimator.ofFloat(card_principal_caderneta2, "translationY", -80f).apply {
                  duration = 500
                  start()
              }
          } else {
              frame_recycle_bolo2.visibility = View.GONE
              card_principal_caderneta.visibility = View.VISIBLE
              card_principal_caderneta3.visibility = View.VISIBLE
              card_principal_caderneta3.visibility = View.VISIBLE

              ObjectAnimator.ofFloat(card_principal_caderneta2, "translationY", 20f).apply {
                  duration = 500
                  start()
              }
          }
      }

        card_principal_caderneta3.setOnClickListener {
              if( frame_recycle_bolo3.visibility == View.GONE){
                  frame_recycle_bolo3.visibility = View.VISIBLE
                  card_principal_caderneta.visibility = View.GONE
                  card_principal_caderneta2.visibility = View.GONE

                  ObjectAnimator.ofFloat(card_principal_caderneta3, "translationY", -160f).apply {
                      duration = 500
                      start()
                  }

              } else {
                  frame_recycle_bolo3.visibility = View.GONE
                  card_principal_caderneta.visibility = View.VISIBLE
                  card_principal_caderneta2.visibility = View.VISIBLE

                  ObjectAnimator.ofFloat(card_principal_caderneta3, "translationY", 0f).apply {
                      duration = 500
                      start()
                  }

              }
          }
    }
}





