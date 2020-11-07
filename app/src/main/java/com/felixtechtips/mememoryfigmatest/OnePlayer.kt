package com.felixtechtips.mememoryfigmatest

import android.app.Activity
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_one_player.*
import java.lang.reflect.Field

class OnePlayer : Activity() {
    private lateinit var soundArray: Array<Array<Int>>
    private lateinit var cardArray: Array<Array<Button>>


    private fun createSoundArray(m: Int, n: Int) {
        val rawFiles = R.raw::class.java.getDeclaredFields()
        val fileList = (rawFiles + rawFiles).toMutableList()
        fileList.shuffle()
        Log.i("ajgfdkls", fileList.toString())
        val fileArray = Array(m) {Array(n) {0} }

        for (i in 0 until m){
            for(j in 0 until n){
                fileArray[i][j] = fileList[i + j].getInt(fileList[i + j])
            }
        }
        this.soundArray = fileArray
    }

    private fun createCardArray(){
        this.cardArray = arrayOf(arrayOf(card00, card01, card02, card03, card04, card05, card06, card07),
            arrayOf(card10, card11, card12, card13, card14, card15, card16, card17),
            arrayOf(card20, card21, card22, card23, card24, card25, card26, card27),
            arrayOf(card30, card31, card32, card33, card34, card35, card36, card37))
    }
    private fun setButtonSound(){
        var button1x = -1
        var button1y = -1
        var button2x = -1
        var button2y = -1

        for (i in cardArray.indices){
            for(j in cardArray[i].indices){
                val card = cardArray[i][j]
                card.setOnClickListener {
                    val mediaPlayer = MediaPlayer.create(this, soundArray[i][j])
                    mediaPlayer.start()
                    card.setBackgroundResource(R.drawable.card_flipped)
                    if(button1x == -1){
                       button1x = i
                        button1y = j
                    }
                    else{
                        button2x = i
                        button2y = j
                        if (soundArray[button1x][button1y] == soundArray[button2x][button2y]){
                            cardArray[button1x][button1y].setBackgroundResource(R.drawable.card_solved)
                            cardArray[button2x][button2y].setBackgroundResource(R.drawable.card_solved)
                        }
                        else{
                            cardArray[button1x][button1y].setBackgroundResource(R.drawable.card_unflipped)
                            cardArray[button2x][button2y].setBackgroundResource(R.drawable.card_unflipped)
                        }
                        button1x = -1
                        button1y = -1
                        button2x = -1
                        button2y = -1
                    }
                }
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_player)
        createSoundArray(4, 8)
        createCardArray()
        setButtonSound()

    }
}