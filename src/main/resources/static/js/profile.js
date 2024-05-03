"use strict";
const divUserData = document.getElementById('userData');
const getUserBySessionApi = 'http://localhost:8080/api/users/session';
async function fetchProfileApi(url) {
    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(`Error fetching user session: ${response.status}, ${response.statusText}`);
        }
        // Parse JSON response
        return await response.json();
    }
    catch (error) {
        throw new Error(`Error fetching session: ${error.message}`);
    }
}
// Wrap the asynchronous operation in an async function
(async () => {
    try {
        const userData = await fetchProfileApi(getUserBySessionApi);
        console.log(userData); // Type-safe access to userData properties
        // Update DOM with template literals and type-safe interpolation
        divUserData.innerHTML = `
        <p>Username: ${userData.username}</p>
        <p>First name: ${userData.firstName}</p>
    `;
    }
    catch (error) {
        console.error('Error:', error);
    }
})();
