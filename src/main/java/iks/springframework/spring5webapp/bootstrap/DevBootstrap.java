package iks.springframework.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import iks.springframework.spring5webapp.model.Author;
import iks.springframework.spring5webapp.model.Book;
import iks.springframework.spring5webapp.model.Publisher;
import iks.springframework.spring5webapp.model.repositories.AuthorRepository;
import iks.springframework.spring5webapp.model.repositories.BookRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent>{
	
	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	
	

	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}

	private void initData() {
		
		//Eric
		Author eric = new Author("Eric", "Evans");
		Publisher harper = new Publisher("Haper Collins", "Somewhere", "California", "California");
		Book ddd = new Book("Domain Driven Design", "1234", harper);
		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);
		
		authorRepository.save(eric);
		bookRepository.save(ddd);
		
		//Rod
		Author rod = new Author("Rod", "Johnson");
		Publisher worx = new Publisher("Worx", "Somewhere", "Virginia", "Virginia");
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
