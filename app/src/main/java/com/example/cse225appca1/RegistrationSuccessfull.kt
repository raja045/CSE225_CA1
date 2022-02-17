package com.example.cse225appca1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout

class RegistrationSuccessfull : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_successfull)
        val personName = findViewById<TextView>(R.id.dataentry)
        val simplerating = findViewById<RatingBar>(R.id.ratingBar)
        val feed = findViewById<Button>(R.id.feeed)

        val name = intent.getStringExtra("Extra_name")
        val namestring = "$name...Relax! Your data has been recorded"
        personName.text = namestring

        feed.setOnClickListener(){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("FeedBack")
                .setMessage("Please give your valuable feedback here")
            val int = EditText(this)
            val lp = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            int.layoutParams = lp
            builder.setView(int)

            builder.setPositiveButton("ok"){a,b ->
              Toast.makeText(applicationContext,"Your valuable Feedback has been recorded.",Toast.LENGTH_LONG).show()

            }
            builder.show()
        }
    }
}