package com.redbase23.noteself

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val noteList =ArrayList<Note>()
    private var recyclerView:RecyclerView?=null
    private var adapter:NoteAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {view ->
            val dialog = DialogNewNote()
            dialog.show(supportFragmentManager, "")
        }

        recyclerView=findViewById<View>(R.id.recyclerView) as RecyclerView
        adapter= NoteAdapter(this,noteList)

        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager=layoutManager
        recyclerView!!.itemAnimator=DefaultItemAnimator()
        recyclerView!!.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))

        recyclerView!!.adapter=adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_settings->true
            else-> super.onOptionsItemSelected(item)
        }
    }

    fun createNewNote(n: Note) {
        noteList.add(n)
        adapter!!.notifyDataSetChanged()
    }

    fun showNote(noteToShow: Int){
        val dialog = DialogShowNote()
        dialog.sendNoteSelected(noteList[noteToShow])
        dialog.show(supportFragmentManager,"")
    }
}