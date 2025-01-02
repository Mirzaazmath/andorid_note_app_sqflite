package com.example.notesapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var notes:List<Note>,context: Context):
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class  NotesViewHolder(item: View):RecyclerView.ViewHolder(item){
        val titleTextView:TextView =item.findViewById(R.id.cardTitle)
        val descriptionTextView:TextView =item.findViewById(R.id.cardDescription)
        val editNote:ImageView=item.findViewById(R.id.editNote)
        val deleteNote:ImageView=item.findViewById(R.id.deleteNote)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return  NotesViewHolder(view)
    }

    override fun getItemCount(): Int=notes.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
       val note =notes[position]
        holder.titleTextView.text=note.title
        holder.descriptionTextView.text=note.description
        holder.editNote.setOnClickListener(){
            val navigationIntent=Intent(holder.itemView.context,AddNotesActivity::class.java).apply{
                putExtra("note_id",note.id)
            }
            holder.itemView.context.startActivity(navigationIntent)

        }

        holder.deleteNote.setOnClickListener(){
            var db =NoteDatabaseHelper(holder.itemView.context)
            db.deleteNote(note.id)
            refreshDate(db.getAllNotes())


        }
    }

    fun refreshDate(newNotes:List<Note>){
        notes=newNotes
        notifyDataSetChanged()

    }
}