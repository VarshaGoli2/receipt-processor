package com.fetch.receipt_processor.controller;

import com.fetch.receipt_processor.service.ReceiptService;
import com.fetch.receipt_processor.model.ReceiptModel;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;
    private final Map<String, Integer> store = new HashMap<>();

    private ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public Map<String, String> processReceipt(@RequestBody ReceiptModel receipt) {
        String id = receiptService.generateReceiptID();
        int points = receiptService.calculatePoints(receipt);
        store.put(id, points);
        return Map.of("id", id);
    }

    @GetMapping("{id}/points")
    public Map<String, Object> getPoints(@PathVariable String id) {
        if (!store.containsKey(id)) {
            return Map.of("error", "Receipt not found");
        }
        return Map.of("points", store.get(id));
    }
}
