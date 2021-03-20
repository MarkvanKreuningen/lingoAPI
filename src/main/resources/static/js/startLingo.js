function start2SpelerGame(){
    var naamSpeler1 = document.getElementById("naamSpeler1").value;
    var naamSpeler2 = document.getElementById("naamSpeler2").value;
    var tijdPerBeurt = document.getElementById("tijdPerBeurt").value;
    sessionStorage.setItem("naamSpeler1",naamSpeler1);
    sessionStorage.setItem("naamSpeler2",naamSpeler2);
    sessionStorage.setItem("tijdPerBeurt",tijdPerBeurt);
    window.location = "game2players"

}