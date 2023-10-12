package com.example.noteapp.ui

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.noteapp.R
import com.example.noteapp.db.Note
import com.example.noteapp.db.NoteDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AddNoteFragment : Fragment() {
    lateinit var fab_done: FloatingActionButton
    lateinit var editText_title: EditText
    lateinit var editText_note: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab_done = view.findViewById(R.id.fab_done)
        editText_title = view.findViewById(R.id.title)
        editText_note = view.findViewById(R.id.note)

        val noteTitle = editText_title.text.toString().trim()
        val noteBody = editText_note.text.toString().trim()

        if (noteTitle.isEmpty()) {
            editText_title.error = "Title required"
            editText_title.requestFocus()
        }

        if (noteBody.isEmpty()) {
            editText_note.error = "Title required"
            editText_note.requestFocus()
        }

        val note = Note(noteTitle, noteBody)
        saveNote(note)



        fab_done.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_addNoteFragment_to_homeFragment)

        }
    }

    private fun saveNote(note: Note) {
        class SaveNote : AsyncTask<Void, Void, Void>() {

            override fun doInBackground(vararg p0: Void?): Void? {
                NoteDatabase(activity!!).getNoteDao().addNote(note)
                return null

            }
            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(activity,"Note Saved",Toast.LENGTH_LONG).show()

            }

        }
        SaveNote().execute()
    }



}
