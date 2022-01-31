package me.dio.web.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.web.personapi.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	

}
