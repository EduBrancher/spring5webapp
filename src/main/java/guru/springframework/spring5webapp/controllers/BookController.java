package guru.springframework.spring5webapp.controllers;

import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private final BookRepository bookrepo;

    public BookController(BookRepository bookrepo) {
        this.bookrepo = bookrepo; //since we have a constructor and this is a spring managed component, when we instantiate this,
                                  //spring will inject a book repository into the controller
    }

    @RequestMapping("/books")
    public String getBooks(Model model){
        //view is going to get a copy of the model

        model.addAttribute("books", bookrepo.findAll()); //at runtime, when a request to url /books is received, we will add all books
                                                           //in bookrepo to the model because spring will execute the method getBooks
                                                           //and assign the list of books to the attribute books in the model. The attribute
                                                           //name is being returned to tell spring we want to apply the view "books".

        return ("books/list");
    }
}
