function validateChain() {
    for (var i = 0; i < arguments.length; i++)
        if (!arguments[i]) {
            document.querySelector("#button1").disabled = true;
            break;
        }
    if (document.querySelectorAll("i:not(:empty)").length === 0)
        document.querySelector("#button1").disabled = false;
}

function validateString(name, val) {
    var error = "Error: you wrote wrong value, must be string";
    if (!isNaN(parseFloat(val)) && isFinite(val))
        //document.getElementById(name).innerHTML = error;
        document.querySelector(`#${name}`).innerHTML = error;
    else {
        document.querySelector(`#${name}`).innerHTML = document.querySelector(`#${name}`).innerHTML.replace(error, "");
        return true;
    }
    return false;
}

function validateNumber(name, val) {
    var error = "Error: you wrote wrong value, must be number";
    if (/[^[0-9]/.test(val))
        document.querySelector(`#${name}`).innerHTML = error;
    else {
        document.querySelector(`#${name}`).innerHTML = document.querySelector(`#${name}`).innerHTML.replace(error, "");
        return true;
    }
    return false;
}

function validateDate(name, val) {
    var error = "Error: you wrote number out of data range";
    if (val > new Date().getFullYear())
        document.querySelector(`#${name}`).innerHTML = error;
    else {
        document.querySelector(`#${name}`).innerHTML = document.querySelector(`#${name}`).innerHTML.replace(error, "");
        return true;
    }
    return false;
}