package com.example.jntest.show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.jntest.R
import com.example.jntest.databinding.FragmentShowBinding
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class ShowFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentShowBinding? = null
    private lateinit var binding: FragmentShowBinding

    private lateinit var fragmentView: View
    private lateinit var navController: NavController

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShowFragment().apply {
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
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show, container, false)
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
            binding.lifecycleOwner = this@ShowFragment
            binding.showFrag = this@ShowFragment
        }
        if (this::fragmentView.isInitialized)
            navController = Navigation.findNavController(fragmentView)
    }

    fun goBack() {
        navController.popBackStack()
    }
}