package com.example.loan.servicing.demo.repository;

import com.example.loan.servicing.demo.model.PersonalInfoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfoModel, Long> {
}
