package com.example.submission

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_FLAG = "extra_flag"
    }
    private lateinit var rvFlags: RecyclerView
    private val list = ArrayList<Flag>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rvFlags = findViewById(R.id.rv_flags)
        rvFlags.setHasFixedSize(true)
        list.addAll(getListFlags())
        showRecyclerList()

        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.appbar)))


    }

    private fun getListFlags(): ArrayList<Flag> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataIbuKota = resources.getStringArray(R.array.data_ibukota)
        val dataTanggal = resources.getStringArray(R.array.data_tanggal_bergabung)
        val listFlag = ArrayList<Flag>()
        for (i in dataName.indices) {
            val flag = Flag(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataIbuKota[i], dataTanggal[i])
            listFlag.add(flag)
        }
        return listFlag
    }

    private fun showRecyclerList() {
        rvFlags.layoutManager = LinearLayoutManager(this)
        val listFlagAdapter = ListFlagAdapter(list)
        rvFlags.adapter = listFlagAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val item = menu?.findItem(R.id.action_share)
        item?.isVisible = false // Sembunyikan menu
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val person = Person(
                    "Muhammad Sutan Fathir",
                    "2210631170090@student.unsika.ac.id",
                    "",
                )
                val moveWithObjectIntent = Intent(this@MainActivity, Profile::class.java)
                moveWithObjectIntent.putExtra(Profile.EXTRA_PERSON, person)
                startActivity(moveWithObjectIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}