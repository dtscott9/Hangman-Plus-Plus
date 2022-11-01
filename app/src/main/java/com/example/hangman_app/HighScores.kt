package com.example.hangman_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class HighScores : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var playerScoreList: ArrayList<Player>
    private lateinit var adapter: RecycleViewAdapter
    private lateinit var db : FirebaseFirestore
    private lateinit var backButton : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_scores)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        playerScoreList = arrayListOf()
        adapter = RecycleViewAdapter(playerScoreList)
        recyclerView.adapter = adapter
        backButton = findViewById(R.id.backButton)

        EventChangeListener()

        backButton.setOnClickListener{
            val intent = Intent(this, MainMenu::class.java)
            startActivity(intent)
            finish();
        }

    }




    private fun EventChangeListener() {
        db = FirebaseFirestore.getInstance()
        db.collection("names")
            .addSnapshotListener(object : EventListener<QuerySnapshot>{
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.e("firestore error", error.message.toString())
                        return
                    }
                    for (dc: DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED){
                            playerScoreList.add(dc.document.toObject(Player::class.java))
                            val swipeDelete = object : swipeToDelete() {
                                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                                    when(direction) {
                                        ItemTouchHelper.LEFT -> {
                                            adapter.deleteScore(viewHolder.adapterPosition)
                                        }
                                    }

                                }
                            }

                            val touchHelper = ItemTouchHelper(swipeDelete)
                            touchHelper.attachToRecyclerView(recyclerView)

                        }

                    }
                    adapter.notifyDataSetChanged()
                }

            })


    }
}