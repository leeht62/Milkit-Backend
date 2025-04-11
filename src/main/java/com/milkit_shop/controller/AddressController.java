package com.milkit_shop.controller;

import com.milkit_shop.dto.AddressDto;
import com.milkit_shop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/address")
    public ResponseEntity<List<AddressDto>> getList(Principal principal) {
        String email = principal.getName();
        List<AddressDto> addressDtos = addressService.getList(email);
        return (addressDtos != null) ?
                ResponseEntity.ok(addressDtos) :
                ResponseEntity.badRequest().build();
    }

    @PostMapping("/address")
    public ResponseEntity<AddressDto> add(@RequestBody AddressDto dto, Principal principal) {
        String email = principal.getName();
        AddressDto addressDto = addressService.add(dto, email);
        return (addressDto != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(addressDto) :
                ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/address/{addressId}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long addressId, Principal principal) {
        String email = principal.getName();
        boolean success = addressService.delete(addressId, email);
        return success ?
                ResponseEntity.ok(null) :
                ResponseEntity.badRequest().build();
    }
}
