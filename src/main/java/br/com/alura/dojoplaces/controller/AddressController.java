package br.com.alura.dojoplaces.controller;

import br.com.alura.dojoplaces.dto.DeleteAdressesDTO;
import br.com.alura.dojoplaces.entity.Address;
import br.com.alura.dojoplaces.form.AddressData;
import br.com.alura.dojoplaces.form.RegisterForm;
import br.com.alura.dojoplaces.form.UpdateForm;
import br.com.alura.dojoplaces.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    @Transactional
    @PostMapping("/register")
    public String create(@Valid @ModelAttribute("form") RegisterForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()) {
            return createPage(form, model);
        }

        if(addressRepository.existsByCode(form.getCode())) {
            redirectAttributes.addFlashAttribute("flashMessage", "O código já está em uso");
            redirectAttributes.addFlashAttribute("typeMessage", "flash-message-danger");
            return "redirect:/register";
        }

        this.addressRepository.save(form.toModel());

        redirectAttributes.addFlashAttribute("flashMessage", "Lugar cadastrado com sucesso!");
        redirectAttributes.addFlashAttribute("typeMessage", "flash-message-success");

        return "redirect:/addresses";
    }


    @GetMapping("/address/{id}/edit")
    public String editPage(@PathVariable Long id, Model model, UpdateForm form) {
        Address address = addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        model.addAttribute("address", new AddressData(address));
        model.addAttribute("form", form);

        return "/address-edit";
    }

    @Transactional
    @PostMapping("/address/edit")
    public String edit(@Valid @ModelAttribute("form") UpdateForm form, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()) {
            return editPage(form.getId(), model, form);
        }

        if(addressRepository.existsByCode(form.getCode())) {
            redirectAttributes.addFlashAttribute("flashMessage", "O código já está em uso");
            redirectAttributes.addFlashAttribute("typeMessage", "flash-message-danger");
            return "redirect:/address/%d/edit".formatted(form.getId());
        }

        Address address = addressRepository.findById(form.getId()).orElseThrow(EntityNotFoundException::new);

        address.updateModel(form);
        this.addressRepository.save(address);

        redirectAttributes.addFlashAttribute("flashMessage", "Lugar editado com sucesso!");
        redirectAttributes.addFlashAttribute("typeMessage", "flash-message-success");
        return "redirect:/addresses";
    }

    @PostMapping("/address/delete")
    public String delete(@Valid DeleteAdressesDTO deleteAdressesDTO, RedirectAttributes redirectAttributes){
        Address address = addressRepository.findById(deleteAdressesDTO.id()).orElseThrow(EntityNotFoundException::new);
        addressRepository.delete(address);

        redirectAttributes.addFlashAttribute("flashMessage", "Endereço deletado com sucesso!");
        redirectAttributes.addFlashAttribute("typeMessage", "flash-message-success");

        return "redirect:/addresses";
    }
}
