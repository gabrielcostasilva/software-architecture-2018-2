package edu.utfpr.cp.sa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import edu.utfpr.cp.sa.entity.Country;

@Component
public interface CountryDAO extends JpaRepository<Country, Long> { }
