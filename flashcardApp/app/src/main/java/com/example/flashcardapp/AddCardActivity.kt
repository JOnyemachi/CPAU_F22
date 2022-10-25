package com.example.flashcardapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar

class AddCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_card_activity)

        findViewById<View>(R.id.cancel_button).setOnClickListener {
            finish()
        } //OCL for cancel button

        findViewById<EditText>(R.id.enterQuestionField).text.toString() //used to grab text data from EditText layout

        findViewById<View>(R.id.save_button).setOnClickListener {
            val data = Intent() // create a new Intent, this is where we will put our data

            data.putExtra(
                "string1",
                "some string"
            ) // puts one string into  Intent, with key 'string1' and its corresponding value
            data.putExtra(
                "string2",
                "another string"
            ) // puts one string into  Intent, with key 'string1' and its corresponding value

            setResult(RESULT_OK, data) // set result code and bundle data for response
            Snackbar.make(findViewById(R.id.flashcard_question),
                "Your flashcard has been created",
                Snackbar.LENGTH_SHORT)
                .show()
            finish()
        }// end save button OCL

    } // end onCreate
}//end XML