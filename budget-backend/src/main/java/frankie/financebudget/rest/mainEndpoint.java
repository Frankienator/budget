package frankie.financebudget.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class mainEndpoint {

    @GetMapping
    public List<String> hello() {
        return List.of("hello", "my", "dearest", "friend");
    }

}
