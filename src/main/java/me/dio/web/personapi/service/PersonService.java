package me.dio.web.personapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dio.web.personapi.dto.MessageResponseDTO;
import me.dio.web.personapi.dto.PersonDTO;
import me.dio.web.personapi.exception.PersonNotFoundException;
import me.dio.web.personapi.mapper.PersonMapper;
import me.dio.web.personapi.model.Person;
import me.dio.web.personapi.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository personRepository;
	
	private final PersonMapper personMapper = PersonMapper.INSTANCE;
	
	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public MessageResponseDTO createPerson(PersonDTO personDTO) {
		Person personToSave = personMapper.toModel(personDTO);
		Person saved = personRepository.save(personToSave);
		return new MessageResponseDTO("Created person with ID " + saved.getId());
	}

	public List<PersonDTO> listAll() {
		List<Person> allPeople = personRepository.findAll();
		return allPeople.stream()
				.map(personMapper::toDTO)
				.collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = verifyIfExist(id);
		 //Optional<Person> optionalPerson = personRepository.findById(id);
		 //if(optionalPerson.isEmpty()) {
		 //	 throw new PersonNotFoundException(id);
		 //}
		 return personMapper.toDTO(person);
	}

	public void delete(Long id) throws PersonNotFoundException {
		verifyIfExist(id);
		
		personRepository.deleteById(id);
	}
	
	public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
		verifyIfExist(id);
		
		Person personToUpdate = personMapper.toModel(personDTO);
		Person updated = personRepository.save(personToUpdate);
		return new MessageResponseDTO("Updated person with ID " + updated.getId());
	}
	
	private Person verifyIfExist(Long id) throws PersonNotFoundException {
		return personRepository.findById(id)
			.orElseThrow(()->new PersonNotFoundException(id));
	}
}