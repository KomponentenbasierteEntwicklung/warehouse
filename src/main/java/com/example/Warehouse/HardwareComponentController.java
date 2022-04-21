package com.example.Warehouse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class HardwareComponentController {
    @GetMapping("/components")
    public HardwareComponent[] getComponents(@RequestParam(value = "name", defaultValue = "World") String name) {
        HardwareComponent[] components = {new HardwareComponent("monitor", "desc monitior", new BigDecimal(200))};
        return components;
    }
}
