package com.example.OnLib.controllers;

import com.example.OnLib.dao.BookRepository;
import com.example.OnLib.entities.Book;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Controller
@AllArgsConstructor
public class BookController {
    private BookRepository bookRepository;
    @GetMapping("/")
    public String showAllBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "mainPage";
    }

    @PostMapping("/books-by-title")
    public String showBooksByAuthor(@RequestParam("title") String title, Model model) {
        List<Book> books = bookRepository.findBookByTitle(title);
        model.addAttribute("books", books);
        model.addAttribute("title", title);
        return "index";
    }
    @PostMapping("/addBooks")
    public String addBooks(@RequestParam String title,
                           @RequestParam String author,
                           @RequestParam String publisher,
                           @RequestParam String genre,
                           @RequestParam String description,
                           @RequestParam int number_Of_Pages,
                           @RequestParam int year_Of_Publication,
                           @RequestParam MultipartFile image,
                           @RequestParam String text) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setGenre(genre);
        book.setDescription(description);
        book.setNumberOfPages(number_Of_Pages);
        book.setYearOfPublication(year_Of_Publication);
        book.setText(text);
        try {
            book.setImage(image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        bookRepository.save(book);
        return "redirect:/";
    }
    @GetMapping("/deleteBook")
    public String deleteBook (@RequestParam int id){
        bookRepository.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("/editBook")
    public String editBook(@RequestParam int  id, Model model){
        Optional <Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isEmpty()){
            return "redirect:/";
        }
        model.addAttribute("book", optionalBook.get());
        return "edit";
    }
    @PostMapping("/updateBook")
    public String updateBook (@RequestParam int id,
                              @RequestParam String title,
                              @RequestParam String author,
                              @RequestParam String publisher,
                              @RequestParam String genre,
                              @RequestParam String description,
                              @RequestParam int number_Of_Pages,
                              @RequestParam int year_Of_Publication,
                              @RequestParam MultipartFile image,
                              @RequestParam String text) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        optionalBook.ifPresent(t -> {
            t.setTitle(title);
            t.setAuthor(author);
            t.setPublisher(publisher);
            t.setGenre(genre);
            t.setDescription(description);
            t.setNumberOfPages(number_Of_Pages);
            t.setYearOfPublication(year_Of_Publication);
            t.setText(text);
            try {
                t.setImage(image.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            bookRepository.save(t);
        });
        return "redirect:/";
    }

    @GetMapping("/book/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        byte[] image = book.getImage();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
    @GetMapping("/read")
    public String showBookText(Model model, @RequestParam("id") Integer bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        model.addAttribute("bookText", book.getText());
        return "readBook";
    }
    @GetMapping("/romance-books")
    public String showRomanceBooks(Model model) {
        List<Book> books = bookRepository.findByGenre("Роман");
        model.addAttribute("books", books);
        return "secondPage";
    }
    @GetMapping("/detective-books")
    public String showDetectiveBooks(Model model) {
        List<Book> books = bookRepository.findByGenre("Детектив");
        model.addAttribute("books", books);
        return "secondPage";

    }
    @GetMapping("/drama-books")
    public String showDramaBooks(Model model) {
        List<Book> books = bookRepository.findByGenre("Драма");
        model.addAttribute("books", books);
        return "secondPage";

    } @GetMapping("/fantasy-books")
    public String showFentBooks(Model model) {
        List<Book> books = bookRepository.findByGenre("Фэнтези");
        model.addAttribute("books", books);
        return "secondPage";
    }
    @GetMapping("/triller-books")
    public String showTrillerBooks(Model model) {
        List<Book> books = bookRepository.findByGenre("Триллер/Ужасы");
        model.addAttribute("books", books);
        return "secondPage";
    }
    @GetMapping("/adventures-books")
    public String showAdventureBooks(Model model) {
        List<Book> books = bookRepository.findByGenre("Приключения");
        model.addAttribute("books", books);
        return "secondPage";
    }
    @GetMapping("/psyh-books")
    public String showPsyhBooks(Model model) {
        List<Book> books = bookRepository.findByGenre("Психология");
        model.addAttribute("books", books);
        return "secondPage";
    }
    @GetMapping("/fairy-books")
    public String showFairyBooks(Model model) {
        List<Book> books = bookRepository.findByGenre("Сказки");
        model.addAttribute("books", books);
        return "secondPage";
    }
    @GetMapping("/classic-books")
    public String showClassicBooks(Model model) {
        List<Book> books = bookRepository.findByGenre("Классика");
        model.addAttribute("books", books);
        return "secondPage";
    }
    @GetMapping("/mistik-books")
    public String showMistikBooks(Model model) {
        List<Book> books = bookRepository.findByGenre("Мистика");
        model.addAttribute("books", books);
        return "secondPage";
    }
}
