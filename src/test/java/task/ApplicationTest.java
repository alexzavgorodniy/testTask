package task;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ApplicationTest extends ConfigForTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void shouldRunComputeMethod() {
        //given
        String[] args = {"New","York","EET"};
        //when
        Application.main(args);
        //then
        assertEquals(args.length,3);
    }
}


