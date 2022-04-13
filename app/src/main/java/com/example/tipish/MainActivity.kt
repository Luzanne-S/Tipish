package com.example.tipish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.nfc.Tag as Tag


private const val Tag = "MainActivity"
private const val INITIAL_TIP_PERCENT =10
class MainActivity : AppCompatActivity() {
    private lateinit var etAmount:EditText
    private lateinit var barTip:SeekBar
    private lateinit var tipPercent:TextView
    private lateinit var tipAmount:TextView
    private lateinit var tTotalAmount:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etAmount = findViewById(R.id.etAmount)
        barTip = findViewById(R.id.barTip)
        tipPercent = findViewById(R.id.tipPercent)
        tipAmount = findViewById(R.id.tipAmount)
        tTotalAmount = findViewById(R.id.tTotalAmount)
        barTip.progress = INITIAL_TIP_PERCENT
        tipPercent.text ="$INITIAL_TIP_PERCENT%"
       barTip.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
           override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
               Log.i(Tag,"onProgressChanged $p1")
               tipPercent.text = "$p1%"
               computeTipAndTotal()
           }

           override fun onStartTrackingTouch(p0: SeekBar?) {

           }

           override fun onStopTrackingTouch(p0: SeekBar?) {

           }

       })
        etAmount.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                Log.i(Tag,"afterTextChanged $p0")
                computeTipAndTotal()
            }

        })
    }
    private fun computeTipAndTotal(){
        if (etAmount.text.isEmpty()){
            tipAmount.text = ""
            tTotalAmount.text = ""
            return
        }
        val baseAmount = etAmount.text.toString().toDouble()
        val tipPercent = barTip.progress
        val tip = baseAmount * tipPercent / 100
        val totalAmount = baseAmount + tip

        tipAmount.text = "%.2f".format (tip) //round off the tip amount
        tTotalAmount.text = "%.2f".format (totalAmount)//round off  final amount

    }
}
