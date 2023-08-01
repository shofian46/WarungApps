package com.example.warungfikri

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*
import kotlin.collections.ArrayList
import android.widget.AdapterView
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import com.example.gridview.GridRVAdapter
import com.example.gridview.GridViewModal

class Warungku : AppCompatActivity() {
  // on below line we are creating
  // variables for grid view and course list
  lateinit var courseGRV: GridView
  lateinit var courseList: List<GridViewModal>
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_warungku)
    // initializing variables of grid view with their ids.
    courseGRV = findViewById(R.id.idGRV)
    courseList = ArrayList<GridViewModal>()

    // on below line we are adding data to
    // our course list with image and course name.
    courseList = courseList + GridViewModal("Menu", R.drawable.eat)
    courseList = courseList + GridViewModal("Transaksi", R.drawable.pesan)
    courseList = courseList + GridViewModal("Tentang Developer", R.drawable.user)
    courseList = courseList + GridViewModal("Lokasi", R.drawable.lokasi)
    courseList = courseList + GridViewModal("Berita", R.drawable.newspaper)
    courseList = courseList + GridViewModal("PPKD", R.drawable.ppkd)

    // on below line we are initializing our course adapter
    // and passing course list and context.
    val courseAdapter = GridRVAdapter(courseList = courseList, this@Warungku)

    // on below line we are setting adapter to our grid view.
    courseGRV.adapter = courseAdapter

    // on below line we are adding on item
    // click listener for our grid view.
    courseGRV.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
      // inside on click method we are simply displaying
      // a toast message with course name.
      Toast.makeText(
        applicationContext, courseList[position].courseName + " selected",
        Toast.LENGTH_SHORT
      ).show()

      if (courseList[position].courseName == "Menu") {
        val intent = Intent(this, Menu::class.java)
        startActivity(intent)
      }
      else if (courseList[position].courseName == "Transaksi") {
        val intent = Intent(this, Transaksi::class.java)
        startActivity(intent)
      }
      else if (courseList[position].courseName == "Tentang Developer") {
        val intent = Intent(this, TentangDeveloper::class.java)
        startActivity(intent)
      }
      else if (courseList[position].courseName == "PPKD") {
            val intent = Intent(this, Ppkd::class.java)
            startActivity(intent)
      }
      else if (courseList[position].courseName == "Berita") {
        val intent = Intent(this, Berita::class.java)
        startActivity(intent)
      }
      else if (courseList[position].courseName == "Lokasi") {
        val intent = Intent(this, Location::class.java)
        startActivity(intent)
      }
    }
  }
}