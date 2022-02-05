package com.example.hirollr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class PlayActivity : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        //get data from previous activities (either venue or play)
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


        val regionTitleData = findViewById<TextView>(R.id.regionTitle)
        //val venueTitleData = findViewById<TextView>(R.id.venueTitle)
        val gameTypeTitleData = findViewById<TextView>(R.id.gameTypeTitle)
        //val gameSubtypeTitleData = findViewById<TextView>(R.id.gameSubtypeTitle)

        val walkAmountDisplay1 = findViewById<TextView>(R.id.walkAmountNumberDisplay1)
        val walkAmountDisplay2 = findViewById<TextView>(R.id.walkAmountNumberDisplay2)
        val lossNumberDisplay1 = findViewById<TextView>(R.id.lossNumberDisplay1)
        val lossNumberDisplay2 = findViewById<TextView>(R.id.lossNumberDisplay2)
        val buyInNumberDisplay = findViewById<TextView>(R.id.buyInNumberDisplay1)

        regionTitleData.text = selectedVenue + ", " + selectedRegion
        //venueTitleData.text = "Venue: " + selectedVenue
        gameTypeTitleData.text = selectedGameType + ": " + selectedSubgame
        //gameSubtypeTitleData.text = "Game: " + selectedSubgame

        buyInNumberDisplay.text = "$" + buyIn
        walkAmountDisplay1.text = "$" + walkAmount
        walkAmountDisplay2.text = "$" + (walkAmount - buyIn)
        lossNumberDisplay1.text = "$" + (buyIn - willingToLose)
        if(willingToLose == 0)  {
            lossNumberDisplay2.text = "$0"
        }else   {
            lossNumberDisplay2.text = "-$" + willingToLose
        }


        val endButton1 = findViewById<Button>(R.id.endButton)
        val sessionButton1 = findViewById<Button>(R.id.sessionButton)

        sessionButton1.setOnClickListener {

            val intent = Intent(this, SessionActivity::class.java)
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

        endButton1.setOnClickListener {
            endSessionRequestAlert()
        }
    }

    fun endSessionRequestAlert()    {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setTitle("End Session")
        alertDialog.setMessage("Do you wish to stop playing?")

        alertDialog.setPositiveButton("Yes") {dialog, id ->
            val intent = Intent(this, ResultActivity::class.java)
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
        alertDialog.setNegativeButton("No")   { dialog, id ->
        }
        alertDialog.show()
    }

}