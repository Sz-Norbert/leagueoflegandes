package com.nika.leagueoflegandes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.nika.leagueoflegandes.R
import com.nika.leagueoflegandes.databinding.FragmentDetailBinding
import com.nika.leagueoflegandes.util.Util.Companion.BUNDLE_KEY

class DetailFragment():Fragment(R.layout.fragment_detail) {

    private lateinit var binding : FragmentDetailBinding
    private  val args : DetailFragmentArgs by  navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=FragmentDetailBinding.inflate(inflater,container,false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSumLev.text=args.sumLev
        binding.tvSumName.text=args.sumName



    }
}