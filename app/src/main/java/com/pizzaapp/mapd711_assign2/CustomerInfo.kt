package com.pizzaapp.mapd711_assign2

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pizzaapp.mapd711_assign2.databinding.ActivityCustomerInfoBinding
import kotlinx.android.synthetic.main.activity_customer_info.*
import java.util.*

class CustomerInfo : AppCompatActivity() {

    var pizzaName = "";
    var pizzaSize = "";
    var cardType = "";
    var extraToppings = arrayOf<String>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityCustomerInfoBinding.inflate(layoutInflater).root)
        if (intent != null) {
            pizzaName = intent.getStringExtra("pizzaName").toString()
            pizzaSize = intent.getStringExtra("pizzaSize").toString()
            extraToppings = intent.getStringArrayExtra("extraToppings") as Array<String>
        }
    }

    // On confirm button click
    fun btnPayConfirmOrder(v: View) {
        if (v.id == R.id.btnConfirmOrder) checkInputs()
    }

    //Check if any input is empty
    private fun checkInputs() {
        when {
            TextUtils.isEmpty(etName.text) || etName.text.length < 3 -> etName.error =
                "Enter valid full name"
            TextUtils.isEmpty(etAddress.text) || etAddress.text.length < 3 -> etAddress.error =
                "Enter valid address"
            TextUtils.isEmpty(etPostalCode.text) || etPostalCode.text.length < 6 -> etPostalCode.error =
                "Enter valid postal code"
            TextUtils.isEmpty(etPhoneNum.text) || etPhoneNum.text.length < 8 -> etPhoneNum.error =
                "Enter valid phone number"
            TextUtils.isEmpty(etCardName.text) || etCardName.text.length < 3 -> etCardName.error =
                "Enter valid name on card"
            TextUtils.isEmpty(etCardNum.text) || etCardNum.text.length < 16 -> etCardNum.error =
                "Enter valid card number"
            TextUtils.isEmpty(etExpMonth.text) || etExpMonth.text.length < 2 || etExpMonth.text.toString()
                .toInt() > 12 -> etExpMonth.error =
                "Enter valid expiry month"
            TextUtils.isEmpty(etExpYear.text) || etExpYear.text.length < 4 || etExpYear.text.toString()
                .toInt() < Calendar.getInstance()
                .get(Calendar.YEAR) ->
                etExpYear.error = "Enter valid expiry year"
            else -> getCardType()
        }
    }

    private fun getCardType() {
        when (rbtngCardType.checkedRadioButtonId) {
            R.id.rbtnDebit -> cardType = "Debit"
            R.id.rbtnCredit -> cardType = "Credit"
            else -> Toast.makeText(
                applicationContext,
                "Please select type of card",
                Toast.LENGTH_SHORT
            ).show()
        }
        if (cardType.isNotEmpty()) openCheckoutActivity()
    }

    //Open Checkout Activity with some data
    private fun openCheckoutActivity() {

    }

    fun onBackPressed(view: View) {
        if (view.id == R.id.imgBack) onBackPressed()
    }

}