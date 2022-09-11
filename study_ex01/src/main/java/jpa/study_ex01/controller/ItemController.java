package jpa.study_ex01.controller;


import jpa.study_ex01.item.Book;
import jpa.study_ex01.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form",new BookForm());
        return "items/createItemForm";
    }



    @PostMapping("items/{itemId}/edit")
    public String updateitem(@ModelAttribute("form") BookForm form){

        Book book = new Book();
        book.setPrice(form.getPrice());
        book.setName(form.getName());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        return "redirect:form";

    }

}
