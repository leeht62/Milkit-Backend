package com.milkit_shop.service;

import com.milkit_shop.dto.AddressDto;
import com.milkit_shop.entity.Address;
import com.milkit_shop.entity.Member;
import com.milkit_shop.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private MemberService memberService;


    public List<AddressDto> getList(String email) {
        List<Address> addressList = addressRepository.findAllByEmail(email);
        List<AddressDto> addressDtos = new ArrayList<>();
        for (Address address: addressList) {
            addressDtos.add(new AddressDto(address));
        }

        return addressDtos;
    }

    public AddressDto add(AddressDto dto, String email) {
        Member member = memberService.findMemberByEmail(email);

//        Address address = new Address(dto, member);
        Address address = Address.createAddress(dto, member);
        Address saved = addressRepository.save(address);
        return new AddressDto(saved);
    }

    public boolean delete(Long addressId, String email) {
        Member member = memberService.findMemberByEmail(email);

        Address address = addressRepository.findById(addressId).orElse(null);

        if (address == null) {
            return false;
        }
        if (address.getMember().getId() != member.getId()) {
            return false;
        }
        addressRepository.delete(address);
        return true;
    }
}
