package com.milkit_shop.dto;

import com.milkit_shop.entity.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private String address;

    private String addressDetail;

    public AddressDto(Address address) {
        this.address = address.getAddress();
        this.addressDetail = address.getAddressDetail();
    }
}
