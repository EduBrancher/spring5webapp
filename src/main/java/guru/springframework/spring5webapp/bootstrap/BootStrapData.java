package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository pubRepository;

    public BootStrapData(AuthorRepository authrep, BookRepository bookrep, PublisherRepository pubrep){
        this.authorRepository = authrep;
        this.bookRepository = bookrep;
        this.pubRepository = pubrep;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher wil = new Publisher("Wiley", "1234 Penny Lane", "Abbeyton", "Midlands", "0239-3421");
        pubRepository.save(wil);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "A1234");
        generatePublication(ddd, eric, wil);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book ("J2EE Development without EJB", "B2341");
        generatePublication(noEJB, eric, wil);

        Author apos = new Author("Tom", "Apostol");
        Book calc = new Book("Calculus", "B4183");
        generatePublication(calc, apos, wil);

        System.out.println("Starting in bootstrap");
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of authors: " + authorRepository.count());
        System.out.println("Number of publishers: " + pubRepository.count());
        System.out.println("Books published by Wiley: " + wil.getPublishedBooks().size());
    }

    private void generatePublication(Book book, Author author, Publisher publisher){
        author.getBooks().add(book);
        book.getAuthors().add(author);
        book.setPublisher(publisher);
        publisher.getPublishedBooks().add(book);
        authorRepository.save(author);
        bookRepository.save(book);
        pubRepository.save(publisher);
    }
}
