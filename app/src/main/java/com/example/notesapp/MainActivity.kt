package com.example.notesapp

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private  lateinit var binding : ActivityMainBinding
    private  lateinit var db : NoteDatabaseHelper
    private  lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = NoteDatabaseHelper(this)
        notesAdapter= NotesAdapter( db.getAllNotes(),this)

        binding.recycleView.layoutManager=LinearLayoutManager(this)

        binding.recycleView.adapter=notesAdapter

        binding.fabAddNoteBtn.setOnClickListener{
            val  navigationIntent : Intent =  Intent(this,AddNotesActivity::class.java)
            startActivity(navigationIntent)
//            val addEditNoteDialog = Dialog(this)
//
//            val customView = layoutInflater.inflate(R.layout.custom_dailog, null)
//            addEditNoteDialog.setContentView(customView)
//            addEditNoteDialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
//
//            addEditNoteDialog.show()


        }


    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshDate(db.getAllNotes())
    }
}