package br.com.alura.dojoplaces.form;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RegisterFormTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void should__return_true_if_register_form_is_complete_with_all_informations(){
        RegisterForm registerForm = new RegisterForm("uma ai","12345678","tv minha rua","alura");

        Set<ConstraintViolation<RegisterForm>> constraintViolations = validator.validate(registerForm);
        assertThat(constraintViolations).isEmpty();
        assertThat(registerForm.getStreet()).isEqualTo("uma ai");
        assertThat(registerForm.getCode()).isEqualTo("12345678");
        assertThat(registerForm.getNeighborhood()).isEqualTo("tv minha rua");
        assertThat(registerForm.getCity()).isEqualTo("alura");
    }

    @Test
    void should__return_false_if_register_form_is_with_invalid_code_7_numbers(){
        RegisterForm registerForm = new RegisterForm("uma ai","1234567","tv minha rua","alura");

        Set<ConstraintViolation<RegisterForm>> constraintViolations = validator.validate(registerForm);
        assertThat(constraintViolations).isNotEmpty();
        assertThat(constraintViolations).hasSize(1);
    }

    @Test
    void should__return_false_if_register_form_if_with_invalid_code_9_numbers(){
        RegisterForm registerForm = new RegisterForm("uma ai","123456789","tv minha rua","alura");

        Set<ConstraintViolation<RegisterForm>> constraintViolations = validator.validate(registerForm);
        assertThat(constraintViolations).isNotEmpty();
        assertThat(constraintViolations).hasSize(1);
    }
}