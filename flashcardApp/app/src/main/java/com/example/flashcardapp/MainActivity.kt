package com.example.flashcardapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val flashcardQuestion = findViewById<View>(R.id.flashcard_question)
        val flashcardAnswer = findViewById<View>(R.id.textView2)

        flashcardQuestion.setOnClickListener() {

            val answerSideView = findViewById<View>(R.id.textView2)

// get the center for the clipping circle

// get the center for the clipping circle
            val cx = answerSideView.width / 2
            val cy = answerSideView.height / 2

// get the final radius for the clipping circle

// get the final radius for the clipping circle
            val finalRadius = Math.hypot(cx.toDouble(), cy.toDouble()).toFloat()

// create the animator for this view (the start radius is zero)

// create the animator for this view (the start radius is zero)
            val anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius)
            //end circular anim
            flashcardQuestion.visibility = View.INVISIBLE // set question card invisible
            flashcardAnswer.visibility = View.VISIBLE // set answer card visible
            //set duration of anim
            anim.duration = 3000
            anim.start()
        }

        flashcardAnswer.setOnClickListener() {
            flashcardQuestion.visibility = View.VISIBLE // set question card invisible
            flashcardAnswer.visibility = View.INVISIBLE // set answer card visible
        }

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            // This code is executed in StartingActivity after we come back from EndingActivity

            val data: Intent? = result.data
            if (data != null) { // Check that we have data returned
                val string1 = data.getStringExtra("string1") // 'string1' needs to match the key we used when we put the string in the Intent
                val string2 = data.getStringExtra("string2")

                // Log the value of the strings for easier debugging
                Log.i("MainActivity", "string1: $string1")
                Log.i("MainActivity", "string2: $string2")
            } else {
                Log.i("MainActivity", "Returned null data from AddCardActivity")
            }

            // snackbar popup for after flashcard is made

        } // records the data passed from addCardActivity

        findViewById<View>(R.id.addButton).setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            resultLauncher.launch(intent) // previously startActivity(intent), but changed so data in addCard could be saved
            overridePendingTransition(R.anim.right_in, R.anim.left_out)

        } // onClick that when clicked, takes us to addCardActivity


    }
}