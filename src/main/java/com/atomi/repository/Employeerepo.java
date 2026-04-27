package com.atomi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atomi.entity.Employee;

public interface Employeerepo extends JpaRepository<Employee, Integer> {

}
