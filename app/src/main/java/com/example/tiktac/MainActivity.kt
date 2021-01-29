package com.example.tiktac

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var player=true
    var turncount=0
    var boardstatus=Array(3){IntArray(3)}
    lateinit var board:Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board= arrayOf(
                 arrayOf(button,button2,button3),
                 arrayOf(button4,button5,button6),
                 arrayOf(button7,button8,button9)
        )
        for(i in board){
            for (button in i)
            {
                button.setOnClickListener(this)
            }
        }

        initilizeboardstatus()
        resetbtn.setOnClickListener{
            player=true
            turncount=0
            initilizeboardstatus()
        }
    }

    private fun initilizeboardstatus() {
        for(i in 0..2)
        {
            for (j in 0..2)
            {
                boardstatus[i][j]==-1
            }
        }
        for(i in board)
        {
            for (button in i)
            {
                button.isEnabled=true
                button.text=""
            }
        }
    }


    override fun onClick(view: View) {
        when(view.id){
            R.id.button -> {
                upadatevalue(row=0,col=0,play=player)
            }
            R.id.button2 -> {
                upadatevalue(row=0,col=1,play=player)
        }
            R.id.button3 -> {
                upadatevalue(row=0,col=2,play=player)
            }
            R.id.button4 -> {
                upadatevalue(row=1,col=0,play=player)
            }
            R.id.button5 -> {
                upadatevalue(row=1,col=1,play=player)
            }
            R.id.button6 -> {
                upadatevalue(row=1,col=2,play=player)
            }
            R.id.button7 -> {
                upadatevalue(row=2,col=0,play=player)
            }
            R.id.button8 -> {
                upadatevalue(row=2,col=1,play=player)
            }
            R.id.button9 -> {
                upadatevalue(row=2,col=2,play=player)
            }
        }
        turncount++
        player = !player
        if(player) {updateDisplay("player X turn")}
        else {updateDisplay("player 0 turn")}

    if(turncount==9)
    { updateDisplay("Game draw")}
        checkWinner()

    }
    private fun checkWinner(){
        for (i in 0..2)
        {
            if(boardstatus[i][0]==boardstatus[i][1] && boardstatus[i][0]==boardstatus[i][2])
            {
                if(boardstatus[i][0]==1)
                {
                    updateDisplay("Player X Winner")
                    break
                } else if(boardstatus[i][0]==0)
                {
                    updateDisplay("Player 0 Winner")
                    break
                }
            }
            if(boardstatus[0][i]==boardstatus[1][i] && boardstatus[0][i]==boardstatus[2][i])
            {
                if(boardstatus[0][i]==1)
                {
                    updateDisplay("Player X Winner")
                    break
                } else if(boardstatus[0][i]==0)
                {
                    updateDisplay("Player 0 Winner")
                    break
                }
            }
            if(boardstatus[0][0]==boardstatus[1][1] && boardstatus[0][0]==boardstatus[2][2])
            {
                if(boardstatus[0][0]==1)
                {
                    updateDisplay("Player X Winner")
                    break
                } else if(boardstatus[0][0]==0)
                {
                    updateDisplay("Player 0 Winner")
                    break
                }
            }
            if(boardstatus[0][2]==boardstatus[1][1] && boardstatus[0][2]==boardstatus[2][0])
            {
                if(boardstatus[0][2]==1)
                {
                    updateDisplay("Player X Winner")
                    break
                } else if(boardstatus[0][2]==0)
                {
                    updateDisplay("Player 0 Winner")
                    break
                }
            }
        }

    }




    private fun updateDisplay(text: String) {
        displayTv.text= text
        if (text.contains("Winner")) {
            disable()
        }

    }
    private  fun disable(){
        for(i in board){
            for (button in i)
            {
                button.isEnabled=false
            }
        }
    }

    private fun upadatevalue(row: Int, col: Int, play: Boolean) {
        val text=if(player)"X" else "0"
        val value=if(player) 1 else 0
        board[row][col].apply {
            isEnabled=false
            setText(text)
        }
    boardstatus[row][col]=value
    }
}