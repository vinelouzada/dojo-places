package br.com.alura.dojoplaces.form.validators;

import br.com.alura.dojoplaces.form.RegisterForm;
import br.com.alura.dojoplaces.repository.AddressRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class RegisterFormValidator implements Validator {

    private final AddressRepository addressRepository;

    public RegisterFormValidator(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        RegisterForm form = (RegisterForm) target;

        if (addressRepository.existsByCode(form.getCode())){
            errors.rejectValue("code","","Código já existente");
        }
    }
}
