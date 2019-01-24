

function compareNumbers() {
//    alert(ageInput);
    var numberInput1 = document.getElementById("number1").value;
    var numberInput2 = document.getElementById("number2").value;
    var messageBoxElement = document.getElementById("message-box");

    if (numberInput1 == "" || numberInput2 =="") {
        messageBoxElement.innerHTML = "Unesite brojeve";
    } else if (numberInput1 > numberInput2) {
        messageBoxElement.innerHTML = "Broj A je veći od B";
    } else if (numberInput1 < numberInput2) {
        messageBoxElement.innerHTML = "Broj B je veći od A";
    } else if (numberInput1 = numberInput2) {
        messageBoxElement.innerHTML = "Brojevi su jednaki";
   }

}


