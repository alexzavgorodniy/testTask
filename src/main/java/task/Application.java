package task;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;
import task.config.SpringBootConfig;
import task.service.Computation;

@Component
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfig.class, args);
        Computation computation = new Computation();
        if (args.length != 0) {
            computation.compute(args);
        } else {
            computation.print("Array of arguments is empty");
        }
    }
}
