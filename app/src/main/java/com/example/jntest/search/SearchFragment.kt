package com.example.jntest.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jntest.R
import com.example.jntest.databinding.FragmentSearchBinding
import com.example.jntest.search.adapter.RepositoriesRecyclerAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class SearchFragment : Fragment(), RepositoriesRecyclerAdapter.IRepositoriesRecyclerCallBack {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentSearchBinding? = null
    private lateinit var binding: FragmentSearchBinding

    private lateinit var fragmentView: View
    private lateinit var navController: NavController

    @Inject
    lateinit var recyclerAdapter: RepositoriesRecyclerAdapter

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        _binding?.let { binding = it }

        val view: View? = _binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentView = view

        initCode()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initCode() {
        if (this::binding.isInitialized) {
            binding.lifecycleOwner = this@SearchFragment
            binding.searchFrag = this@SearchFragment
        }
        if (this::fragmentView.isInitialized)
            navController = Navigation.findNavController(fragmentView)

        initRecycler()
        defineRecycler()
    }

    /**
     * recycler view
     */
    private fun initRecycler() = context?.let {
        val layoutManager = LinearLayoutManager(it)
        binding.rvSaveRepositories.layoutManager = layoutManager
    }


    private fun defineRecycler() {
        context?.let { recyclerAdapter = RepositoriesRecyclerAdapter(it, this@SearchFragment) }
        binding.rvSaveRepositories.setHasFixedSize(true)
        binding.rvSaveRepositories.adapter = recyclerAdapter
    }

    override fun onRecyclerClick(position: Int) {
        navController.navigate(R.id.action_searchFragment_to_showFragment)
    }
}