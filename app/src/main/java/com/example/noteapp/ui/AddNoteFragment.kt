package com.example.noteapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.noteapp.R
import com.example.noteapp.db.Note
import com.example.noteapp.db.NoteDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class AddNoteFragment : BaseFragment() {
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
            editText_note.error = "body required"
            editText_note.requestFocus()
        }



                fab_done.setOnClickListener {
                    launch{
                        val note=Note(noteTitle,noteBody)
                        context?.let{
                            NoteDatabase(it).getNoteDao().addNote(note)
                            it.toast("note saved")

                        }
                    }
            Navigation.findNavController(view).navigate(R.id.action_addNoteFragment_to_homeFragment)
        }
    }




}
