package com.example.hangman_app

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class RecycleViewAdapter(private val playerList: ArrayList<Player>) : RecyclerView.Adapter<RecycleViewAdapter.Views>() {

    private lateinit var db: FirebaseFirestore

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Views {
        val scoreView = LayoutInflater.from(parent.context).inflate(R.layout.score_item,
            parent, false)
        return Views(scoreView)
    }

    public fun deleteScore(position: Int) {
        db = FirebaseFirestore.getInstance()
        val player : Player = playerList[position]
        playerList.removeAt(position)
        db.collection("names")
            .document("${player.name+player.mode}").delete()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: Views, position: Int) {
        val player : Player = playerList[position]
        holder.name.text = player.name
        holder.gameScore.text = player.score
        holder.modeLabel.text = player.mode
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    public class Views(scoreItem: View) : RecyclerView.ViewHolder(scoreItem) {
        val name : TextView = itemView.findViewById(R.id.nameLabel)
       val modeLabel: TextView = itemView.findViewById(R.id.modeLabel1)
        val gameScore: TextView = itemView.findViewById(R.id.scoreLabel)

    }
}