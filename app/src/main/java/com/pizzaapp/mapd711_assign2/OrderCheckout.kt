package com.pizzaapp.mapd711_assign2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pizzaapp.mapd711_assign2.databinding.ActivityOrderCheckoutBinding
import kotlinx.android.synthetic.main.activity_order_checkout.*

class OrderCheckout : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityOrderCheckoutBinding.inflate(layoutInflater).root)
        if (intent != null) {
            textViewCustomerName.text = "Customer Name : " + intent.getStringExtra("fullName")
            textViewPizzaType.text = "Pizza Type : " + intent.getStringExtra("pizzaName")
            textViewPizzaSize.text = "Pizza Size : " + intent.getStringExtra("pizzaSize")
            textViewAddress.text = "Address : " + intent.getStringExtra("address")
            var printToppings = "";
            for (toppings in intent.getStringArrayExtra("extraToppings") as Array<String>)
                printToppings += " $toppings,"
            if (printToppings.isEmpty()) textViewToppings.text = "Toppings : N/A"
            else textViewToppings.text = "Toppings :" + printToppings.dropLast(1)
        }
    }


    fun onBackPressed(view: View) {
        if (view.id == R.id.imgBack) onBackPressed()
    }

}