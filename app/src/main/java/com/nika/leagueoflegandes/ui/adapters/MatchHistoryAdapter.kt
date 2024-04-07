package com.nika.leagueoflegandes.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nika.leagueoflegandes.R
import com.nika.leagueoflegandes.databinding.SumMatchCardBinding
import com.nika.leagueoflegandes.remote.models.match.details.Participant
import com.nika.leagueoflegandes.util.Util
import com.nika.leagueoflegandes.util.Util.Companion.CHAMPION_ICON_BASE
import com.nika.leagueoflegandes.util.Util.Companion.ITEM_ICON_BASE

class MatchHistoryAdapter():RecyclerView.Adapter<MatchHistoryAdapter.MatchViewHolder>() {

    class MatchViewHolder(val binding :SumMatchCardBinding): RecyclerView.ViewHolder(binding.root)
    private var matchList:List<Participant> = mutableListOf()
    fun setMatchList(matchList:List<Participant>){
        this.matchList=matchList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(SumMatchCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        println("size of matchList ${matchList.size}")

        return matchList.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match=matchList[position]
        holder.binding.apply {
            tvKda.text="${match.kills}/${match.deaths}/${match.assists}"
            Glide.with(holder.itemView)
                .load("$CHAMPION_ICON_BASE${match.championName}.png")
                .into(ivChamp)
            if (!match.win){
                if (!match.win){
                    cardview.setCardBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.loseRed))
                }
            }
            loadImgWithGlide(holder,holder.binding.ivItem1,match.item0)
            loadImgWithGlide(holder,holder.binding.ivItem2,match.item1)
            loadImgWithGlide(holder,holder.binding.ivItem3,match.item2)
            loadImgWithGlide(holder,holder.binding.ivItem4,match.item3)
            loadImgWithGlide(holder,holder.binding.ivItem5,match.item4)
            loadImgWithGlide(holder,holder.binding.ivItem6,match.item5)
            loadImgWithGlide(holder,holder.binding.ivItem7,match.item6)

        }
    }

    private fun loadImgWithGlide(
        holder: MatchViewHolder,
        view: ImageView,
        item: Int
    ) {
        Glide.with(holder.itemView)
            .load("$ITEM_ICON_BASE$item.png")
            .into(view)
    }


}