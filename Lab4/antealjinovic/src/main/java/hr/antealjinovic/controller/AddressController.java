package hr.antealjinovic.controller;

import hr.antealjinovic.models.Address;
import hr.antealjinovic.models.Client;
import hr.antealjinovic.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
@AllArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public Address getAddressByCity(@RequestParam String city) {
        return addressService.findByCity(city);
    }

    @PostMapping
    public ResponseEntity<?> createAddress() {
        try {
            Address savedAddress = addressService.saveAddress();
            return ResponseEntity.ok(savedAddress);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
