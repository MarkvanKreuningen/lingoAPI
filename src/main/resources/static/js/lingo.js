const naamSpeler1 = window.sessionStorage.getItem("naamSpeler1");
const naamSpeler2 = window.sessionStorage.getItem("naamSpeler2");
const tijdPerBeurt = window.sessionStorage.getItem("tijdPerBeurt");

function inladenSpel(){
    let woordByChar = [];
    let atmWoord = [];
    console.log(naamSpeler2+naamSpeler1+tijdPerBeurt);
    const formData = new FormData();
    formData.append("naamSpeler1", naamSpeler1);
    formData.append("naamSpeler2", naamSpeler2);
    formData.append("tijdPerBeurt", tijdPerBeurt);
    const encData = new URLSearchParams(formData.entries());
    const fetchOptions = {
        method: 'POST',
        body: encData
    }

    fetch("api/start2spelers", fetchOptions)
        .then(response => response.json())
        .then(function(response){
            woordByChar = Array.from(response.teRadenWoord);
            atmWoord.push(woordByChar[0])
            for (let i = 0; i<woordByChar.length; i++){
                atmWoord.push("*")
            }
            if (response.beurt === "speler1")
                document.getElementById("aandeB").innerHTML = response.naamSpeler1+" is aan de beurt";
            else document.getElementById("aandeB").innerHTML = response.naamSpeler2+" is aan de beurt";


            vulTeRadenWoord(woordByChar, atmWoord)
        })
    // timeLeft(10);
}

function vulTeRadenWoord(lengthWoord, WoordVanSpelers){
    let i;
    document.getElementById("beschrijving").innerHTML = "Het volgende woord heeft "+lengthWoord.length+" aantal letters";
    //document.getElementById("punten").innerHTML = "Totaal aantal punten tot nu toe:\n"+"<br>"+100;
    for (i = 0; i < lengthWoord.length; i++){
        if (lengthWoord.length === 5){
            gevuldeLingoArray = document.getElementById("komenWeinigLettersTeStaan");
            gevuldeLingoArray.innerHTML += '<p class="col circle-sm normal">'+WoordVanSpelers[i]+'</p>'
        }
        else if (lengthWoord.length === 6){
            gevuldeLingoArray = document.getElementById("komenMiddelLettersTeStaan");
            gevuldeLingoArray.innerHTML += '<p class="col circle-sm normal">'+WoordVanSpelers[i]+'</p>'
        } else if (lengthWoord.length > 6){
            gevuldeLingoArray = document.getElementById("komenVeelLettersTeStaan");
            gevuldeLingoArray.innerHTML += '<p class="col circle-sm normal">'+WoordVanSpelers[i]+'</p>'
        }
    }
}

function timeLeft(timeleft) {
    const time = timeleft;
    let timings = timeleft
    var downloadTimer = setInterval(function(){
    if(timeleft <= 0){
        clearInterval(downloadTimer);
        document.getElementById("myBtn").disabled = true;
        document.getElementById("myBtn").classList.remove("buttonSubmit");
        teLaat();
    }
    document.getElementById("progressBar").value = timings - timeleft;
    timeleft -= 1;
    }, time * 100);
}

function teLaat(){

}

function spelersStart(){

}

window.onload = inladenSpel();

