package com.example.flashcardapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val flashcardQuestion = findViewById<View>(R.id.flashcard_question)
        val flashcardAnswer = findViewById<View>(R.id.textView2)

        flashcardQuestion.setOnClickListener() {
            flashcardQuestion.visibility = View.INVISIBLE // set question card invisible
            flashcardAnswer.visibility = View.VISIBLE // set answer card visible

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

        } // onClick that when clicked, takes us to addCardActivity


    }
}