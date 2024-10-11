package com.example.loan.servicing.demo.repository;

import com.example.loan.servicing.demo.model.CollateralModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollateralRepository extends JpaRepository<CollateralModel, Long> {

}
