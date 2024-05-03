"use strict";
const loginBtn = document.getElementById("loginBtn");
const loginUrl = "http://localhost:8080/login";
// async function fetchLogin(url: string) {
//     try{
//         const response = await fetch(url);
//
//         return await response
//     } catch(error) {
//         throw new Error(`Error fetching login page: ${(error as Error).message}` );
//     }
// }
async function fetchLoginAndRender(url) {
    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(`Error fetching login page: ${response.statusText}`);
        }
        // Check for redirect status code (302)
        if (response.redirected) {
            const newUrl = response.url; // Get the redirect URL
            const loginContainer = document.getElementById("login-container");
            loginContainer.innerHTML = "Redirecting..."; // Optional message
            // Fetch the actual login form content from the redirect URL
            const loginResponse = await fetch(newUrl);
            if (!loginResponse.ok) {
                throw new Error(`Error fetching login form: ${loginResponse.statusText}`);
            }
            const template = await loginResponse.text();
            loginContainer.innerHTML = template;
        }
        else {
            throw new Error(`Unexpected response from login endpoint: ${response.status}`);
        }
    }
    catch (error) {
        console.error("Error fetching login page:", error);
        // Handle errors with user-friendly messages
    }
}
loginBtn.addEventListener('click', async function () {
    try {
        return await fetchLoginAndRender(loginUrl);
    }
    catch (error) {
        throw new Error(`Button clicked could not fetch login page ${error.message}`);
    }
});
