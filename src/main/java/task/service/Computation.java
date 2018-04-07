package task.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import task.controller.Messages;

@Component
public class Computation {

    private static final Logger LOG = LoggerFactory.getLogger(Computation.class);

    @Autowired
    private Messages messages = new Messages();

    String defineCity(String[] args) {
        LOG.info("Defining city");
        String city = args[0];
        StringBuilder builder = new StringBuilder();
        for (int index = 1; index < args.length; index++) {
            builder = builder.append("_").append(args[index]);
        }
        return city.concat(builder.toString());
    }

    ZoneId defineTimeZoneByZoneId(String[] args) {
        LOG.info("Defining zoneId by user input time zone");
        String userZoneId = args[args.length - 1];
        ZoneId zoneId = null;
        String[] availableIDs = TimeZone.getAvailableIDs();
        for (String availableID : availableIDs) {
            if (userZoneId.equalsIgnoreCase(availableID)) {
                zoneId = TimeZone.getTimeZone(availableID).toZoneId();
            }
        }
        return zoneId;
    }

    ZoneId defineZoneIdByCity(String city) {
        LOG.info("Defining zoneId by name of entered city");
        ZoneId zoneId = null;
        String[] availableIDs = TimeZone.getAvailableIDs();
        for (String availableID : availableIDs) {
            if (availableID.contains(city)) {
                zoneId = ZoneId.of(availableID);
            }
        }
        return zoneId;
    }

    String formationOfGreetingMessage(ZoneId zoneId) {
        LOG.info("Forming string for greeting message which depends of hour of the day");
        StringBuilder builder = new StringBuilder();
        Integer hourInEnteredCity = LocalDateTime.now(zoneId).getHour();
        if (hourInEnteredCity >= 6 && hourInEnteredCity < 9) {
            builder.append(messages.getMessage("good.morning"));
        } else if (hourInEnteredCity >= 9 && hourInEnteredCity < 19) {
            builder.append(messages.getMessage("good.day"));
        } else if (hourInEnteredCity >= 19 && hourInEnteredCity < 23) {
            builder.append(messages.getMessage("good.evening"));
        } else if (hourInEnteredCity >= 23 || (hourInEnteredCity >= 0 && hourInEnteredCity < 6)) {
            builder.append(messages.getMessage("good.night"));
        }
        return builder.append(", ").toString();
    }

    public void compute(String[] args) {
        LOG.info("Command line parameters are passed to compute method");
        ZoneId zoneByUserZoneId = defineTimeZoneByZoneId(args);
        if (zoneByUserZoneId != null) {
            args = Arrays.copyOf(args, args.length - 1);
        }
        String city = defineCity(args);
        ZoneId zoneIdByCity = defineZoneIdByCity(city);
        if (zoneIdByCity == null && zoneByUserZoneId != null) {
            zoneIdByCity = zoneByUserZoneId;
        }
        if (zoneIdByCity == null && zoneByUserZoneId == null) {
            zoneIdByCity = ZoneId.of("GMT");
        }
        String greetingMessage = formationOfGreetingMessage(zoneIdByCity);
        print(greetingMessage + city);
    }

    public void print(String string) {
        System.out.println(string + "!");
    }
}

