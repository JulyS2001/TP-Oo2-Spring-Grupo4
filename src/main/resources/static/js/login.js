document.addEventListener("DOMContentLoaded", () => {
    const passwordInput = document.getElementById("password");
    const toggleButton = document.querySelector(".toggle-password"); // usamos el botón
    const toggleIcon = document.getElementById("eye-icon");

    let visible = false;

    toggleButton.addEventListener("click", () => {
        visible = !visible;
        passwordInput.type = visible ? "text" : "password";
        toggleIcon.src = visible ? "css/ojo-abierto.svg" : "css/ojo-cerrado.svg";
        toggleIcon.alt = visible ? "Ocultar contraseña" : "Mostrar contraseña";
    });
});