package app.rosetta.springreactive.repositories;

import app.rosetta.springreactive.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonRepository {
    Mono<Person> getById(Integer id);

    Flux<Person> findAll();

}
