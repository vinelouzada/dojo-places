package br.com.alura.dojoplaces.form.validators;

import br.com.alura.dojoplaces.form.UpdateForm;
import br.com.alura.dojoplaces.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UpdateFormValidatorTest {
    private Errors errors;
    private UpdateForm form;
    private UpdateFormValidator validator;
    private AddressRepository addressRepository;

    @BeforeEach
    public void setUp(){
        errors = mock(Errors.class);

        addressRepository = mock(AddressRepository.class);
        form = mock(UpdateForm.class);

        validator = new UpdateFormValidator(addressRepository);
    }

    @Test
    public void isValid_when_code_address_does_not_exist(){
        when(addressRepository.existsByCodeAndIdNot(form.getCode(),form.getId())).thenReturn(false);

        validator.validate(form, errors);

        verify(errors, never()).rejectValue(any(), any(), any());
    }

    @Test
    public void isValid_when_code_address_exists(){
        when(addressRepository.existsByCodeAndIdNot(form.getCode(),form.getId())).thenReturn(true);

        validator.validate(form, errors);


        verify(errors, times(1)).rejectValue("code", "","Código já existente");

    }

}