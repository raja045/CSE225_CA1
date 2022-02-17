package com.example.cse225appca1

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var button: Button
    companion object{
        val IMAGE_REQUEST_CODE =100
        val REQUEST_IMAGE_CAPTURE = 1

    }
    lateinit var requestGallery:ActivityResultLauncher<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ADialog = findViewById<Button>(R.id.submit)
        button = findViewById(R.id.pic)
        imageView = findViewById(R.id.picc)
        val namee = findViewById<EditText>(R.id.entertxt)
        val spinn = findViewById<Spinner>(R.id.spin)

        val lan = arrayOf("Army","Navy","Air Force")

        if(spinn!=null){
            val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,lan)
            spinn.adapter = adapter
        }

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle("NCC Registration")
        toolbar.setLogo(R.drawable.ncc)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener(){
            Toast.makeText(applicationContext,"Back Arrow",Toast.LENGTH_SHORT).show()
        }

        ADialog.setOnClickListener(){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Are you sure?")
                .setMessage("Do you want to submit the details, you cannot make further changes")
            builder.setPositiveButton("submit"){a, b->
                val name = namee.text.toString().toUpperCase()
                Intent(this,RegistrationSuccessfull::class.java).also {
                    it.putExtra("Extra_name",name)
                    startActivity(it)
                }
            }
            builder.setNeutralButton("Don't submit"){a,b ->

            }
            val alertDialog:AlertDialog = builder.create()
            alertDialog.show()
        }

        requestGallery = registerForActivityResult(ActivityResultContracts.RequestPermission())
        {
            if(it){
                Toast.makeText(
                    applicationContext,
                    "Permission Granted",
                    Toast.LENGTH_LONG
                ).show()
                pickImagegallery()
            }else{
                Toast.makeText(
                    applicationContext,
                    "Permission Denied",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        button.setOnClickListener(){
            storagePermission()
        }
    }

    private fun pickImagegallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_REQUEST_CODE)
    }

    fun storagePermission(){
        when{
            ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    ==PackageManager.PERMISSION_GRANTED ->{Toast.makeText(applicationContext,"Opening Gallery, Permission already granted", Toast.LENGTH_LONG).show()
                    pickImagegallery()
                    }
            else ->{
                requestGallery.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id:Int= item.itemId
        return when(id){
            R.id.settings -> {
                Toast.makeText(applicationContext,"Settings",Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_add ->{
                Toast.makeText(applicationContext,"add",Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}