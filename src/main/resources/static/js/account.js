function nieuwAccount(){
    let wachtwoord1 = document.getElementById("wachtwoord").value;
    let wachtwoord2 = document.getElementById("herhaalWachtwoord").value;

    if (wachtwoord1 === wachtwoord2) {
        if (wachtwoord1.length >= 8){
            console.log("asdasdasd")
            const formData = new FormData(document.getElementById("registreerAccount"));
            const encData = new URLSearchParams(formData.entries());
            const fetchOptions = {
                method: 'POST',
                body: encData
            };
            fetch("/speler/registreren", fetchOptions)
                .then(function (response){
                    if (response.status === 200){
                        alert("Je account is toegevoegd.");
                        return response.json()
                    }  else if (response.status === 400) {
                        alert("Gebruikersnaam is al in gebruik");
                    } else if (response.status === 404) {
                        throw "Kan gebruiker niet vinden"
                    }
                })
        } else if (wachtwoord1.length < 8){
            alert("Je wachtwoord is te kort")
        }
    } else {
        alert("Wachtwoorden zijn niet gelijk!")
    }
}