package com.example.baselibapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.dilshad.android.baselibapp.R
import com.dilshad.android.baselibapp.databinding.ItemInfoBinding
import com.example.baselibapp.data.model.InfoItem


class InfoAdapter(private val items: List<InfoItem>) :
    RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {

    private val lottieAnimations = listOf(
        R.raw.checklist,
        R.raw.financial_analysis,
        R.raw.marketing,
        R.raw.project_evolution_steps,
        R.raw.resource_management
    )

    class InfoViewHolder(val binding: ItemInfoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val binding = ItemInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvTitle.text = item.title
        holder.binding.tvValue.text = item.value
        holder.binding.lottieIcon.repeatCount = 5

        // Pick random animation
        val randomAnimation = lottieAnimations.random()

        holder.binding.lottieIcon.setAnimation(randomAnimation) // put a lottie file in assets
        holder.binding.lottieIcon.playAnimation()
    }

    override fun getItemCount() = items.size
}
