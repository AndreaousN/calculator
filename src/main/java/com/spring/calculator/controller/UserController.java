package com.spring.calculator.controller;

import com.spring.calculator.model.User;
import com.spring.calculator.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;;

@Controller
@EnableAutoConfiguration
public class UserController {

    @Autowired
    @Qualifier("UserService")
    public UserService userService;

    @GetMapping("/signup")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "signup";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword",
                    "error.user", "Slaptažodžiai nesutampa");
            return "signup";
        }

        if (userService.getUserByEmail(user.getEmail()) != null) {
            bindingResult.rejectValue("email",
                    "error.user", "Toks el.pastas jau egzistuoja");
            return "signup";
        }

        userService.saveUser(user);
        return "redirect:/";
    }
}
