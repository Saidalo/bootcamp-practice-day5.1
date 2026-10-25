package lv.bootcamp.shelter.controller;

import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import lv.bootcamp.shelter.form.AnimalForm;
import lv.bootcamp.shelter.service.AnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AnimalPageController {
    private final AnimalService animalService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/animals")
    public String list(Model model) {
        model.addAttribute("animals", animalService.findAll());
        return "animals";
    }

    @GetMapping("/animals/new")
    public String newAnimalForm(Model model) {
        model.addAttribute("form", new AnimalForm(null, null, null, null, null, null));
        return "animals-new";

    }

    @PostMapping("/animals")
    public String create(@Valid @ModelAttribute("form") AnimalForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "animals-new";
        }
        animalService.createFromForm(form);
        return "redirect:/animals";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
