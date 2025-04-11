package com.milkit_shop.entity;

import com.milkit_shop.dto.AddressDto;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Address {
    @Id
    @GeneratedValue
    @Column(name="address_id")
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Column
    private String address;

    @Column
    private String addressDetail;

    public Address(AddressDto dto, Member member) {
        this.address = dto.getAddress();
        this.addressDetail = dto.getAddressDetail();
        this.member = member;
    }
}
