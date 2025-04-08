package com.example.gharbipi.entities;


import jakarta.persistence.*;


@Entity

public class InsurancePro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String proName;
    private String description;
    private double premiumAmount;

    @ManyToOne
    private InsuranceProType insuranceProType; // Foreign key relationship

    // Constructors, getters, and setters
    public InsurancePro() {}

    public InsurancePro(Long id, String proName, String description, double premiumAmount, InsuranceProType insuranceProType) {
        this.id = id;
        this.proName = proName;
        this.description = description;
        this.premiumAmount = premiumAmount;
        this.insuranceProType = insuranceProType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public InsuranceProType getInsuranceProType() {
        return insuranceProType;
    }

    public void setInsuranceProType(InsuranceProType insuranceProType) {
        this.insuranceProType = insuranceProType;
    }
}

