package com.redbase23.noteself

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class NoteAdapter(
    private val mainActivity: MainActivity,
    private val noteList: List<Note>
) : RecyclerView.Adapter<NoteAdapter.ListItemHolder>() {

    inner class ListItemHolder(view:View):RecyclerView.ViewHolder(view), View.OnClickListener{
        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ListItemHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}