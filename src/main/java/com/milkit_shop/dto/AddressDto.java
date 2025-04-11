package com.milkit_shop.dto;

import com.milkit_shop.entity.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto {
    private Long addressId;

    private String address;

    private String addressDetail;

    public AddressDto(Address address) {
        this.addressId = address.getId();
        this.address = address.getAddress();
        this.addressDetail = address.getAddressDetail();
    }
}
