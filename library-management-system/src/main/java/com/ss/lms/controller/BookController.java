package com.ss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ss.lms.entity.Book;
import com.ss.lms.service.BookService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

// GET a List a ALL BOOKs in the Library...     
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.findById(id);
    }

//ADD A NEW  BOOK 
    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        
        ResponseEntity<String> responseEntity = null;
		String message = null ;
		
		
		Book savedBook = bookService.save(book);
		
        if (savedBook != null) {
        	
        	message = "Book ADDED Successfully...  " ;
        	responseEntity = new ResponseEntity<String>( message, HttpStatus.OK); 
        	
        } else {
            
			message = " ***  ISBN already exists with Different Title and Author...  Hence can not save an un authorized copy !!!! " ;
			responseEntity = new ResponseEntity<String>( message, HttpStatus.METHOD_NOT_ALLOWED); 
			
        }
        
        return responseEntity;
    }

   
//BORROW a Book
    @PostMapping("/{bookId}/borrow/{userId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId, @PathVariable Long userId) {
    	
        Book borrowedBook = bookService.borrowBook(bookId, userId);
        
        ResponseEntity<String> responseEntity = null;
		
        if (borrowedBook != null) {
        	
        	responseEntity = new ResponseEntity<String>( borrowedBook.getMsg(), HttpStatus.OK); 
        	
            //return ResponseEntity.ok(borrowedBook);
        	
        } else {
        	
			responseEntity = new ResponseEntity<String>( borrowedBook.getMsg(), HttpStatus.NO_CONTENT); 
			
            //return ResponseEntity.badRequest().build(); // or a more descriptive error response
        }
        
        return responseEntity;
    }

//RETURN a Book
    @PostMapping("/{bookId}/return")
    public ResponseEntity<Book> returnBook(@PathVariable Long bookId) {
        Book returnedBook = bookService.returnBook(bookId);
        if (returnedBook != null) {
            return ResponseEntity.ok(returnedBook);
        } else {
            return ResponseEntity.badRequest().build(); 
        }
    }
    
    
    
  
}
