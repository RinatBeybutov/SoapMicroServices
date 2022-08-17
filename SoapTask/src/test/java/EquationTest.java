import io.spring.guides.gs_producing_web_service.GetEquationRequest;
import io.spring.guides.gs_producing_web_service.GetEquationResponse;
import io.spring.guides.gs_producing_web_service.Request;
import io.spring.guides.gs_producing_web_service.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.test.EquationEndpoint;


public class EquationTest {

  EquationEndpoint equationEndpoint = new EquationEndpoint();


  @Test
  public void get2Result(){
    GetEquationRequest getEquationRequest = new GetEquationRequest();
    Request request = new Request();
    request.setA(-1);
    request.setB(7);
    request.setC(8);
    getEquationRequest.setRequest(request);

    GetEquationResponse equationResponse = equationEndpoint.getEquationResponse(getEquationRequest);

    Assertions.assertEquals(equationResponse.getSolution().getD(), 81);
    Assertions.assertEquals(equationResponse.getSolution().getFormula(),"-1x^2+7x+8=0");
    Assertions.assertEquals(equationResponse.getSolution().getX1(),-1);
    Assertions.assertEquals(equationResponse.getSolution().getX2(),8.0);

  }

  @Test
  public void get1Result() {
    GetEquationRequest getEquationRequest = new GetEquationRequest();
    Request request = new Request();
    request.setA(4);
    request.setB(4);
    request.setC(1);
    getEquationRequest.setRequest(request);

    GetEquationResponse actualResponse = equationEndpoint.getEquationResponse(getEquationRequest);

    Assertions.assertEquals(actualResponse.getSolution().getD(), 0);
    Assertions.assertEquals(actualResponse.getSolution().getFormula(),"4x^2+4x+1=0");
    Assertions.assertEquals(actualResponse.getSolution().getX1(), -0.5);
    Assertions.assertNull(actualResponse.getSolution().getX2());
  }

  @Test
  public void get0Result() {
    GetEquationRequest getEquationRequest = new GetEquationRequest();
    Request request = new Request();
    request.setA(2);
    request.setB(1);
    request.setC(1);
    getEquationRequest.setRequest(request);

    GetEquationResponse actualResponse = equationEndpoint.getEquationResponse(getEquationRequest);

    Assertions.assertEquals(actualResponse.getSolution().getD(), -7);
    Assertions.assertEquals(actualResponse.getSolution().getFormula(),"2x^2+1x+1=0");
    Assertions.assertNull(actualResponse.getSolution().getX1());
    Assertions.assertNull(actualResponse.getSolution().getX2());
    Assertions.assertEquals(actualResponse.getSolution().getError(), "negative discriminant");
  }

  @Test
  public void getNull() {
    GetEquationRequest getEquationRequest = new GetEquationRequest();
    NullPointerException exception = Assertions.assertThrows(NullPointerException.class, () -> equationEndpoint.getEquationResponse(getEquationRequest));
  }
}
