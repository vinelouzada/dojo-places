package br.com.alura.dojoplaces.form.validators;

import br.com.alura.dojoplaces.form.RegisterForm;
import br.com.alura.dojoplaces.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;
import static org.mockito.Mockito.*;

class RegisterFormValidatorTest {
    private Errors errors;
    private RegisterForm form;
    private RegisterFormValidator formValidator;
    private AddressRepository addressRepository;

    @BeforeEach
    public void setUp() {
        errors = mock(Errors.class);

        addressRepository = mock(AddressRepository.class);
        form = mock(RegisterForm.class);

        formValidator = new RegisterFormValidator(addressRepository);
    }

    @Test
    public void isValid_when_address_does_not_exist() {
        when(addressRepository.existsByCode(form.getCode())).thenReturn(false);

        formValidator.validate(form, errors);

        verify(errors, never()).rejectValue(any(), any(), any());
    }
    @Test
    public void isInvalid__when_address_already_exists() {
        when(addressRepository.existsByCode(form.getCode())).thenReturn(true);

        formValidator.validate(form, errors);

        verify(errors, times(1)).rejectValue("code", "","Código já existente");
    }
}