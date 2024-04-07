package com.nika.leagueoflegandes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nika.leagueoflegandes.R
import com.nika.leagueoflegandes.databinding.FragmentDetailBinding
import com.nika.leagueoflegandes.mvvm.DetailFragmentMVVM
import com.nika.leagueoflegandes.mvvm.view_states.DetailFragmentViewState
import com.nika.leagueoflegandes.remote.models.match.details.Participant
import com.nika.leagueoflegandes.ui.adapters.MatchHistoryAdapter
import com.nika.leagueoflegandes.util.Util.Companion.ICON_BASE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment():Fragment(R.layout.fragment_detail) {

    private lateinit var binding : FragmentDetailBinding
    private  val args : DetailFragmentArgs by  navArgs()
    private val vm by viewModels<DetailFragmentMVVM> ()
    var sumIcon=0
    private lateinit var matchHistoryAdapter: MatchHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding=FragmentDetailBinding.inflate(inflater,container,false)


        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getSummonerFromDb(args.sumName)
        loadViews()

    }

    private fun loadMatchHistory() {
        vm.participantLiveData.observe(viewLifecycleOwner){
            println("detail fragment , size of List ${it.size}")
            if (it.size==0){
                loading()
            }
            setUpAdapter(it)
        }
    }

    private  fun getSummonerFromDb(summonerName:String){
        vm.getUserFromdb(summonerName).observe(viewLifecycleOwner) {
            if (it != null) {
                Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
                sumIcon = it.profileIconId
                vm.getSummonerDetail(it.id)
                vm.getSumMatchList(it.puuid, it.name)
            } else {
                Toast.makeText(requireContext(), "null", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun loadViews(){
        vm.sumDetailsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is DetailFragmentViewState.Loading -> {
                    loading()
                }

                is DetailFragmentViewState.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    println(it.message)
                }

                is DetailFragmentViewState.SummonerLoaded -> {
                    with(binding) {
                        tvSumName.text = args.sumName
                        tvSumLev.text = args.sumLevel.toString()
                        tvSumLev.text = args.sumLevel.toString()
                        tvSumTier.text = it.summoner[0].tier
                        tvLose.text = "${it.summoner[0].losses} L"
                        tvWin.text = "${it.summoner[0].wins} W"
                        tvLp.text = "${it.summoner[0].leaguePoints} LP"
                        loadDivisionIcon(it.summoner[0].tier)
                        Glide.with(requireContext())
                            .load("${ICON_BASE}$sumIcon.png")
                            .into(ivSumIcon)
                        println(sumIcon)
                        loadMatchHistory()
                    }
                }
            }
        }
    }

    private fun loadDivisionIcon(rank: String) {
        val resId = requireContext().resources.getIdentifier(rank.lowercase(), "drawable", requireContext().packageName)
        val ic="${rank.lowercase()}.jpg"
        binding.ivRank.setImageResource(resId)
        println(resId)
        println(ic)

    }


    private fun loading() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
        }
    }

    private fun setUpAdapter(matchList:List<Participant>){
        matchHistoryAdapter=MatchHistoryAdapter()
        matchHistoryAdapter.setMatchList(matchList)
        binding.recyclerView.apply {
            layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter=matchHistoryAdapter
        }
    }
}