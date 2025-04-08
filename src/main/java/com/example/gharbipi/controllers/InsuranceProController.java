package com.example.gharbipi.controllers;


import com.example.gharbipi.entities.InsurancePro;
import com.example.gharbipi.entities.StatisticsDTO;
import com.example.gharbipi.services.InsuranceProService;
import com.example.gharbipi.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/insurancePros")
public class InsuranceProController {

    @Autowired
    private InsuranceProService insuranceProService;

    @GetMapping
    public List<InsurancePro> getAllInsurancePros() {
        return insuranceProService.getAllInsurancePros();
    }

    @GetMapping("/{id}")
    public Optional<InsurancePro> getInsuranceProById(@PathVariable Long id) {
        return insuranceProService.getInsuranceProById(id);
    }

    @PostMapping
    public InsurancePro createInsurancePro(@RequestBody InsurancePro insurancePro) {
        return insuranceProService.createInsurancePro(insurancePro);
    }

    @PutMapping
    public InsurancePro updateInsurancePro(@RequestBody InsurancePro insurancePro) {
        return insuranceProService.updateInsurancePro(insurancePro);
    }

    @PostMapping("/initialize")
    public InsurancePro initializeAmountInsurancePro(@RequestBody InsurancePro insurancePro) {
        return insuranceProService.InitializeAmountInsurancePro(insurancePro);
    }

    @DeleteMapping("/{id}")
    public void deleteInsurancePro(@PathVariable Long id) {
        insuranceProService.deleteInsurancePro(id);
    }

    @Autowired
    private StatsService statsService;


    @GetMapping("/statistics")
    public StatisticsDTO getAllStatistics() {
        return statsService.getAllStatistics();
    }
}

