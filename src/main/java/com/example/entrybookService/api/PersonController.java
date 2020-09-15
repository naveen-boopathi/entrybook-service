package com.example.entrybookService.api;

import com.example.entrybookService.model.Person;
import com.example.entrybookService.model.Response;
import com.example.entrybookService.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/person")
@RestController
public class PersonController {
    private final PersonService personService;
    private final List<Object> successMessage = new ArrayList<>();

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public Response addPerson(@NonNull @RequestBody Person person) {
        personService.addPerson(person);
        return new Response("Person added successfully!");
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public Response deletePersonById(@PathVariable("id") UUID id) {
        personService.deletePerson(id);
        return new Response("Person deleted successfully!");
    }

    @PutMapping(path = "{id}")
    public Response updatePersonById(@PathVariable("id") UUID id, @NonNull @RequestBody Person personToUpdate) {
        personService.updatePerson(id, personToUpdate);
        return new Response("Person updated successfully!");
    }

}
