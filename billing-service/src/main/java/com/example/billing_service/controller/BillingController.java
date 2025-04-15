package com.example.billing_service.controller;

import com.example.billing_service.model.Bill;
import com.example.billing_service.repo.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bill")
public class BillingController {

    @Autowired
    private BillRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public ResponseEntity<?> createInvoice(@RequestBody Bill invoice) {
        // ✅ 1. Check if customer exists
        String customerUrl = "http://customer-service/customer/" + invoice.getCustomerId();
        ResponseEntity<Object> customerResp = restTemplate.getForEntity(customerUrl, Object.class);

        if (!customerResp.getStatusCode().is2xxSuccessful() || customerResp.getBody() == null) {
            return ResponseEntity.badRequest().body("❌ Invalid Customer ID");
        }

        // ✅ 2. Check if car exists & is available
        String carUrl = "http://car-listing-service/car/" + invoice.getCarId();
        ResponseEntity<Map> carResp = restTemplate.getForEntity(carUrl, Map.class);

        if (!carResp.getStatusCode().is2xxSuccessful() || carResp.getBody() == null) {
            return ResponseEntity.badRequest().body("❌ Invalid Car ID");
        }

        String status = (String) carResp.getBody().get("status");
        if (!"AVAILABLE".equalsIgnoreCase(status)) {
            return ResponseEntity.badRequest().body("❌ Car is already SOLD");
        }

        // ✅ 3. Save invoice
        // invoice.setInvoiceDate(LocalDate.now());
        if (invoice.getInvoiceDate() == null) {
            invoice.setInvoiceDate(LocalDate.now());
        }

        Bill savedInvoice = repository.save(invoice);

        // ✅ 4. Mark car as sold
        String markSoldUrl = "http://car-listing-service/car/" + invoice.getCarId() + "/mark-sold";
        restTemplate.put(markSoldUrl, null);

        return ResponseEntity.ok(savedInvoice);
    }

    @GetMapping("/all")
    public List<Bill> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(null);
    }
}
