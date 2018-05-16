package mum.asd.service.impl;

import mum.asd.domain.Address;
import mum.asd.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

}
