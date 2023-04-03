package com.hr.self.learning.addressservice.controller;

import com.hr.self.learning.addressservice.model.AddressResponse;
import com.hr.self.learning.addressservice.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping(value = "/address/{empId}")
    public ResponseEntity<AddressResponse> getAddressByEmpId(@PathVariable("empId") int empId) {

        AddressResponse addressResponse = addressService.getAddressByEmpId(empId);
        return ResponseEntity.status(HttpStatus.OK).body(addressResponse);

    }
}
