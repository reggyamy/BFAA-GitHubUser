package com.dicodingsubmission.consumerapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicodingsubmission.consumerapp.databinding.ListUserBinding

class GitHubUserAdapter:RecyclerView.Adapter<GitHubUserAdapter.ViewHolder>(){

    private val listData = ArrayList<DataUser>()

    fun setData(items: ArrayList<DataUser>) {
        listData.clear()
        listData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }


    override fun getItemCount(): Int {
        return listData.size
    }


    inner class ViewHolder(private val binding : ListUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataUser: DataUser) {
            binding.apply{
                Glide.with(itemView.context)
                    .load(dataUser.avatar_url)
                    .apply(RequestOptions().override(62, 62))
                    .into(itemPhoto)

                itemUsername.text=dataUser.login

            }
        }
    }

}


