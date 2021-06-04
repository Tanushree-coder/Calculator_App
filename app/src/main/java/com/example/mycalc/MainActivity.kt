package com.example.mycalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var lastNumeric:Boolean=false
    var lastDot:Boolean=false
    lateinit var tv1:TextView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv1= findViewById(R.id.tvInput)
    }

    fun onDigit (view: View)
    {
        //var tv1:TextView= findViewById(R.id.tvInput)
        tv1.append((view as Button).text)
        lastNumeric=true

    }
    fun onClear(view: View)
    {
        //var tv1:TextView= findViewById(R.id.tvInput)
        tv1.text=""
        lastNumeric=false
        lastDot=false
    }
    fun onDecimalPoint(view: View)
    {
        if(lastNumeric && !lastDot)
        {
            //var tv1:TextView= findViewById(R.id.tvInput)
            tv1.append(".")
            lastNumeric=false
            lastDot=true
        }
    }

    fun onEqual(view: View)
    {
        //var tv1:TextView= findViewById(R.id.tvInput)
        if(lastNumeric)
        {
            var tvValue=tv1.text.toString()
            var prefix= ""
            try{
                if(tvValue.startsWith("-"))
                {
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }

                if(tvValue.contains("-")){
                    val splitValue=tvValue.split("-")
                    var one=splitValue[0]
                    val two=splitValue[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    tv1.text=removeZeroAfterDot((one.toDouble()-two.toDouble()).toString())
                }
                else if(tvValue.contains("+")){
                    val splitValue=tvValue.split("+")
                    var one=splitValue[0]
                    val two=splitValue[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    tv1.text=removeZeroAfterDot((one.toDouble()+two.toDouble()).toString())
                }
                else if(tvValue.contains("/")){
                    val splitValue=tvValue.split("/")
                    var one=splitValue[0]
                    val two=splitValue[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    tv1.text=removeZeroAfterDot((one.toDouble()/two.toDouble()).toString())
                }
                else if(tvValue.contains("*")){
                    val splitValue=tvValue.split("*")
                    var one=splitValue[0]
                    val two=splitValue[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix+one
                    }
                    tv1.text=removeZeroAfterDot((one.toDouble()*two.toDouble()).toString())
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result:String) :String
    {
        var value=result
        if(result.contains(".0"))
            value=result.substring(0,result.length-2)
        return value
    }

    fun onOperator(view: View)
    {
        //val tv1:TextView= findViewById(R.id.tvInput)
        if(lastNumeric && !isOperatorAdded(tv1.text.toString()))
        {
            tv1.append((view as Button).text)
            lastNumeric=false
            lastDot=false
        }
    }

    private fun isOperatorAdded(value:String) :Boolean
    {
        return if(value.startsWith("-")) {
            false
        }
        else
        {
            value.contains("/" ) || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }

}