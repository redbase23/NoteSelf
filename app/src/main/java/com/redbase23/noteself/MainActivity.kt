package com.redbase23.noteself

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private  var mSerializer: JSONSerializer?=null
    private var noteList:ArrayList<Note>?=null
    private var recyclerView:RecyclerView?=null
    private var adapter:NoteAdapter?=null
    private var showDividers:Boolean=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {view ->
            val dialog = DialogNewNote()
            dialog.show(supportFragmentManager, "")
        }

        mSerializer=JSONSerializer("NoteToSelf.json",applicationContext)

        try{
            noteList=mSerializer!!.load()
        }catch (e:Exception){
            noteList= ArrayList()
            Log.e("Error loading notes: ",", ", e)
        }

        recyclerView=findViewById<View>(R.id.recyclerView) as RecyclerView
        adapter= NoteAdapter(this,noteList!!)

        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.layoutManager=layoutManager
        recyclerView!!.itemAnimator=DefaultItemAnimator()

        recyclerView!!.adapter=adapter
    }

    fun createNewNote(n: Note) {
        noteList!!.add(n)
        adapter!!.notifyDataSetChanged()
    }

    fun showNote(noteToShow: Int){
        val dialog = DialogShowNote()
        dialog.sendNoteSelected(noteList!![noteToShow])
        dialog.show(supportFragmentManager,"")
    }

    private fun saveNotes(){
        try{
            mSerializer!!.save(noteList!!)
        }catch (e: Exception){
            Log.e("Error saving notes",", ",e)
        }
    }

    override fun onPause() {
        super.onPause()

        saveNotes()
    }

    override fun onResume() {
        super.onResume()

        val prefs=getSharedPreferences(SettingsActivity.keyForPreferences,Context.MODE_PRIVATE)
        showDividers=prefs.getBoolean(SettingsActivity.keyForDividersPreference,true)

        if(showDividers){
            recyclerView!!.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))
        }else{
            if(recyclerView!!.itemDecorationCount>0)
                recyclerView!!.removeItemDecorationAt(0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_settings->{
                val intent = Intent(this,SettingsActivity::class.java)
                startActivity(intent)
                true
            }
            else-> super.onOptionsItemSelected(item)
        }
    }
}