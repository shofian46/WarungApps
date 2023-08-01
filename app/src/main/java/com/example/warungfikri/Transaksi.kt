package com.example.warungfikri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.EditText
import android.widget.Toast
import java.util.*
//import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_transaksi.*


class Transaksi : AppCompatActivity() {
  // on below line creating a variable.
  lateinit var dateEdt: EditText
  lateinit var timeEdt: EditText

  @SuppressLint("Range")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_transaksi)
    // on below line we are initializing our variables.
    dateEdt = findViewById(R.id.enterDate)
    timeEdt = findViewById(R.id.enterJam)
    // on below line we are adding
    // click listener for our edit text.
    dateEdt.setOnClickListener {

      // on below line we are getting
      // the instance of our calendar.
      val c = Calendar.getInstance()

      // on below line we are getting
      // our day, month and year.
      val year = c.get(Calendar.YEAR)
      val month = c.get(Calendar.MONTH)
      val day = c.get(Calendar.DAY_OF_MONTH)

      // on below line we are creating a
      // variable for date picker dialog.
      val datePickerDialog = DatePickerDialog(
        // on below line we are passing context.
        this,
        { view, year, monthOfYear, dayOfMonth ->
          // on below line we are setting
          // date to our edit text.
          val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
          dateEdt.setText(dat)
        },
        // on below line we are passing year, month
        // and day for the selected date in our date picker.
        year,
        month,
        day
      )
      // at last we are calling show
      // to display our date picker dialog.
      datePickerDialog.show()
    }

    timeEdt.setOnClickListener{
      val c = Calendar.getInstance()

      // on below line we are getting our hour, minute.
      val hour = c.get(Calendar.HOUR_OF_DAY)
      val minute = c.get(Calendar.MINUTE)

      // on below line we are initializing
      // our Time Picker Dialog
      val timePickerDialog = TimePickerDialog(
        this,
        { view, hourOfDay, minute ->
          // on below line we are setting selected
          // time in our text view.
          timeEdt.setText("$hourOfDay:$minute")
        },
        hour,
        minute,
        false
      )
      // at last we are calling show to
      // display our time picker dialog.
      timePickerDialog.show()
    }

    addName2.setOnClickListener {

      // below we have created
      // a new DBHelper class,
      // and passed context to it
      val db = DbTransaksi(this, null)

      // creating variables for values
      // in name and age edit texts
      val nomeja = enterNoMeja.text.toString()
      val kode = enterKode.text.toString()
      val price = enterPrice.text.toString()
      val date = enterDate.text.toString()
      val time = enterJam.text.toString()

      // calling method to add
      // name to our database
      db.addName2(nomeja, kode, price, date, time)

      // Toast to message on the screen
      Toast.makeText(this, nomeja + " added to database", Toast.LENGTH_LONG).show()

      // at last, clearing edit texts
      enterNoMeja.text.clear()
      enterKode.text.clear()
      enterPrice.text.clear()
      enterDate.text.clear()
      enterJam.text.clear()
    }

    // below code is to add on  click
    // listener to our print name button
    printName.setOnClickListener {

      // creating a DBHelper class
      // and passing context to it
      val db = DbTransaksi(this, null)

      // below is the variable for cursor
      // we have called method to get
      // all names from our database
      // and add to name text view
      val cursor = db.getTransaksi()

      // moving the cursor to first position and
      // appending value in the text view
      cursor!!.moveToFirst()
      NoMeja.append(cursor.getString(cursor.getColumnIndex(DbTransaksi.NOMEJA_COl)) + "\n")
      Kode.append(cursor.getString(cursor.getColumnIndex(DbTransaksi.KODE_COL2)) + "\n")
      Price.append(cursor.getString(cursor.getColumnIndex(DbTransaksi.PRICE_COL2)) + "\n")
      Date.append(cursor.getString(cursor.getColumnIndex(DbTransaksi.DATE_COL)) + "\n")
      Time.append(cursor.getString(cursor.getColumnIndex(DbTransaksi.TIME_COL)) + "\n")

      // moving our cursor to next
      // position and appending values
      while (cursor.moveToNext()) {
        NoMeja.append(cursor.getString(cursor.getColumnIndex(DbTransaksi.NOMEJA_COl)) + "\n")
        Kode.append(cursor.getString(cursor.getColumnIndex(DbTransaksi.KODE_COL2)) + "\n")
        Price.append(cursor.getString(cursor.getColumnIndex(DbTransaksi.PRICE_COL2)) + "\n")
        Date.append(cursor.getString(cursor.getColumnIndex(DbTransaksi.DATE_COL)) + "\n")
        Time.append(cursor.getString(cursor.getColumnIndex(DbTransaksi.TIME_COL)) + "\n")

      }

      // at last we close our cursor
      cursor.close()
    }
  }
}