package com.example.frenshichatbotandroidapp.control
import androidx.compose.runtime.mutableStateListOf
import com.example.frenshichatbotandroidapp.data.Message
import com.example.frenshichatbotandroidapp.data.MessagesList
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.hamcrest.core.Every
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class MessagesControllerTest{
    private lateinit var myTestMessagesListViewModel: MessagesList
    @MockK
    private lateinit var myMessagesController: MessagesController

    private lateinit var myTestMessage: Message
    @Before
    fun setupTest(){
        myTestMessagesListViewModel = MessagesList()
        myMessagesController = MessagesController(myTestMessagesListViewModel)
        myTestMessage = Message("user", "Validate my visa", "00:00")
    }
    @Test
    fun testOnCreateDate(){
        every{
            myMessagesController.onCreateDate()
        } returns "00:00"
        assertEquals("00:00", myMessagesController)
    }

    @Test
    fun testOnMessageAdd(){
        myMessagesController.onMessageAdd("user", "Validate my visa")
        assertEquals(1, myMessagesController.messagesList.getMessageListSize())
    }
    @Test
    fun testOnMessageRetrieveSender(){
        assertEquals("user", myMessagesController.onMessageRetrieveSender(myTestMessage))
    }
    @Test
    fun testOnMessageRetrieveContent(){
        assertEquals("Validate my visa", myMessagesController.onMessageRetrieveContent(myTestMessage))
    }
    @Test
    fun testOnMessageRetrieveDate(){
        assertEquals(myMessagesController.onMessageRetrieveDate(myTestMessage), "00:00")
    }
}