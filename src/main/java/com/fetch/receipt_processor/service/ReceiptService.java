package com.fetch.receipt_processor.service;

import com.fetch.receipt_processor.model.ReceiptModel;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ReceiptService {

    public int calculatePoints(ReceiptModel receipt) {
        int points = 0;
        // 1
        String retailer = receipt.getRetailer();
        points += retailer.replaceAll("[^a-zA-Z0-9]", "").length();

        // 2
        double x = receipt.getTotal();
        // double cents = x % 1;
        if (x == Math.floor(x)) {
            points += 50;
        }

        // 3
        if (x % 0.25 == 0) {
            points += 25;
        }

        // 4
        points += (receipt.getItems().size() / 2) * 5;

        // 5
        for (ReceiptModel.Item item : receipt.getItems()) {
            if (item.getShortDescription().trim().length() % 3 == 0) {
                points += Math.ceil(item.getPrice() * 0.2);
            }
        }

        // 6 - alternatively can take split('-') too
        String str = receipt.getPurchaseDate();
        int day = Integer.parseInt(str.substring(8, 10));
        if (day % 2 == 1) {
            points += 6;
        }

        // 7
        String[] time = receipt.getPurchaseTime().split(":");
        int hours = Integer.parseInt(time[0]);
        if (hours >= 14 && hours <= 16) {
            points += 10;
        }

        return points;

    }

    public String generateReceiptID() {
        return UUID.randomUUID().toString();
    }
}
