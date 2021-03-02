package com.example.myapplication.Data

import android.app.Application
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R

class CustomAdapter(private val dataset: List<MealDetail>, private val context: Application): RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mealdetailview, parent, false)
        return ViewHolder(view)
    }
    private val DatabaseAccess = MealDatabase.getDatabase(context).MealDetailDao()
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var image: ImageView
        lateinit var instructions: TextView
        lateinit var button: Button
        lateinit var name: TextView

        init {
            image = view.findViewById(R.id.imageView)
            instructions = view.findViewById(R.id.Instructions)
            name = view.findViewById(R.id.Name)
            button = view.findViewById(R.id.Favourites)
            button.setOnClickListener {
                if(button.getText().toString().equals("ADDTOFAVOURITES")) {
                    button.setText("REMOVEFROMFAVOURITES")
                    AsyncTask.execute {
                        DatabaseAccess.storefav(dataset[button.id.toInt()])
                    }
                } else {
                    button.setText("ADDTOFAVOURITES")
                    AsyncTask.execute {
                        DatabaseAccess.delete(dataset[button.id.toInt()])
                    }
                }


            }
        }
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val md = dataset[position]
        holder.instructions.text = md.instructions
        holder.name.text = md.name+"\n"+md.category
        holder.button.id = position
        val imgUri = md.image.toUri().buildUpon().scheme("https").build()
        Glide.with(holder.image.context).load(imgUri).into(holder.image)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}

