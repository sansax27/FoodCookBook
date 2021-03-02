package com.example.myapplication.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Data.CustomAdapter
import com.example.myapplication.Data.MealListViewModel
import com.example.myapplication.Data.MealListViewModelFactory
import com.example.myapplication.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [Search.newInstance] factory method to
 * create an instance of this fragment.
 */
class Search : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var viewModel: MealListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = MealListViewModelFactory(arguments?.getString("text")!!, requireActivity().application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MealListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var recyclerview: RecyclerView = view.findViewById<ScrollView>(R.id.Scroll).findViewById(R.id.ItemList)

        viewModel.getMeals().observe(viewLifecycleOwner, Observer { meals ->
            recyclerview.layoutManager = LinearLayoutManager(requireActivity().application)
            recyclerview.adapter = CustomAdapter(meals, requireActivity().application)
        })
        super.onViewCreated(view, savedInstanceState)
    }



    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = Search()
    }
}