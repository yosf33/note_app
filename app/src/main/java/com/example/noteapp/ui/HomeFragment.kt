package com.example.noteapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.noteapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeFragment : BaseFragment() {

    lateinit var fab_add:FloatingActionButton
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab_add= view.findViewById(R.id.fab_add)
        fab_add.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_addNoteFragment)

        }

    }


}