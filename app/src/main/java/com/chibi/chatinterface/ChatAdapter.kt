package com.chibi.chatinterface

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


/**
 * @author ChibiRagu on 6/18/2018
 * ChatAdapter bind all chat items to adapter and display view for both sender and receiver
 * */
class ChatAdapter(val chatMap: LinkedHashMap<String, String>) : RecyclerView.Adapter<ChatAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(viewgroup: ViewGroup, position: Int): MyViewHolder {
        val itemView = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.item_chat, viewgroup, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return chatMap.size
    }

    override fun onBindViewHolder(holder: ChatAdapter.MyViewHolder, position: Int) {

        if (chatMap.keys.toTypedArray()[position].contains("sender")) {
            holder.tv_sender.visibility = View.VISIBLE
            holder.tv_receiver.visibility = View.GONE

            holder.tv_sender.text = chatMap.get(chatMap.keys.toTypedArray()[position])
        } else {
            holder.tv_sender.visibility = View.GONE
            holder.tv_receiver.visibility = View.VISIBLE

            holder.tv_receiver.text = chatMap.get(chatMap.keys.toTypedArray()[position])
        }
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tv_sender: TextView = itemView.findViewById(R.id.tv_sender)
        val tv_receiver: TextView = itemView.findViewById(R.id.tv_receiver)

    }
}