package com.example.myapplication.UI

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.CustomAdapter1
import com.example.myapplication.Data.FavouritesViewModel
import com.example.myapplication.Data.FavouritesViewModelFactory
import com.example.myapplication.R

class ViewFavourites : Fragment() {

    private lateinit var viewmodel: FavouritesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = FavouritesViewModelFactory(requireActivity().application)
        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(FavouritesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_favourites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.ItemList)
        viewmodel.getmeals().observe(viewLifecycleOwner, Observer {
            Log.i("DDDD", "AAAA")
            recyclerView.layoutManager = LinearLayoutManager(requireActivity().application)
            recyclerView.adapter = CustomAdapter1(it, requireActivity().application) })

    }

    companion object {

        @JvmStatic
        fun newInstance() = ViewFavourites()
    }
}