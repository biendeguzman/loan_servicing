package com.example.loan.servicing.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PersonalInfoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String phoneNumber;
    private String emailAddress;
    private String telephoneNumber;

//    public PersonalInfoModel() {}

//    private int calculateCreditScore() {
//        boolean hasFullName = fullName != null && !fullName.isEmpty();
//        boolean hasPhoneNumber = phoneNumber != null && !phoneNumber.isEmpty();
//        boolean hasEmailAddress = emailAddress != null && !emailAddress.isEmpty();
//        boolean hasTelephoneNumber = telephoneNumber != null && !telephoneNumber.isEmpty();
//
//        if (hasFullName && hasPhoneNumber && hasEmailAddress && hasTelephoneNumber) {
//            return 100;
//        } else if (hasPhoneNumber && hasEmailAddress && hasTelephoneNumber) {
//            return 90;
//        } else if (hasFullName && hasEmailAddress && hasTelephoneNumber) {
//            return 90;
//        } else if (hasFullName && hasPhoneNumber) {
//            return 70;
//        }
//        return 0; // Default score if none of the criteria are met
//    }
}
