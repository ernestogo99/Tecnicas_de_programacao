package com.example.Crud_Spring.repository;

import com.example.Crud_Spring.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<Person,Long> {


}
