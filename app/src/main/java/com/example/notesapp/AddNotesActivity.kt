package com.example.notesapp
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapp.databinding.ActivityAddNotesBinding


class AddNotesActivity : AppCompatActivity() {


    private lateinit var binding: ActivityAddNotesBinding
    private lateinit var db: NoteDatabaseHelper
    private  var noteId=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
         db = NoteDatabaseHelper(this)

        noteId=intent.getIntExtra("note_id",-1)
        if(noteId!=-1){
            var note =db.getNoteById(noteId)
            binding.editTitle.setText(note.title)
            binding.editDescription.setText(note.description)
            binding.addNoteTitle.setText("Edit Note")
        }

        this.binding.doneBtn.setOnClickListener(){
           val title = binding.editTitle.text.toString()
            val description = binding.editDescription.text.toString()
            println("Title == $title")
            if(title.isEmpty()){
                _toastShower("Please Enter Title")
            }else if(description.isEmpty()){
                _toastShower("Please Enter Description")
            }else{
                if(noteId==-1){
                    val newNote= Note(0,title,description)
                    db.insertNote(newNote)
                    finish()
                    _toastShower("Note Added")
                }else{
                    val updatedNote= Note(noteId,title,description)
                    db.updateNote(updatedNote)
                    finish()
                    _toastShower("Note Updated")

                }

            }

        }


    }
    fun _toastShower(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()


    }
}