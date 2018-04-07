package task.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.ZoneId;
import org.junit.Test;
import task.ConfigForTests;

public class ComputationTest extends ConfigForTests {

    private Computation computation = new Computation();

    @Test
    public void shouldReturnCityBySingleWord() {
        //given
        String[] args = {"Moscow"};
        //when
        String maybeDefinedCity = computation.defineCity(args);
        //then
        assertEquals(maybeDefinedCity,"Moscow");
    }

    @Test
    public void shouldReturnCityByTwoWords() {
        //given
        String[] args = {"New","York"};
        //when
        String maybeDefinedCity = computation.defineCity(args);
        //then
        assertEquals(maybeDefinedCity,"New_York");
    }

    @Test
    public void shouldReturnZoneIdByZoneIdWord() {
        //given
        String[] args = {"New","York","EET"};
        ZoneId zoneId = ZoneId.of("EET");
        //when
        ZoneId maybeZoneId = computation.defineTimeZoneByZoneId(args);
        //then
        assertEquals(maybeZoneId,zoneId);
    }

    @Test
    public void shouldReturnZoneIdByCity() {
        //given
        String city = "Moscow";
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        //when
        ZoneId maybeZoneId = computation.defineZoneIdByCity(city);
        //then
        assertEquals(maybeZoneId,zoneId);
    }

    @Test
    public void shouldFormGreetingMessage() {
        //given
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        //when
        String maybeGreetingMessage = computation.formationOfGreetingMessage(zoneId);
        //then
        assertTrue(maybeGreetingMessage.contains(", "));
    }
}
