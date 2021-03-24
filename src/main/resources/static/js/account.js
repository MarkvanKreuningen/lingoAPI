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

/*
const serverContext = '[[@{/}]]';

    $(document).ready(function () {
        $('form').submit(function(event) {
            register(event);
        });

        $(":password").keyup(function(){
            if($("#password").val() !== $("#matchPassword").val()){
                $("#globalError").show().html([[#{WachtwoordMatches.speler}]]);
            }else{
                $("#globalError").html("").hide();
            }
        });

        options = {
            common: {minChar:8},
            ui: {
                showVerdictsInsideProgressBar:true,
                showErrors:true,
                errorMessages:{
                    wordLength: [[#{error.wordLength}]],
                    wordNotEmail: [[#{error.wordNotEmail}]],
                    wordSequences: [[#{error.wordSequences}]],
                    wordLowercase: [[#{error.wordLowercase}]],
                    wordUppercase: [[#{error.wordUppercase}]],
                    wordOneNumber: [[#{error.wordOneNumber}]],
                    wordOneSpecialChar: [[#{error.wordOneSpecialChar}]]
                }
            }
        };
        $('#password').pwstrength(options);
    });

function register(event){
    console.log("hello");
    event.preventDefault();
    $(".alert").html("").hide();
    $(".error-list").html("");
    if($("#password").val() !== $("#matchPassword").val()){
        $("#globalError").show().html([[#{PasswordMatches.user}]]);
        return;
    }
    var formData= $('form').serialize();
    $.post(serverContext + "/speler/registreren",formData ,function(data){
        if(data.message === "success"){
            window.location.href = serverContext + "successRegister";
        }

    })
        .fail(function(data) {
            if(data.responseJSON.error.indexOf("MailError") > -1)
            {
                window.location.href = serverContext + "emailError";
            }
            else if(data.responseJSON.error === "spelerAlreadyExist"){
                $("#emailError").show().html(data.responseJSON.message);
            }
            else if(data.responseJSON.error.indexOf("InternalError") > -1){
                window.location.href = serverContext + "login?message=" + data.responseJSON.message;
            }
            else{
                var errors = $.parseJSON(data.responseJSON.message);
                $.each( errors, function( index,item ){
                    if (item.field){
                        $("#"+item.field+"Error").show().append(item.defaultMessage+"<br/>");
                    }
                    else {
                        $("#globalError").show().append(item.defaultMessage+"<br/>");
                    }

                });
            }
        });
}*/
