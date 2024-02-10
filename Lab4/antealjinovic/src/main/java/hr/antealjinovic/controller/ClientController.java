package hr.antealjinovic.controller;

import hr.antealjinovic.models.Client;
import hr.antealjinovic.service.ClientService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/address/{id}")
    public Client getClientByAddressId(@PathVariable Long id) {
        return clientService.findByAddressId(id);
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @PostMapping
    public ResponseEntity<?> createClient() {
        try {
            Client savedClient = clientService.saveClient();
            return ResponseEntity.ok(savedClient);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}
