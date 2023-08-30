package org.kata.tim.newMVC.controller;

import jakarta.validation.Valid;
import org.kata.tim.newMVC.model.User;
import org.kata.tim.newMVC.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/people")
public class PeopleController {

    private final UserService userService;

    @Autowired
    private PeopleController(UserService peopleServece) {
        this.userService = peopleServece;
    }

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("users", userService.findAll());
        return "index";
    }

    @GetMapping(value = "/{id}")
    public String findById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "show";
    }

    @GetMapping(value = "/create")
    public String form(@ModelAttribute("user") User user) {
        return "formTL";
    }


    @PostMapping(value = "/create")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formTL";
        }
        userService.save(user);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {

        model.addAttribute("user", userService.findById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        userService.update(id, user);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/people";

    }
}
