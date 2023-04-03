package com.hr.self.learning.addressservice.service;

import com.hr.self.learning.addressservice.model.Address;
import com.hr.self.learning.addressservice.model.AddressResponse;
import com.hr.self.learning.addressservice.repo.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AddressResponse getAddressByEmpId(int empId) {
        Address address = addressRepository.findAddressByEmpId(empId);
        AddressResponse response = modelMapper.map(address, AddressResponse.class);
        return response;
    }
}
