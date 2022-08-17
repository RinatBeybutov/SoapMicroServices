package spring;

import io.spring.guides.gs_producing_web_service.EquationsPort;
import io.spring.guides.gs_producing_web_service.EquationsPortService;
import io.spring.guides.gs_producing_web_service.GetEquationRequest;
import io.spring.guides.gs_producing_web_service.GetEquationResponse;
import io.spring.guides.gs_producing_web_service.Request;
import io.spring.guides.gs_producing_web_service.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EquationService {

  public ResponseEntity<?> getSolution(int a, int b, int c) {
    Request request = new Request();
    request.setA(a);
    request.setB(b);
    request.setC(c);

    return getSolutionFromSoapTask(request);
  }

  private ResponseEntity<?> getSolutionFromSoapTask(Request request) {
    GetEquationRequest getEquationRequest = new GetEquationRequest();
    getEquationRequest.setRequest(request);

    EquationsPortService service = new EquationsPortService();
    EquationsPort port = service.getEquationsPortSoap11();

    GetEquationResponse result = port.getEquation(getEquationRequest);

    return convertFromGetEquationResponseToEquationResponseDto(result);
  }

  private ResponseEntity<?> convertFromGetEquationResponseToEquationResponseDto(GetEquationResponse result) {
    Response solution = result.getSolution();

    if(solution.getError() != null) {
      EquationResponseDto0 equationResponseDto0 = new EquationResponseDto0();
      equationResponseDto0.setError(solution.getError());
      equationResponseDto0.setD(solution.getD());
      equationResponseDto0.setFormula(solution.getFormula());
      return new ResponseEntity<>(equationResponseDto0, HttpStatus.OK);
    }

    if(solution.getError() == null && solution.getX2() == null) {
      EquationResponseDto1 equationResponseDto1 = new EquationResponseDto1();
      equationResponseDto1.setFormula(solution.getFormula());
      equationResponseDto1.setD(solution.getD());
      equationResponseDto1.setX1(solution.getX1());
      return new ResponseEntity<>(equationResponseDto1, HttpStatus.OK);
    }

    EquationResponseDto2 responseDto = new EquationResponseDto2();
    responseDto.setFormula(solution.getFormula());
    responseDto.setD(solution.getD());
    responseDto.setX1(solution.getX1());
    responseDto.setX2(solution.getX2());

    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }
}
