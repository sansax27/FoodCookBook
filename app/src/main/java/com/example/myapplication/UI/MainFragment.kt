package com.example.myapplication.UI

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.myapplication.Data.MealDatabase
import com.example.myapplication.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment() {
    private lateinit var viewmodel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var instructions: TextView = view.findViewById(R.id.Instructions)
        var name: TextView = view.findViewById(R.id.Name)
        var imageview: ImageView = view.findViewById(R.id.imageView)
        var buttonadd: Button = view.findViewById(R.id.Favourites)
        viewmodel.getMeal().observe(viewLifecycleOwner, { meal ->
            instructions.text = meal.instructions
            name.text = meal.name+"\n"+meal.category
            val imgUri = meal.image.toUri().buildUpon().scheme("https").build()
            Glide.with(imageview.context).load(imgUri).into(imageview)
            buttonadd.setOnClickListener {
                Log.i("FFF","RRRR")
                var DatabaseAccess = MealDatabase.getDatabase(requireActivity().application).MealDetailDao()
                if(buttonadd.getText().toString().equals("ADDTOFAVOURITES")) {
                    Log.i("FFFEE","RRRR")
                    buttonadd.setText("REMOVEFROMFAVOURITES")
                    AsyncTask.execute{
                        DatabaseAccess.storefav(meal)
                    }
                } else {
                    buttonadd.text = "ADDTOFAVOURITES"
                    Log.i("FFFEEwww","RRRR")
                    AsyncTask.execute {
                        DatabaseAccess.delete(meal)
                    }
                }
            }
        })


        var search: EditText = view.findViewById(R.id.SendSearch)
        var button: Button = view.findViewById(R.id.button5)
        button.setOnClickListener {
            val bundle = bundleOf("text" to search.text.toString())
            view.findNavController().navigate(R.id.send, bundle)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}