package com.example.submission

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.appbar)))

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        val dataFlag = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Flag>("extra_flag", Flag::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Flag>("extra_flag")
        }

        val name = findViewById<TextView>(R.id.tv_item_name)
        val description = findViewById<TextView>(R.id.tv_item_description)
        val photo = findViewById<ImageView>(R.id.img_item_photo)
        val ibukota = findViewById<TextView>(R.id.tv_ibu_kota)
        val tanggal = findViewById<TextView>(R.id.tv_tanggal_bergabung)


        name.text = dataFlag?.name
        description.text = dataFlag?.description
        photo.setImageResource(dataFlag?.photo!!)
        ibukota.text = dataFlag?.ibukota
        tanggal.text = dataFlag?.tanggal
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.action_share -> {
                shareData()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val item = menu?.findItem(R.id.about_page)
        item?.isVisible = false // Sembunyikan menu
        return super.onPrepareOptionsMenu(menu)
    }

    private fun getImageUri(bitmap: Bitmap): Uri {
        val path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "Image Description", null)
        return Uri.parse(path)
    }

    private fun shareData() {
        val dataFlag = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Flag>("extra_flag", Flag::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Flag>("extra_flag")
        }

        val shareText = "Name: ${dataFlag?.name}\n" +
                "Ibu Kota: ${dataFlag?.ibukota}\n" +
                "Tanggal Bergabung: ${dataFlag?.tanggal}\nDescription: ${dataFlag?.description}"

        // Get the Bitmap from drawable
        val bitmap = BitmapFactory.decodeResource(resources, dataFlag?.photo!!)
        val imageUri = getImageUri(bitmap)

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            putExtra(Intent.EXTRA_STREAM, imageUri)
            type = "image/*"
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }



}