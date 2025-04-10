package com.project._global.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project._global.entity.Phones;

@Repository
public interface PhonesRepository extends JpaRepository<Phones, UUID> {

}
