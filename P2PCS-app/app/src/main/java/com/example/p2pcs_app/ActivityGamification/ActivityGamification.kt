package com.example.p2pcs_app.ActivityGamification

import android.graphics.drawable.AnimationDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.example.p2pcs_app.R
import kotlinx.android.synthetic.main.activity_gamification.*

import java.util.*

class ActivityGamification : AppCompatActivity() {

    var random = Random()
    var slot1: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamification)

        buttonStart.setOnClickListener{



            mainActivitySlot1.setImageResource(R.drawable.animation)
            val slot1Anin: AnimationDrawable = mainActivitySlot1.drawable as AnimationDrawable
            slot1Anin.start()



            var handler = Handler()
            handler.postDelayed({
                slot1Anin.stop()

                setImagenes()


            }, 1000)

        }
    }

    private fun setImagenes(){
        slot1 = random.nextInt(6)
        //slot2 = random.nextInt(5)
        //slot3 = random.nextInt(5)


        when(slot1){
            0 -> mainActivitySlot1.setImageResource(R.drawable.fiveperc)
            1 -> mainActivitySlot1.setImageResource(R.drawable.tenperc)
            2 -> mainActivitySlot1.setImageResource(R.drawable.fifteenperc)
            3 -> mainActivitySlot1.setImageResource(R.drawable.fiftypt)
            4 -> mainActivitySlot1.setImageResource(R.drawable.nogift)
            5 -> mainActivitySlot1.setImageResource(R.drawable.tomorrow)


        }

       }
}
