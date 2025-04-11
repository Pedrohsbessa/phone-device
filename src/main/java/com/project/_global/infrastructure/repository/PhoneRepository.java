package com.project._global.infrastructure.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project._global.domain.entity.Phone;
import com.project._global.domain.entity.PhoneState;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, UUID> {

    Page<Phone> findByBrand(String brand, Pageable pageable);

    Page<Phone> findByState(PhoneState state, Pageable pageable);

}
