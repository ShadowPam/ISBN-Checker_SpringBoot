package com.isbn.isbnchecker.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isbn.isbnchecker.service.IsbnService;

@RequestMapping("api/isbncheck")
@RestController
public class ISBNController {
    private final IsbnService isbnService;

    @Autowired
    public ISBNController(IsbnService isbnService) {
        this.isbnService = isbnService;
    }

    @GetMapping(path = "{isbn}")
    public ResponseEntity<String> checkISBNValidity(@PathVariable("isbn") String isbn) {
        boolean isISBN = isbnService.checkISBN(isbn);

        if (!isISBN) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST).body("Expected input should be a sequence of number.");
        }

        return isbnService.resolve(isbn);
    }
}
