package app.rosetta.springreactive.repositories;

import app.rosetta.springreactive.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImpl implements PersonRepository {

    Person michael1 = Person.builder()
            .id(1)
            .firstName("Michale1")
            .lastName("Weston")
            .build();
    Person michael2 = Person.builder()
            .id(2)
            .firstName("Michale2")
            .lastName("Weston")
            .build();
    Person michae3 = Person.builder()
            .id(3)
            .firstName("Michale3")
            .lastName("Weston")
            .build();
    Person michae4 = Person.builder()
            .id(4)
            .firstName("Michale4")
            .lastName("Weston")
            .build();

    @Override
    public Flux<Person> findAll() {
        return Flux.just(michael1, michael2, michae3, michae4);

    }

    @Override
    public Mono<Person> getById(Integer id) {
        Flux<Person> personFlux = this.findAll();
        return personFlux.filter(person -> person.getId().equals(id)).next();
    }

}
