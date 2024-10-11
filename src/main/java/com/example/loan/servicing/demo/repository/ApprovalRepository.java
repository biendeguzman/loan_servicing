package com.example.loan.servicing.demo.repository;

import com.example.loan.servicing.demo.model.ApprovalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalRepository extends JpaRepository<ApprovalModel, Long> {
}
