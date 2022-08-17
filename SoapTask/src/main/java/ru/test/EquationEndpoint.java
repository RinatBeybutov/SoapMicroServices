package ru.test;

import io.spring.guides.gs_producing_web_service.GetEquationRequest;
import io.spring.guides.gs_producing_web_service.GetEquationResponse;
import io.spring.guides.gs_producing_web_service.Request;
import io.spring.guides.gs_producing_web_service.Response;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EquationEndpoint {

  private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEquationRequest")
  @ResponsePayload
  public GetEquationResponse getEquationResponse(@RequestPayload GetEquationRequest getEquationRequest){
    Request requestData = getEquationRequest.getRequest();
    GetEquationResponse getEquationResponse = new GetEquationResponse();
    int a = requestData.getA();
    int b = requestData.getB();
    int c = requestData.getC();

    double D = Math.pow(b,2) - 4*a*c;
    Response response = new Response();

    if(D > 0) {
      response.setX1((-b+Math.sqrt(D)) / (2*a));
      response.setX2((-b-Math.sqrt(D)) / (2*a));
    } else if(D == 0) {
      response.setX1(-b/(2.0*a));
    } else {
      response.setError("negative discriminant");
    }
    response.setD(D);
    response.setFormula(a + "x^2+" + b + "x+" + c + "=0");

    getEquationResponse.setSolution(response);
    return getEquationResponse;
  }
}
