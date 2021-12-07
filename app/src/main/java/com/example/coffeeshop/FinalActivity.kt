package com.example.coffeeshop

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class FinalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        var totalPrice:Int=0
        var priceString="Price: "
        var quantityString="Quantity: "
        var totalPriceString="Total Price: "
        val intent:Intent= intent
        val finalPrice:Int=intent.getIntExtra("price",0)
        val finalQuantity:Int=intent.getIntExtra("quantity",0)
        val orderSummary =intent.getStringExtra("orderSummary")

        val priceTextView:TextView=findViewById(R.id.priceTV)
        val quantityTextView:TextView=findViewById(R.id.quantityTV)
        val orderSummaryTextView:TextView=findViewById(R.id.orderSummaryTV)
        val totalPriceTV:TextView=findViewById(R.id.TotalPrice)
        val shareBT:Button=findViewById(R.id.shareBT)

        totalPrice=finalQuantity*finalPrice

        priceString+=finalPrice
        quantityString+=finalQuantity
        totalPriceString+=totalPrice

        priceTextView.text=priceString
        quantityTextView.text=quantityString
        orderSummaryTextView.text=orderSummary
        totalPriceTV.text=totalPriceString

        shareBT.setOnClickListener {
            val customMessage:String="Your order summary is as follows\n"+orderSummary+"\n"+quantityString+"\n"+priceString+"\n"+totalPriceString+"\nThank You!"

            val mIntent=Intent(Intent.ACTION_SEND)
            mIntent.data= Uri.parse("mailto:")
            mIntent.type="text/plain"
            mIntent.putExtra(Intent.EXTRA_EMAIL, "coffeeshop@gmail.com")
            mIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary")
            mIntent.putExtra(Intent.EXTRA_TEXT, customMessage)
            startActivity(mIntent)
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