package br.com.alura.dojoplaces.controller;

import br.com.alura.dojoplaces.dto.DeleteAdressesDTO;
import br.com.alura.dojoplaces.entity.Address;
import br.com.alura.dojoplaces.form.AddressData;
import br.com.alura.dojoplaces.form.RegisterForm;
import br.com.alura.dojoplaces.form.UpdateForm;
import br.com.alura.dojoplaces.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @GetMapping("/address/{id}/edit")
    public String editPage(@PathVariable Long id, Model model, UpdateForm form) {
        Address address = addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        model.addAttribute("address", new AddressData(address));
        model.addAttribute("form", form);

        return "/address-edit";
    }

    @PostMapping("/address/edit")
    public String edit(@Valid @ModelAttribute("form") UpdateForm form, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()) {
            return editPage(form.getId(), model, form);
        }

        Address address = addressRepository.findById(form.getId()).orElseThrow(EntityNotFoundException::new);
        address.updateModel(form);

        this.addressRepository.save(address);

        return "redirect:/addresses";
    }

    @PostMapping("/address/delete")
    public String delete(@Valid DeleteAdressesDTO deleteAdressesDTO, RedirectAttributes redirectAttributes){
        Address address = addressRepository.findById(deleteAdressesDTO.id()).orElseThrow(EntityNotFoundException::new);
        addressRepository.delete(address);

        redirectAttributes.addFlashAttribute("flashMessage", "Endereço deletado com sucesso!");

        return "redirect:/addresses";
    }

}
