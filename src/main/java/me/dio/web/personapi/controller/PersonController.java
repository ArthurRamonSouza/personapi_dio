package me.dio.web.personapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.dio.web.personapi.dto.MessageResponseDTO;
import me.dio.web.personapi.dto.PersonDTO;
import me.dio.web.personapi.exception.PersonNotFoundException;
import me.dio.web.personapi.repository.PersonRepository;
import me.dio.web.personapi.service.PersonService;

@RestController
@RequestMapping("api/v1/people")
public class PersonController {
	
	private PersonRepository personRepository;
	private PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED )
	public MessageResponseDTO createPerson(@RequestBody PersonDTO personDTO) {
		return personService.createPerson(personDTO);
	}
	
	@GetMapping
	public List<PersonDTO> listAll(){
		return personService.listAll();
		
	}

	@GetMapping("/{id}")
	public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException{
		return personService.findById(id);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) throws PersonNotFoundException{
		personService.delete(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK )
	public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody PersonDTO personDTO) throws PersonNotFoundException {
		return personService.updateById(id, personDTO);
	}
}
