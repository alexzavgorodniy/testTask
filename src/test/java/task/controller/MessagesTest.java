package task.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import task.ConfigForTests;

public class MessagesTest extends ConfigForTests {

    private Messages messages = new Messages();

    @Test
    public void shouldReturnMessageByCode() {
        //given
        String code = "good.day";
        //when
        String maybeMessage = messages.getMessage(code);
        //then
        assertEquals(maybeMessage,"Добрый день");
    }
}
