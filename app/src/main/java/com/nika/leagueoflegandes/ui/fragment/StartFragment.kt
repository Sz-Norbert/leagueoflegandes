package com.nika.leagueoflegandes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.nika.leagueoflegandes.R
import com.nika.leagueoflegandes.databinding.FragmentStartBinding
import com.nika.leagueoflegandes.mvvm.StartFragmentMVVM
import com.nika.leagueoflegandes.mvvm.StartFragmentMVVM.StartFramgentViewState
import com.nika.leagueoflegandes.ui.activity.MainActivity


class StartFragment : Fragment(R.layout.fragment_start) {

    lateinit var binding: FragmentStartBinding
    val vm: StartFragmentMVVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onGoPressed()
        vm.summonerLiveData.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                StartFramgentViewState.Loading -> {
                    //showLoading
                }
                StartFramgentViewState.NoSummerFound -> {
                    //
                }
                is StartFramgentViewState.SummonerFound -> {
                    //
                }
            }
        }
    }


    private fun onGoPressed() {
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_detailFragment2)
        }
    }


}