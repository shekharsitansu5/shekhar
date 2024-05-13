package com.ss.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.lms.entity.Book;
import com.ss.lms.entity.User;
import com.ss.lms.repository.BookRepository;
import com.ss.lms.repository.UserRepository;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    
    
    
    
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    
    
    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
    public Book findByTitleAndAuthor(String title, String author) {
        return bookRepository.findByTitleAndAuthor(title, author);
    }
    
    
    

    public Book save(Book book) {
    	
    	Book findBookByIsbn = findByIsbn(book.getIsbn());
    	
    	if(findBookByIsbn != null) {
    		
    		Book findBookByTitleAndAuthor = findByTitleAndAuthor(book.getTitle(), book.getAuthor());
    		
    		if(findBookByTitleAndAuthor != null) {
    			
    			//System.out.println("BOOK ALREADY EXISTS - ADDED  another COPY with same  'ISBN, Title, Author ' = ["+book.getIsbn()+"]");
    			return bookRepository.save(book);
    			
    		}else {
    			System.out.println(" ***  ISBN already exists with Different Title and Author...  Hence can not save an unauthorized copy !!!! \n ISBN=["+book.getIsbn()+"]");
    			return null;
    		}
    		
    	}
    	
    	System.out.println("Book ISBN dont NOT exists... Saving a copy of Book...  \n  ID = ["+book.getId()+"] & ISBN=["+book.getIsbn()+"]");
    	return bookRepository.save(book);
    	
    	
        
    }
    
    
  //BORROW a Book
    public Book borrowBook(Long bookId, Long userId) {
        Book book = findById(bookId);
        User user = userRepository.findById(userId).orElse(null);

        if (book != null ) {
        	if(user != null) {
	        	if(!book.isBorrowed()) {
		            book.setBorrowedBy(user);
		            book.setBorrowed(true);
		            return save(book);
	        	}else {
	        		book.setMsg("!!! Book already Borrowed !!!");
	        		//System.out.println("!!! Book already Borrowed !!!"); // 8. Ensure that no more than one member is borrowing the same book (same book id) at a time.
	        	}
        	}else {
        		book.setMsg("*** USER NOT Found... ");
        		//System.out.println("*** USER NOT Found... ");
        	}
        } else {
        	book = new Book();
        	book.setMsg("*** Book NOT Found... ");
        	//System.out.println("*** Book NOT Found... ");
        }
        
        
        return book;
    }

    public Book returnBook(Long bookId) {
        Book book = findById(bookId);
        if (book != null && book.isBorrowed()) {
            book.setBorrowedBy(null);
            book.setBorrowed(false);
            return save(book);
        }
        
        return null;
    }
}
