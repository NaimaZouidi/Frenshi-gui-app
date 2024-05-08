package com.example.frenshichatbotandroidapp.data

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
class MessagesListTest{
    private lateinit var myTestMessagesListViewModel: MessagesList
    @Before
    fun setupTest(){
        myTestMessagesListViewModel = MessagesList()
    }
    @Test
    fun testAddMessageToList(){
        myTestMessagesListViewModel.addMessageToList("user", "Validate my visa", "00:00")
        assertEquals(1, myTestMessagesListViewModel.messagesList.size)
    }
}