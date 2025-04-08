package com.example.gharbipi.services;

import com.example.gharbipi.entities.InsurancePro;
import com.example.gharbipi.entities.InsuranceProType;
import com.example.gharbipi.repos.InsuranceProRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class InsuranceProService {

    @Autowired
    private JavaMailSender javaMailSender;

    // Method to send an email
    public void sendEmail(String to, String subject, String text) {
        try {
            System.out.println("will send email now " + to);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
            System.out.println("Email sent successfully to " + to);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Base premium amount
    private static final double BASE_PREMIUM = 1000.0;

    // Method to calculate premiumAmount based on risk
    public double calculatePremiumAmount(double risk) {
        // Validate risk (must be between 1 and 10)
        if (risk < 1 || risk > 10) {
            throw new IllegalArgumentException("Risk must be between 1 and 10.");
        }

        // Step 1: Calculate risk multiplier
        double riskMultiplier = 1.0 + (risk - 1) * 0.2; // risk 1 = 1.0, risk 10 = 2.8

        // Step 2: Multiply base premium by risk multiplier
        double premium = BASE_PREMIUM * riskMultiplier;

        // Step 3: Apply a flat fee for high-risk policies
        if (riskMultiplier > 2.0) {
            premium += 500.0; // Add a flat fee for high-risk policies
        }

        // Step 4: Apply a discount for mid-range risks
        if (riskMultiplier >= 1.5 && riskMultiplier <= 2.0) {
            premium *= 0.9; // 10% discount
        }

        // Step 5: Apply a small adjustment based on risk level
        premium += risk * 10; // Add $10 for each risk level

        // Step 6: Round to 2 decimal places
        premium = Math.round(premium * 100.0) / 100.0;

        return premium;
    }

    @Autowired
    private InsuranceProRepository insuranceProRepository;

    public List<InsurancePro> getAllInsurancePros() {
        return insuranceProRepository.findAll();
    }

    public Optional<InsurancePro> getInsuranceProById(Long id) {
        return insuranceProRepository.findById(id);
    }

    public InsurancePro createInsurancePro(InsurancePro insurancePro) {
        System.out.println("Creating InsurancePro..."); // Debugging
        InsurancePro savedInsurancePro = insuranceProRepository.save(insurancePro);
        System.out.println("InsurancePro saved with ID: " + savedInsurancePro.getId()); // Debugging

        String emailSubject = "New InsurancePro Created";
        String emailText = "A new InsurancePro has been created : " + savedInsurancePro.getInsuranceProType().getName();
        System.out.println("Preparing to send email..."); // Debugging

        this.sendEmail("azizghest@gmail.com", emailSubject, emailText);
        System.out.println("Email sending process completed."); // Debugging

        return savedInsurancePro;
    }

    public void deleteInsurancePro(Long id) {
        insuranceProRepository.deleteById(id);
    }

    public InsurancePro updateInsurancePro(InsurancePro insurancePro) {
        InitializeAmountInsurancePro(insurancePro);
        System.out.println("Creating InsurancePro..."); // Debugging
        InsurancePro savedInsurancePro = insuranceProRepository.save(insurancePro);
        System.out.println("InsurancePro saved with ID: " + savedInsurancePro.getId()); // Debugging

        String emailSubject = "InsurancePro Updated";
        String emailText = "An InsurancePro has been updated: " + savedInsurancePro.getInsuranceProType().getName();
        System.out.println("Preparing to send email..."); // Debugging

        this.sendEmail("azizghest@gmail.com", emailSubject, emailText);
        System.out.println("Email sending process completed."); // Debugging

        return savedInsurancePro;
    }


    // Method to check if the number of InsurancePro of the same type exceeds 40% of all InsurancePro
    private boolean exceedsTypeLimit(InsurancePro insurancePro) {
        // Get the total number of InsurancePro objects
        long totalInsurancePros = insuranceProRepository.count();

        // Get the number of InsurancePro objects of the same type
        long sameTypeCount = insuranceProRepository.countByInsuranceProType(insurancePro.getInsuranceProType());

        // Calculate 40% of the total count
        double threshold = totalInsurancePros * 0.4;

        // Check if the same type count exceeds the threshold
        return sameTypeCount > threshold;
    }

    // Method to initialize and adjust premiumAmount
    public InsurancePro InitializeAmountInsurancePro(InsurancePro insurancePro) {
        // Calculate the base premium amount
        double premiumAmount = calculatePremiumAmount(Double.parseDouble(insurancePro.getInsuranceProType().getRisk()));

        // Check if the number of InsurancePro of the same type exceeds 40% of all InsurancePro
        if (exceedsTypeLimit(insurancePro)) {
            premiumAmount *= 1.5; // Increase premium by 1.5 times
        }

        // Set the premiumAmount
        insurancePro.setPremiumAmount(premiumAmount);
        return insurancePro;
    }

}