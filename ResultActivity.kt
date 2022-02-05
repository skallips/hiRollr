package com.example.hirollr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog

class ResultActivity : AppCompatActivity() {

    var buyIn = 0
    var willingToLose = 0
    var profitGoal = 0
    var luckLevel = 0
    var comfortLevel = 0
    var sobrietyLevel = 0
    var walkAmount = 0
    var selectedRegion = "none"
    var selectedVenue = "none"
    var selectedGameType = "none"
    var selectedSubgame = "none"

    var cashOutTotal = 0
    var resultBankTotal = 0
    var resultProfitTotal = 0
    var winLossVariance = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        selectedRegion = intent.getStringExtra("region").toString()
        selectedVenue = intent.getStringExtra("venue").toString()
        selectedGameType = intent.getStringExtra("gameType").toString()
        selectedSubgame = intent.getStringExtra("subgame").toString()
        buyIn = intent.getIntExtra("buyIn", 0)
        willingToLose = intent.getIntExtra("willingToLose", 0)
        profitGoal = intent.getIntExtra("profitGoal", 0)
        luckLevel = intent.getIntExtra("luckLevel", 0)
        comfortLevel = intent.getIntExtra("comfortLevel", 0)
        sobrietyLevel = intent.getIntExtra("sobrietyLevel", 0)
        walkAmount = intent.getIntExtra("walkAmount", 0)

        val cashOutTextEntry = findViewById<EditText>(R.id.cashOutEntry)


        val backToPlay1 = findViewById<Button>(R.id.playButton)
        val finished1 = findViewById<Button>(R.id.homeButton)
        //val recordResult1 = findViewById<Button>(R.id.recordButton)

        val radioGroup1 = findViewById<RadioGroup>(R.id.cashOutRadioGroup)
        val totalRadio1 = findViewById<RadioButton>(R.id.totalRadio)
        val profitRadio1 = findViewById<RadioButton>(R.id.profitRadio)


        //recordResult1.setOnClickListener {



        radioGroup1.setOnCheckedChangeListener()  {group, checkedId ->

            if(cashOutTextEntry.text.isNullOrEmpty())   {
                Toast.makeText(applicationContext, "Please enter a cash out value.", Toast.LENGTH_LONG).show()
            }
            else    {
                cashOutTotal = cashOutTextEntry.text.toString().toInt()

                if(checkedId == R.id.totalRadio)    {
                    resultBankTotal = cashOutTotal
                    if(resultBankTotal < buyIn) {
                        //lost money (AS WHOLE POSITIVE NUMBER VARIANCE)
                        winLossVariance =  buyIn - resultBankTotal
                        resultsAlert(walkAmount, cashOutTotal, winLossVariance, 1)
                    }
                    else if(resultBankTotal == buyIn)   {
                        //break even
                        resultsAlert(walkAmount, cashOutTotal, winLossVariance, 2)
                    }
                    else    {
                        //made money and met goal
                        winLossVariance = resultBankTotal - buyIn
                        if(resultBankTotal > walkAmount)    {
                            resultsAlert(walkAmount, cashOutTotal, winLossVariance, 4)
                        }
                        else    {       //just made money, did not meet goal
                            resultsAlert(walkAmount, cashOutTotal, winLossVariance, 3)
                        }
                    }
                }
                else if(checkedId == R.id.profitRadio)    {
                    resultProfitTotal = cashOutTotal
                    if(resultProfitTotal < 0) {
                        //lost money
                        winLossVariance = resultProfitTotal
                        resultsAlert(walkAmount, (buyIn + winLossVariance), (-1*winLossVariance), 1)
                    }
                    else if(resultProfitTotal == 0)   {
                        //break even
                        resultsAlert(walkAmount, (buyIn + winLossVariance), winLossVariance, 2)
                    }
                    else    {
                        //made money and met goal
                        winLossVariance = resultProfitTotal
                        if(resultProfitTotal > (walkAmount - buyIn))    {
                            resultsAlert(walkAmount, (buyIn + winLossVariance), winLossVariance, 4)
                        }
                        else    {       //just made money, did not meet goal
                            resultsAlert(walkAmount, (buyIn + winLossVariance), winLossVariance, 3)
                        }
                    }
                }
                else    {
                    Toast.makeText(applicationContext, "Please select a cash out type", Toast.LENGTH_LONG).show()
                }
            }
        }

        backToPlay1.setOnClickListener {

            val intent = Intent(this, PlayActivity::class.java)
            intent.putExtra("region", selectedRegion)
            intent.putExtra("venue", selectedVenue)
            intent.putExtra("gameType", selectedGameType)
            intent.putExtra("subgame", selectedSubgame)
            intent.putExtra("buyIn", buyIn)
            intent.putExtra("willingToLose", willingToLose)
            intent.putExtra("profitGoal", profitGoal)
            intent.putExtra("luckLevel", luckLevel)
            intent.putExtra("comfortLevel", comfortLevel)
            intent.putExtra("sobrietyLevel", sobrietyLevel)
            intent.putExtra("walkAmount", walkAmount)
            startActivity(intent)
        }

        finished1.setOnClickListener {

            val intent = Intent(this, DashboardActivity::class.java)
            intent.putExtra("region", selectedRegion)
            intent.putExtra("venue", selectedVenue)
            intent.putExtra("gameType", selectedGameType)
            intent.putExtra("subgame", selectedSubgame)
            intent.putExtra("buyIn", buyIn)
            intent.putExtra("willingToLose", willingToLose)
            intent.putExtra("profitGoal", profitGoal)
            intent.putExtra("luckLevel", luckLevel)
            intent.putExtra("comfortLevel", comfortLevel)
            intent.putExtra("sobrietyLevel", sobrietyLevel)
            intent.putExtra("walkAmount", walkAmount)
            startActivity(intent)
        }
    }

    fun resultsAlert(inWalkAmount : Int, inCashOutAmount: Int, inVariance : Int, winCode: Int)    {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)

        if(winCode == 1)    {
            alertDialog.setTitle("You Lost Money")
            alertDialog.setMessage("Money lost: $" + inVariance.toString())
        }
        else if(winCode == 2)   {
            alertDialog.setTitle("You Broke Even")
            alertDialog.setMessage("Money lost: $0")
        }
        else if(winCode == 3)   {
            alertDialog.setTitle("You Made Money!")
            alertDialog.setMessage("Money earned: $" + inVariance.toString())
        }
        else if(winCode == 4)   {
            alertDialog.setTitle("You Made Money and met your Walk Away goal!")
            alertDialog.setMessage("Money earned: $" + inVariance.toString() + "\n " +
                    "Walk away goal exceeded by $" + (inCashOutAmount - inWalkAmount).toString())
        }


        alertDialog.setPositiveButton("Got it") {dialog, id ->
        }

        alertDialog.show()
    }
}