package br.com.alura.dojoplaces.form;

import br.com.alura.dojoplaces.entity.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public final class RegisterForm{

        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 100, message = "O nome deve ser menor que 100 caracteres")
        private String street;

        @NotBlank(message = "O CEP é obrigatório")
        @Pattern(regexp = "\\d{8}", message = "O Cep deve conter exatamente 8 dígitos")
        private String code;

        @NotBlank(message = "O Bairro é obrigatório")
        private String neighborhood;

        @NotBlank(message = "A cidade é obrigatória")
        private String city;

        public RegisterForm(String street, String code, String neighborhood, String city) {
               this.street = street;
               this.code = code;
               this.neighborhood = neighborhood;
               this.city = city;
        }

        public Address toModel(){
                return new Address(this.street, this.code, this.neighborhood, this.city);
        }

        public String getStreet() {
                return street;
        }

        public String getCode() {
                return code;
        }

        public String getNeighborhood() {
                return neighborhood;
        }

        public String getCity() {
                return city;
        }
}
