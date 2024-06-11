package br.com.alura.dojoplaces.controller;

import br.com.alura.dojoplaces.entity.Address;
import br.com.alura.dojoplaces.form.AddressData;
import br.com.alura.dojoplaces.form.RegisterForm;
import br.com.alura.dojoplaces.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AddressController {

    private AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping("/addresses")
    public String listAll(Model model) {
        List<AddressData> addresses = addressRepository.findAll().stream()
                .map(AddressData::new)
                .toList();

        model.addAttribute("addresses", addresses);

        return "/addresses";
    }

    @GetMapping("/register")
    public String createPage(RegisterForm form, Model model) {
        model.addAttribute("form", form);
        return "/register";
    }

    @PostMapping("/register")
    public String create(@Valid @ModelAttribute("form") RegisterForm form, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()) {
            return createPage(form, model);
        }

        this.addressRepository.save(form.toModel());

        return "redirect:/addresses";
    }


    @GetMapping("/addresses/{id}/edit")
    public String edit(@PathVariable Long id, Model model, RegisterForm form) {
        Address address = addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        model.addAttribute("address", new AddressData(address));
        model.addAttribute("form", form);

        return "/address-edit";
    }


}
