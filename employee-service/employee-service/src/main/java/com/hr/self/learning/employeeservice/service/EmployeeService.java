package com.hr.self.learning.employeeservice.service;

import com.hr.self.learning.employeeservice.feignclient.AddressFeignClient;
import com.hr.self.learning.employeeservice.model.AddressResponse;
import com.hr.self.learning.employeeservice.model.Employee;
import com.hr.self.learning.employeeservice.model.EmployeeResponse;
import com.hr.self.learning.employeeservice.repo.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClient;

    @Autowired
    private AddressFeignClient feignClient;

    public EmployeeResponse getEmployeeById(int id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        EmployeeResponse response = mapper.map(employee, EmployeeResponse.class);

        //call address service using RestTemplate
        ResponseEntity<AddressResponse> addressResponse = getAddressUsingRestTemplate(id);
        if (addressResponse != null && addressResponse.getStatusCode() == HttpStatus.OK && addressResponse.getBody() != null) {
            response.setAddressResponse(addressResponse.getBody());
        }

        //call address service using WebClient
        AddressResponse addressResponse1 = getAddressUsingWebClient(id);
        if (addressResponse1 != null) {
            response.setAddressResponse(addressResponse1);
        }

        //call address service using feign client
        AddressResponse addressResponse2 = getAddressUsingFeignClient(id);
        if (addressResponse2 != null) {
            response.setAddressResponse(addressResponse2);
        }

        return response;
    }

    //call address service using RestTemplate
    private ResponseEntity<AddressResponse> getAddressUsingRestTemplate(int id) {
        ResponseEntity<AddressResponse> addressResponse = restTemplate.getForEntity("/address/{empId}",
                AddressResponse.class, id);
        return addressResponse;
    }

    //call address service using web client
    private AddressResponse getAddressUsingWebClient(int id) {
        AddressResponse addressResponse1 = webClient.get().uri("/address/{empId}", id).
                retrieve().bodyToMono(AddressResponse.class).block();
        return addressResponse1;
    }

    //call address microservice using feign client
    private AddressResponse getAddressUsingFeignClient(int id) {
        AddressResponse addressResponse2 = feignClient.getAddressByEmpId(id);
        return addressResponse2;
    }
}
