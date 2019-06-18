package com.chibi.chatinterface

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author ChibiRagu on 6/18/2019
 * Chat Activity simple chat interface like whats app
 * */
class ChatActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var chatAdapter: ChatAdapter
    private var chatMap: LinkedHashMap<String, String> = LinkedHashMap()
    private var senderCount = 0
    private var receiverCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnClickListeners()
        initChatAdapter()
        img_nav_down.visibility = View.GONE
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.img_receive -> {
                chatMap.put("receiver$receiverCount", "Received")
                receiverCount++
                updateAdapter()
            }

            R.id.img_send -> {
                chatMap.put("sender$senderCount", et_input.text.toString())
                senderCount++
                et_input.text.clear()
                updateAdapter()
            }

            R.id.img_nav_down -> {
                updateAdapter()
                img_nav_down.visibility = View.GONE
            }
        }
    }


    // Update the adapter and scroll the position for each items inserted
    private fun updateAdapter() {
        chatAdapter.notifyItemRangeInserted(chatMap.size, chatMap.size);
        rv_chat_list.smoothScrollToPosition(chatMap.size);
    }

    // Set all view need to be respond to click
    private fun setOnClickListeners() {
        img_receive.setOnClickListener(this)
        img_send.setOnClickListener(this)
        img_nav_down.setOnClickListener(this)
    }

    // init chat rv
    private fun initChatAdapter() {
        val llm = LinearLayoutManager(this)
        llm.stackFromEnd = true     // items gravity sticks to bottom
        llm.reverseLayout = false
        rv_chat_list.layoutManager = llm
        rv_chat_list.setHasFixedSize(true)
        chatAdapter = ChatAdapter(chatMap)
        rv_chat_list.adapter = chatAdapter
    }
}