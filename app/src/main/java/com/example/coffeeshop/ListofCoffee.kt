package com.example.coffeeshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog

class ListofCoffee : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listof_coffee)

        var quantity:Int=0
        var price:Int=0
        var orderSummary:String=""

        val radioButton1:RadioButton=findViewById(R.id.radioBT1)
        val radioButton2:RadioButton=findViewById(R.id.radioBT2)
        val radioButton3:RadioButton=findViewById(R.id.radioBT3)

        radioButton1.setOnClickListener {
            Toast.makeText(this,"Expresso is selected",Toast.LENGTH_SHORT).show()
            price=120
            orderSummary+="Expresso"
        }
        radioButton2.setOnClickListener {
            Toast.makeText(this,"Latte is selected",Toast.LENGTH_SHORT).show()
            price=170
            orderSummary+="Latte"
        }
        radioButton3.setOnClickListener {
            Toast.makeText(this,"Cappuccino is selected",Toast.LENGTH_SHORT).show()
            price=220
            orderSummary+="Cappuccino"
        }


        val quan= arrayOf(0,1,2,3,4,5,6,7,8)
        val spin:Spinner=findViewById(R.id.spinner)
        if(spin!=null)
        {
            val adapter= ArrayAdapter(this,android.R.layout.simple_spinner_item,quan)
            spin.adapter=adapter
        }
        spin.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //Implement quantity variable
                quantity=quan[position]

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
                quantity=0
            }

        }

        val customBT:Button=findViewById(R.id.customizeBT)
        customBT.setOnClickListener {
            val builder=AlertDialog.Builder(this)
            builder.setTitle("Select the Add on")
            val checkBox=CheckBox(this)
            checkBox.setText("Extra Cream  ₹45")
            val lp=LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT)
            lp.setMargins(150,30,0,0)
            checkBox.setPadding(40,0,0,0)
            checkBox.layoutParams=lp

            //setonClickListner for checkbox
            checkBox.setOnClickListener {
                if(checkBox.isChecked())
                {
                    Toast.makeText(this,"Extra Cream is added",Toast.LENGTH_SHORT).show()
                    //update the price
                    price+=45
                    orderSummary+="\nExtra Cream"
                }
            }


            builder.setView(checkBox)
            builder.setPositiveButton("Add"){
                    dialog,i ->
                Toast.makeText(applicationContext,"Done",Toast.LENGTH_LONG).show()
            }
            builder.setNegativeButton("Cancel")
            {
                dialog,i ->
                Toast.makeText(applicationContext,"Doesn't want Cream?",Toast.LENGTH_LONG).show()
            }
            builder.show()


        }

        val proceedBT:Button=findViewById(R.id.proceed)
        proceedBT.setOnClickListener {
            val intent=Intent(this,FinalActivity::class.java)
            intent.putExtra("quantity",quantity)
            intent.putExtra("price",price)
            intent.putExtra("orderSummary",orderSummary)
            startActivity(intent)


        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optionmenu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id=item.itemId
        return when(id) {
            R.id.Exit ->
            {
                finish()
                System.exit(0)
                return true
            }
            else ->super.onOptionsItemSelected(item)
        }

    }
}