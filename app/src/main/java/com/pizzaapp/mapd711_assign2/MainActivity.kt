package com.pizzaapp.mapd711_assign2

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.pizzaapp.mapd711_assign2.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    var pizzaName: String = ""
    var pizzaSize: String = ""
    var extraToppings = arrayOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
        intUI()
    }

    private fun intUI() {

        //Change the status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.WHITE
        }

        val pizzaList = resources.getStringArray(R.array.myPizzaList)
        val adapter = ArrayAdapter(applicationContext, R.layout.item_spinner, pizzaList)

        //Handling Spinner
        spinnerPizzaList.adapter = adapter
        spinnerPizzaList.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                pizzaName = pizzaList[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // pizzaName value remains empty
            }
        }
    }

    //On Click of Place Order button
    fun btnPizzaOrder(v: View) {
        if (v.id == R.id.btnConfirmOrder) getPizzaSize()
    }

    //Handling radio buttons for pizza size
    private fun getPizzaSize() {
        when (radioBtnGroup.checkedRadioButtonId) {
            R.id.rdButtonLarge -> pizzaSize = "Large"
            R.id.rdButtonMedium -> pizzaSize = "Medium"
            R.id.rdButtonSmall -> pizzaSize = "Small"
            else -> Toast.makeText(applicationContext, "Please select size of your pizza", Toast.LENGTH_SHORT).show()
        }
        if (pizzaSize.isNotEmpty()) getToppings()
    }

    //Handling checkbox for toppings
    private fun getToppings() {
        if (chBoxCheese.isChecked) extraToppings += "Cheese"
        if (chBoxGP.isChecked) extraToppings += "Green Pepper"
        if (chBoxSH.isChecked) extraToppings += "Smoked Ham"
        if (chBoxBO.isChecked) extraToppings += "Black Olives"
        if (chBoxSpinach.isChecked) extraToppings += "Spinach"
        if (chBoxSO.isChecked) extraToppings += "Spanish Onions"
        openCustomerInfoActivity()
    }

    //Open Checkout activity
    private fun openCustomerInfoActivity() {
        val i = Intent(applicationContext, CustomerInfo::class.java)
        i.putExtra("pizzaName", pizzaName)
        i.putExtra("pizzaSize", pizzaSize)
        i.putExtra("extraToppings", extraToppings)
        startActivity(i)
    }
}