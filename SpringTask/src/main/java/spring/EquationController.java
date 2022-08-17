package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EquationController {

  @Autowired
  EquationService equationService;

  @GetMapping(value = "/calc")
  public ResponseEntity<?> getSolution(@RequestParam int a,
      @RequestParam int b,
      @RequestParam int c) {
    return equationService.getSolution(a,b,c);
  }

  @RequestMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

}
