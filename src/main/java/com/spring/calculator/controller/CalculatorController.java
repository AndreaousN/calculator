package com.spring.calculator.controller;

import com.spring.calculator.model.Number;
import com.spring.calculator.service.NumberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

// Web kontroleris leidzia viduje naudoti @RequestMapping.
// @RestController anotacija nurodo , jog pvz: String tipo rezultatas turi buti ispaudinaas klientui toks koks yra
//@RestController

//@RestController nergrazina view
//Kadangi mums reikia grąžinti view pagal Spring MVC, naudojame @Controller
@Controller
// Zymi konfiguracijos komponenta viduje leidzia kurti bean per metodus su @Bean anotacija.
// Si klases lygio anotacija nurodo spring karkasui "Atspeti" konfiguracija.
// Rementis priklausomybemis ( Jar bibliotekomis ) kurios programuotojas itraukia i projekta ( Pom.xml
// Siuo atveju ji veikia kartu su main metodu.
@EnableAutoConfiguration
public class CalculatorController {
    @Autowired
    @Qualifier("NumberService")
    public NumberService numberService;

    // Marsrutizavimo informacija. šiuo atveju, ji nurodo Spring karkasui,
    // jog visas HTTP užklausas, kurių kelias yra "/" apdoros metodas home().
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String home(Model model) {
        // Jeigu model "number" nepraeina validacijos - per ji grazinamos validacijos klaidos į vaizda (view).
        model.addAttribute("number", new Number());
        // Grąžiname JSP failą, turi būti talpinami 'webapp -> WEB-INF -> jsp' aplanke
        return "calculator";
    }
    // kadangi skaiciuotuvo forma naudoja POST metoda, cia irgi nurodome POST.
//    @RequestMapping(method = RequestMethod.POST, value = "/calculate")
    // trumpesnis POST variantas
    @PostMapping("/calculate")
    // naudotis @RequestParam reikia kai raktai skiriasi nuo frontend ir backend
        String calculate(@Valid @ModelAttribute("number") Number e, BindingResult br,
                         @RequestParam HashMap<String, String> numbers, ModelMap modelMap) {
        if(br.hasErrors()) {
            return "calculator";
        } else {

            int num1 = Integer.parseInt(numbers.get("num1"));
            int num2 = Integer.parseInt(numbers.get("num2"));
            String operation = numbers.get("operation");

            System.out.println("Results: " + numbers.entrySet());
//    String calculate(int num1, int num2, String operation, ModelMap modelMap) {
            int result = 0;

            if (operation.equals("+")) {
                result = num1 + num2;
            } else if (operation.equals("-")) {
                result = num1 - num2;
            } else if (operation.equals("*")) {
                result = num1 * num2;
            } else if (operation.equals("/")) {
                result = num1 / num2;
            }
            // ModelMap objektas naudojamas siųsti reikšmes iš Spring MVC controller į JSP
            modelMap.put("num1", num1);
            modelMap.put("num2", num2);
            modelMap.put("operation", operation);
            modelMap.put("result", result);

            numberService.save(new Number(num1, num2, operation, result));

            return "calculate";
        }
    }
    @GetMapping("/numbers")
    public String getAllNumbers(Model model) {
        model.addAttribute("numbers", numberService.getAll());
        return "numbers";
    }
    @GetMapping("/view{id}")
    public String getById(@RequestParam("id") int id, Model model) {
        model.addAttribute("number", numberService.getById(id));
        return "number";
    }
    @GetMapping("/delete{id}")
    public String delete(@RequestParam("id") int id, Model model) {
        numberService.delete(id);
        model.addAttribute("numbers", numberService.getAll());
        return "numbers";
    }
    @GetMapping("/refresh{id}")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("number", numberService.getById(id));
        return "refresh";
    }
    @PostMapping("/refreshNumber")
    public String updateNumber(@ModelAttribute("number") Number number) {
        numberService.update(number);
        return "redirect:/view?id=" + number.getId();
    }
}
