package com.hr.self.learning.addressservice.repo;

import com.hr.self.learning.addressservice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    public Address findAddressByEmpId(int id);
}
