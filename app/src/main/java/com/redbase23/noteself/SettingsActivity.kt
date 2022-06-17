package com.redbase23.noteself

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    private var showDividers:Boolean=true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val prefs= getSharedPreferences(Companion.keyForPreferences, Context.MODE_PRIVATE)
        showDividers = prefs.getBoolean(keyForDividersPreference,true)

        switch1.isChecked=showDividers

        switch1.setOnCheckedChangeListener{
            buttonView,isChecked->
            showDividers=isChecked
        }
    }
    override fun onPause(){
        super.onPause()

        val prefs=getSharedPreferences(Companion.keyForPreferences,Context.MODE_PRIVATE)
        val editor =prefs.edit()
        editor.putBoolean(keyForDividersPreference,showDividers)
        editor.apply()
    }

    companion object {
        val keyForPreferences:String="Note to self"
        val keyForDividersPreference="dividers"
    }
}