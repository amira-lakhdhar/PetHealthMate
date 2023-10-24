package com.example.amiraweb.Repository;

import com.example.amiraweb.Entity.Soin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SoinRepository extends JpaRepository<Soin, Long>, JpaSpecificationExecutor<Soin> {
}