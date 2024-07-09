( () => {

    const cep = document.getElementById("code");
    const neighborhood = document.getElementById("neighborhood");
    const city = document.getElementById("city");
    const street = document.getElementById("street");

    cep.addEventListener("blur", () => {
        const cepValue = cep.value;
        fetch(`https://viacep.com.br/ws/${cepValue}/json/`)
            .then(response => response.json())
            .then(data => {
                neighborhood.value = data.bairro
                city.value = data.localidade
                street.value = data.logradouro
            })
            .catch(error => {
                console.log(error);
            });
    });

})()

