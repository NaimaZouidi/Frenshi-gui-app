package com.example.frenshichatbotandroidapp.data

import org.junit.Assert.*
import org.junit.Test

class MessageTest{
    private val myTestMsg = Message("user", "Validate my visa", "00:00")
    @Test
    fun testGetMessageSender(){
        assertEquals("user", myTestMsg.getMessageSender())
    }
    @Test
    fun testGetMessageText(){
        assertEquals("Validate my visa", myTestMsg.getMessageText())
    }
    @Test
    fun testGetMessageSendTime(){
        assertEquals("00:00", myTestMsg.getMessageSendTime())
    }
}