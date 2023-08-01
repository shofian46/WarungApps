package com.example.warungfikri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_menu.*

class Menu : AppCompatActivity() {
  @SuppressLint("Range")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_menu)
    // below code is to add on click
    // listener to our add name button
    addName.setOnClickListener{

      // below we have created
      // a new DBHelper class,
      // and passed context to it
      val db = DBHelper(this, null)

      // creating variables for values
      // in name and age edit texts
      val name = enterName.text.toString()
      val kode = enterKode.text.toString()
      val price = enterPrice.text.toString()

      // calling method to add
      // name to our database
      db.addName(name, kode, price)

      // Toast to message on the screen
      Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()

      // at last, clearing edit texts
      enterName.text.clear()
      enterKode.text.clear()
      enterPrice.text.clear()
    }

    // below code is to add on  click
    // listener to our print name button
    printName.setOnClickListener{

      // creating a DBHelper class
      // and passing context to it
      val db = DBHelper(this, null)

      // below is the variable for cursor
      // we have called method to get
      // all names from our database
      // and add to name text view
      val cursor = db.getNameMenu()

      // moving the cursor to first position and
      // appending value in the text view
      cursor!!.moveToFirst()
      Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
      Kode.append(cursor.getString(cursor.getColumnIndex(DBHelper.KODE_COL1)) + "\n")
      Price.append(cursor.getString(cursor.getColumnIndex(DBHelper.PRICE_COL1)) + "\n")

      // moving our cursor to next
      // position and appending values
      while(cursor.moveToNext()){
        Name.append(cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl)) + "\n")
        Kode.append(cursor.getString(cursor.getColumnIndex(DBHelper.KODE_COL1)) + "\n")
        Price.append(cursor.getString(cursor.getColumnIndex(DBHelper.PRICE_COL1)) + "\n")
      }

      // at last we close our cursor
      cursor.close()
    }
  }
}