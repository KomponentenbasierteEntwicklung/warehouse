package com.example.warehouse.hardware;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class HardwareService {
    public List<Hardware> getHardwares() {
        return List.of(
                new Hardware(1L, "monitor", "description for monitor", new BigDecimal(300)),
                new Hardware(2L, "mouse", "description for mouse", new BigDecimal(60))
        );
    }
}
