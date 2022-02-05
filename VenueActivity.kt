package com.example.hirollr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class VenueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venue)

        //Various lists to populate spinners
        var regionList = arrayListOf<String>("Las Vegas", "California", "Pacific Northwest", "Southern Beltway", "East Coast")
        var vegasList = arrayListOf<String>("Aria", "Bally's", "Bellagio", "Binions", "Buffalo Bill's",
                "California","Casino Royale", "Caesar's Palace", "Circa", "Cannery", "Circus Circus",
                "The Cosmopolitan","Cromwell", "The D", "Downtown Grand", "El Cortez", "Encore", "Excalibur",
                "Fiesta", "Flamingo", "Four Queens", "Fremont", "Gold Coast", "Golden Nugget", "Green Valley",
                "Harrah's", "Linq", "Luxor", "M Resort", "Main Street", "Mandalay Bay", "MGM Grand", "Mirage",
                "New York New York", "Orleans", "Palazzo", "Palms", "Park MGM", "Paris", "Planet Hollywood",
                "Plaza", "Resorts World", "Rio", "Sahara", "Sam's Town", "Silverton", "South Point", "Strat",
                "Sun Coast", "Treasure Island", "Tropicana", "Venetian", "Wynn")
        val calList = arrayOf("Agua Caliente", "Barona", "Black Oak", "Cache Creek", "Casino Pauma",
                "Chukchansi", "Eagle Mountain", "Fantasy Springs", "Feather Falls", "Gold Country", "Graton", "Hard Rock",
                "Harrah's Norcal", "Harrah's Socal", "Havasu Landing","Jamul", "Konocti", "Lucky 7", "Lucy Bear",
                "Mono", "Morongo", "Paiute Palace", "Pala", "Pechanga", "Rain Rock", "San Manuel", "San Pablo", "Soboba",
                "Spotlight 29", "Table Mountain", "Tachi Palace", "Thunder Valley", "Tortoise Rock", "Twin Pine", "Valley View","Viejas")
        val pnwList = arrayOf("Angel of the Winds", "Chewelah", "Chinook Winds", "Emerald Queen", "Ilani", "Indian Head", "Legends", "Little Creek",
                "Lucky Eagle", "Muckleshoot", "Nooksack", "Okanogan", "Quinault", "Seven Feathers", "Shoalwater Bay", "Spirit Mountain",
                "Suquamish Clearwater", "Swinomish", "Snoqualmie", "Three Rivers", "Tulalip", "Wildhorse")
        val southList = arrayOf("Apache Gold", "Beau Rivage", "Bluewater", "Boomtown", "Coushatta", "Desert Diamond",
                "Flamingo", "Harrah's New Orleans", "Hollywood",
                "IP", "Mazatzal", "Micosukee", "Seminole", "Talking Stick", "Twin Arrows", "Palace", "Paragon", "Sam's Town", "Treasure Bay")
        val eastList = arrayOf("Bally's Atlantic City", "Borgata", "Caesars Atlantic City", "Empire", "Encore", "Foxwoods",
                "Hard Rock","Horseshoe", "MGM National Harbor", "MGM Springfield", "Mohegan Sun", "Resorts World NY", "Seneca Niagara")
        val gameType = arrayOf("Slot", "Table", "Random Number")
        val slotType = arrayOf("Pachinko", "Slot Machine", "Video Lottery", "Video Poker")
        val tableType = arrayOf("Baccarat", "BlackJack", "Craps", "Roulette", "Poker", "Big Six Wheel")
        val randomNumberType = arrayOf("Bingo", "Keno")

        //creating spinnevariables by ID
        val regionSpinnerList = findViewById<Spinner>(R.id.regionSpinner)
        val venueSpinnerList = findViewById<Spinner>(R.id.venueSpinner)
        val gameTypeSpinnerList = findViewById<Spinner>(R.id.gameTypeSpinner)
        val gameSubtypeSpinnerList = findViewById<Spinner>(R.id.gameSubtypeSpinner)

        //default settings for user selections off of spinners
        var selectedRegion = "None"
        var selectedVenue = "None"
        var selectedGameType = "None"
        var selectedSubgame = "None"

        //creating adapters for each list to be used by spinners
        val regionArrayAdapter = ArrayAdapter(this, R.layout.spinner_selected_item, regionList)
        val vegasListAdapter = ArrayAdapter(this, R.layout.spinner_selected_item, vegasList)
        val calListAdapter = ArrayAdapter(this, R.layout.spinner_selected_item, calList)
        val pnwListAdapter = ArrayAdapter(this, R.layout.spinner_selected_item, pnwList)
        val southListAdapter = ArrayAdapter(this, R.layout.spinner_selected_item, southList)
        val eastListAdapter = ArrayAdapter(this, R.layout.spinner_selected_item, eastList)
        val gameTypeAdapter = ArrayAdapter(this,R.layout.spinner_selected_item, gameType)
        val slotTypeAdapter = ArrayAdapter(this,R.layout.spinner_selected_item, slotType)
        val tableTypeAdapter = ArrayAdapter(this,R.layout.spinner_selected_item, tableType)
        val randomNumberTypeAdapter = ArrayAdapter(this,R.layout.spinner_selected_item, randomNumberType)

        regionArrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        vegasListAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        calListAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        pnwListAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        southListAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        eastListAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        gameTypeAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        slotTypeAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        tableTypeAdapter.setDropDownViewResource(R.layout.spinner_dropdown)
        randomNumberTypeAdapter.setDropDownViewResource(R.layout.spinner_dropdown)

        //setting defaulst spinner adapters
        regionSpinnerList.adapter = regionArrayAdapter
        venueSpinnerList.adapter = vegasListAdapter
        gameTypeSpinnerList.adapter = gameTypeAdapter
        gameSubtypeSpinnerList.adapter = slotTypeAdapter

        //display to user selected results (for error checking and verification)
        //val textViewEntry = findViewById<TextView>(R.id.textView2)

        //region spinner list listener
        regionSpinnerList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
            ) {
                selectedRegion = (parent.getItemAtPosition(position).toString())

                //textViewEntry.text = selectedRegion + selectedVenue + selectedGameType + selectedSubgame

                //set new sp
                if(selectedRegion == "Las Vegas")   {
                    vegasListAdapter.notifyDataSetChanged()
                    venueSpinnerList.adapter = vegasListAdapter
                }else if (selectedRegion == "California")   {
                    calListAdapter.notifyDataSetChanged()
                    venueSpinnerList.adapter = calListAdapter
                }else if (selectedRegion == "Pacific Northwest")    {
                    pnwListAdapter.notifyDataSetChanged()
                    venueSpinnerList.adapter = pnwListAdapter
                }else if (selectedRegion == "Southern Beltway") {
                    southListAdapter.notifyDataSetChanged()
                    venueSpinnerList.adapter = southListAdapter
                }else if(selectedRegion == "East Coast")    {
                    eastListAdapter.notifyDataSetChanged()
                    venueSpinnerList.adapter = eastListAdapter
                }

                venueSpinnerList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: View,
                            position: Int,
                            id: Long
                    ) {
                        selectedVenue = (parent.getItemAtPosition(position).toString())
                        //textViewEntry.text = selectedRegion + selectedVenue + selectedGameType + selectedSubgame
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }


        gameTypeSpinnerList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
            ) {
                selectedGameType = (parent.getItemAtPosition(position).toString())
                //textViewEntry.text = selectedRegion + selectedVenue + selectedGameType + selectedSubgame

                if(selectedGameType == "Slot")  {
                    gameSubtypeSpinnerList.adapter = slotTypeAdapter
                }else if(selectedGameType == "Table")    {
                    gameSubtypeSpinnerList.adapter = tableTypeAdapter
                }else if(selectedGameType == "Random Number")    {
                    gameSubtypeSpinnerList.adapter = randomNumberTypeAdapter
                }

                gameSubtypeSpinnerList.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                            parent: AdapterView<*>,
                            view: View,
                            position: Int,
                            id: Long
                    ) {

                        selectedSubgame = (parent.getItemAtPosition(position).toString())
                        //textViewEntry.text = selectedRegion + selectedVenue + selectedGameType + selectedSubgame
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        val backButton1 = findViewById<Button>(R.id.backButton)
        backButton1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val nextButton1 = findViewById<Button>(R.id.nextButton)
        nextButton1.setOnClickListener {

            val intent = Intent(this, SessionActivity::class.java)

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
    }


}