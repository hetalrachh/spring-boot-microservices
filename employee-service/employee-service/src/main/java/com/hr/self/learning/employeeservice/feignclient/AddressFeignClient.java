package com.hr.self.learning.employeeservice.feignclient;

import com.hr.self.learning.employeeservice.model.AddressResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address-service", url = "${address.service.baseUrl}")
public interface AddressFeignClient {

    @GetMapping(value = "/address/{empId}")
    public AddressResponse getAddressByEmpId(@PathVariable("empId") int empId);
}
