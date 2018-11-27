package iks.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import iks.springframework.spring5webapp.model.Author;
import iks.springframework.spring5webapp.model.Book;
import iks.springframework.spring5webapp.model.Publisher;
import iks.springframework.spring5webapp.model.repositories.AuthorRepository;
import iks.springframework.spring5webapp.model.repositories.BookRepository;
import iks.springframework.spring5webapp.model.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{
	
	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;
	
	

	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	private void initData() {
		
		//Eric
		Author eric = new Author("Eric", "Evans");
		Publisher harper = new Publisher("Haper Collins", "Somewhere in California");
		publisherRepository.save(harper);
		Book ddd = new Book("Domain Driven Design", "1234", harper);
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		
		authorRepository.save(eric);
		bookRepository.save(ddd);
		
		//Rod
		Author rod = new Author("Rod", "Johnson");
		Publisher worx = new Publisher("Worx", "Somewhere in Texas");
		publisherRepository.save(worx);
		Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
		rod.getBooks().add(noEJB);
		
		authorRepository.save(rod);
		bookRepository.save(noEJB);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		initData();
	}
}
