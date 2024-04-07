package com.nika.leagueoflegandes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.nika.leagueoflegandes.R
import com.nika.leagueoflegandes.databinding.FragmentStartBinding
import com.nika.leagueoflegandes.mvvm.StartFragmentMVVM
import com.nika.leagueoflegandes.mvvm.view_states.StartFramgentViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : Fragment(R.layout.fragment_start) {

    lateinit var binding: FragmentStartBinding
    val vm by viewModels<StartFragmentMVVM>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onGoPressed()
        observeState()
    }

    private fun observeState() {
        vm.summonerLiveData.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                StartFramgentViewState.Loading -> {
                    showLoading()
                }

                StartFramgentViewState.NoSummerFound -> {
                    hideLoading()
                    Toast.makeText(requireContext(),"////////" ,Toast.LENGTH_SHORT).show()
                }

                is StartFramgentViewState.SummonerFound -> {
                    hideLoading()
                    val summonerName=viewState.summoner.name
                    val sumLevel=viewState.summoner.summonerLevel
                    val direction= StartFragmentDirections.actionStartFragmentToDetailFragment2(summonerName,
                        sumLevel
                    )
                    findNavController().navigate(direction)
                }

                else -> {}
            }
        }
    }


    private fun onGoPressed() {
        binding.btSearchUser.setOnClickListener {
             val sumName= binding.etSearchUser.text
            vm.getPlayer(sumName.toString())
        }
    }

    fun showLoading() {
        with(binding) {
            progressBar.visibility=View.VISIBLE
            etSearchUser.visibility=View.INVISIBLE
            btSearchUser.visibility=View.INVISIBLE;
        }
    }

    fun hideLoading() {
        with(binding) {
            progressBar.visibility=View.INVISIBLE
            etSearchUser.visibility=View.VISIBLE
            btSearchUser.visibility=View.VISIBLE;
        }
    }




}