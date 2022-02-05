package com.example.hirollr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class SessionActivity : AppCompatActivity() {

    var buyIn = 0
    var willingToLose = 0
    var profitGoal = 0
    var luckLevel = 0
    var comfortLevel = 0
    var sobrietyLevel = 0
    var walkAmount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session)

        //get data from previous activities (either venue or play)
        var selectedRegion = intent.getStringExtra("region")
        var selectedVenue = intent.getStringExtra("venue")
        var selectedGameType = intent.getStringExtra("gameType")
        var selectedSubgame = intent.getStringExtra("subgame")
        buyIn = intent.getIntExtra("buyIn", 0)
        willingToLose = intent.getIntExtra("willingToLose", 0)
        profitGoal = intent.getIntExtra("profitGoal", 0)
        luckLevel = intent.getIntExtra("luckLevel", 0)
        comfortLevel = intent.getIntExtra("comfortLevel", 0)
        sobrietyLevel = intent.getIntExtra("sobrietyLevel", 0)
        walkAmount = intent.getIntExtra("walkAmount", 0)

        //variable for storing user entries in EditText boxes
        val buyInTextEntry = findViewById<EditText>(R.id.buyInEntry)
        val willingToLoseEntry = findViewById<EditText>(R.id.willingToLoseEntry)
        val profitGoalEntry = findViewById<EditText>(R.id.profitGoalEntry)

        //seekbar title data
        val luckTitleData = findViewById<TextView>(R.id.luckTitle)
        val comfortTitleData = findViewById<TextView>(R.id.comfortTitle)
        val sobrietyTitleData = findViewById<TextView>(R.id.sobrietyTitle)

        //actual seekbar sliders
        val luckSeekBarData = findViewById<SeekBar>(R.id.luckSeekBar)
        val comfortSeekBarData = findViewById<SeekBar>(R.id.comfortSeekBar)
        val sobrietySeekBarData = findViewById<SeekBar>(R.id.sobrietySeekBar)

        val playButton = findViewById<Button>(R.id.playButton)
        val venueButton = findViewById<Button>(R.id.venueButton)

        buyInTextEntry.setText(buyIn.toString())
        willingToLoseEntry.setText(willingToLose.toString())
        profitGoalEntry.setText(profitGoal.toString())
        luckTitleData.text = "Luck Level: " + luckLevel + "%"
        comfortTitleData.text = "Comfort Level: " + comfortLevel + "%"
        sobrietyTitleData.text = "Sobriety Level: " + sobrietyLevel + "%"
        luckSeekBarData.progress = luckLevel
        comfortSeekBarData.progress = comfortLevel
        sobrietySeekBarData.progress = sobrietyLevel


        luckSeekBarData.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                luckLevel = progress
                luckTitleData.text = "Luck Level: " + luckLevel + "%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                luckLevel = seekBar.progress
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                luckLevel = seekBar.progress
            }
        })

        comfortSeekBarData.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                comfortLevel = progress
                comfortTitleData.text = "Comfort Level: " + comfortLevel + "%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                comfortLevel = seekBar.progress
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                comfortLevel = seekBar.progress
            }
        })

        sobrietySeekBarData.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sobrietyLevel = progress
                sobrietyTitleData.text = "Sobriety Level: " + sobrietyLevel + "%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                sobrietyLevel = seekBar.progress
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                sobrietyLevel = seekBar.progress
            }
        })




        venueButton.setOnClickListener {

            val intent = Intent(this, VenueActivity::class.java)
            intent.putExtra("region", selectedRegion)
            intent.putExtra("venue", selectedVenue)
            intent.putExtra("gameType", selectedGameType)
            intent.putExtra("subgame", selectedSubgame)
            intent.putExtra("buyIn", 0)
            intent.putExtra("willingToLose", 0)
            intent.putExtra("profitGoal", 0)
            intent.putExtra("luckLevel", 0)
            intent.putExtra("comfortLevel", 0)
            intent.putExtra("sobrietyLevel", 0)
            intent.putExtra("walkAmount", 0)

            startActivity(intent)
        }


        playButton.setOnClickListener {
            //resetting after multiple clicks
            walkAmount = 0;

            buyIn = buyInTextEntry.text.toString().toInt()
            willingToLose = willingToLoseEntry.text.toString().toInt()
            profitGoal = profitGoalEntry.text.toString().toInt()

            var diff = profitGoal.toDouble()
            var applied = diff*((luckLevel.toDouble() + comfortLevel.toDouble() + sobrietyLevel.toDouble())/300.00)
            walkAmount = (buyIn + applied).toInt()
            //walkAmount = ((profitGoal - buyIn)*((luckLevel + comfortLevel + sobrietyLevel)/300)) + buyIn


            if(willingToLose > buyIn)  {
                Toast.makeText(applicationContext, "Your Willing-To-Lose amount exceeds your Buy-In.", Toast.LENGTH_SHORT).show()
            }
            else    {
                /*Toast.makeText(applicationContext, "Luck: " + luckLevel + ", Comfort: " + comfortLevel + ". Sobriety: " + sobrietyLevel +
                        "------------------------------------------------------" +
                        "Diff: " + diff + ", Applied: " + applied + ", WA: " + walkAmount +
                        ", BI: " + buyIn + ", WTL: " + willingToLose + ", PG: " + profitGoal + ", WA: " + walkAmount +

                    selectedRegion + selectedVenue + selectedGameType + selectedSubgame, Toast.LENGTH_LONG).show()*/


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

        }

    }
}