"use strict";
document.addEventListener('DOMContentLoaded', () => {
    const profileBtn = document.getElementById('profileBtn');
    const form = document.getElementById('createUserForm');
    const createUserUrl = 'http://localhost:8080/api/users/create';
    if (form) {
        form.addEventListener('submit', async (event) => {
            event.preventDefault();
            try {
                const firstName = document.getElementById('firstNameInput');
                const lastName = document.getElementById('lastNameInput');
                const username = document.getElementById('usernameInput');
                const phone = document.getElementById('phoneNumberInput');
                const password = document.getElementById('passwordInput');
                const response = await fetchApi(createUserUrl, {
                    firstName: firstName.value,
                    lastName: lastName.value,
                    username: username.value,
                    phone: phone.value,
                    password: password.value
                });
                console.log('User created:', await response.json());
            }
            catch (error) {
                console.error('Error:', error);
            }
        });
    }
});
async function fetchApi(url, data) {
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: convertInputToJson(data)
        });
        if (!response.ok) {
            throw new Error(`Error creating user: ${response.status}, ${response.statusText}`);
        }
        return response;
    }
    catch (error) {
        throw new Error(`Error creating user: ${error.message}`);
    }
}
function convertInputToJson(data) {
    return JSON.stringify(data);
}
