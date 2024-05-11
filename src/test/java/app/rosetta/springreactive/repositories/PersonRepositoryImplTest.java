package app.rosetta.springreactive.repositories;

import java.util.List;

import org.junit.jupiter.api.Test;

import app.rosetta.springreactive.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepositoryImplTest {
    PersonRepository personRepository = new PersonRepositoryImpl();

    @Test
    void testMonoByIdBlock() {
        Mono<Person> personMono = personRepository.getById(1);
        System.out.println(personMono.toString());
    }

    @Test
    void testGetByIdSubscriber() {
        Mono<Person> personMono = personRepository.getById(3);
        personMono.subscribe(
                person -> {
                    System.out.println(person.toString());
                });
    }

    @Test
    void testMapOperation() {
        Mono<Person> personMono = personRepository.getById(1);

        personMono.map(person -> {
            return person.getFirstName();
        }).subscribe(firstName -> {
            System.out.println(firstName);
        });
    }

    @Test
    void testFluxBlockFirst() {
        Flux<Person> personFlux = personRepository.findAll();
        Person person = personFlux.blockFirst();
        System.out.println(person.toString());

    }

    @Test
    void testFluxSubscriber() {
        Flux<Person> personFlux = personRepository.findAll();
        personFlux.subscribe(
                person -> System.out.println(person.toString()));
    }

    @Test
    void testFluxMap() {
        Flux<Person> personFlux = personRepository.findAll();
        personFlux.map(Person::getFirstName).subscribe(
                person -> System.out.println(person.toString()));
    }

    @Test
    void testFluxToList() {
        Flux<Person> personFlux = personRepository.findAll();
        Mono<List<Person>> listMono = personFlux.collectList(); // this a single element a list
        listMono.subscribe(
                list -> {
                    list.forEach(person -> System.out.println(person.getFirstName()));
                });
    }

    @Test
    void testFilterOnName() {
        Flux<Person> personFlux = personRepository.findAll();
        personFlux.filter(person -> person.getFirstName().equals("Michale1")) // this a single element a list
                .subscribe(
                        person -> {
                            System.out.println(person.toString());
                        });
    }

    @Test
    void testFindPersonByIdNotFound() {
        Flux<Person> personFlux = personRepository.findAll();

        final Integer id = 8;

        Mono<Person> personMono = personFlux.filter(person -> person.getId() == id).single()
                .doOnError(throwable -> {
                    System.out.println("Error occurred in flux");
                    System.out.println(throwable.toString());
                });
        personMono.subscribe(person -> {
            System.out.println(person.toString());
        }, throwable -> {
            System.out.println("Error occurred in the mono");
            System.out.println(throwable.toString());
        });
    }

    @Test
    void testFindAll() {

    }

    @Test
    void testGetById() {
        Mono<Person> personMono = personRepository.findAll().filter(person -> person.getFirstName().equals("Michale1"))
                .next();
        personMono.subscribe(
                person -> {
                    System.out.println(person.toString());
                });
    }
}
