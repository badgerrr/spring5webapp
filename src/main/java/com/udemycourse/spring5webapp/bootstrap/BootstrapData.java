package com.udemycourse.spring5webapp.bootstrap;

import com.udemycourse.spring5webapp.domain.Author;
import com.udemycourse.spring5webapp.domain.Book;
import com.udemycourse.spring5webapp.domain.Publisher;
import com.udemycourse.spring5webapp.repositories.AuthorRepository;
import com.udemycourse.spring5webapp.repositories.BookRepository;
import com.udemycourse.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");
        Publisher jack = new Publisher("jackzBookz", "1 awesome street", "awesome", "awesominington", "awe some");

        publisherRepository.save(jack);
        System.out.println("Number of Publishers: " + publisherRepository.count());

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        ddd.setPublisher(jack);
        jack.getBooks().add(ddd);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(jack);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        noEJB.setPublisher(jack);
        jack.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(jack);

        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Publishers Number of Books: " + jack.getBooks().size());
    }
}
