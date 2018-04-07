package task;

import org.junit.Test;
import org.mockito.Mockito;
import task.service.Computation;

public class ApplicationTest extends ConfigForTests {

    private Computation computationMock = Mockito.mock(Computation.class);

    @Test
    public void contextLoads() {
    }
}


