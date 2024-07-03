package br.com.alura.dojoplaces.form.validators;

import br.com.alura.dojoplaces.form.UpdateForm;
import br.com.alura.dojoplaces.repository.AddressRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UpdateFormValidator implements Validator {

    private final AddressRepository addressRepository;

    public UpdateFormValidator(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return UpdateForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        UpdateForm form = (UpdateForm) target;

        if (addressRepository.existsByCodeAndIdNot(form.getCode(), form.getId())) {
            errors.rejectValue("code", "","Código já existente");
        }

    }
}
