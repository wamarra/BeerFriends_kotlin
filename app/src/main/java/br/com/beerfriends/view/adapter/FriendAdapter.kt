package br.com.beerfriends.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.beerfriends.R
import br.com.beerfriends.databinding.FriendItemBinding
import br.com.beerfriends.model.Friend

class FriendAdapter(private val items: ArrayList<Friend>) : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val itemBinding = FriendItemBinding.bind(view)

        fun bind(friend: Friend) {
            itemBinding.icon.text = friend.name.first().toString()
            itemBinding.name.text = friend.name
            itemBinding.email.text = friend.email
        }
    }

    fun update(friends: List<Friend>) {
        items.clear()
        items.addAll(friends)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.friend_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}