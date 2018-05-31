/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function applyMode() {
    if (sessionStorage.getItem("darkMode")) {
        document.getElementById('h1Mode').style = "color: white";
        document.getElementById("bodyMode").style = "background-color: #2E3338";
    }
}

function toggleDarkMode() {
    var darkMode = sessionStorage.getItem("darkMode");
    if (mode === null) {
        sessionStorage.setItem("darkMode", false);
    } else {
        sessionStorage.setItem("darkMode", !darkMode);
    }
}