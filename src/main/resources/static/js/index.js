"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
// document.addEventListener('DOMContentLoaded', () => {
//
// const firstNameInput: HTMLInputElement | null = document.getElementById('firstNameInput') as HTMLInputElement | null;
// const lastNameInput: HTMLInputElement | null = document.getElementById('lastNameInput') as HTMLInputElement | null;
// const usernameInput: HTMLInputElement | null = document.getElementById('usernameInput') as HTMLInputElement | null;
// const phoneNumberInput: HTMLInputElement | null = document.getElementById('phoneNumberInput') as HTMLInputElement | null;
// const passwordInput: HTMLInputElement | null = document.getElementById('passwordInput') as HTMLInputElement | null;
// const form: HTMLInputElement | null = document.getElementById('createUserForm') as HTMLInputElement | null;
//
// const firstName: string = firstNameInput?.value ?? '';
// const lastName: string = lastNameInput?.value ?? '';
// const username: string = usernameInput?.value ?? '';
// const phone: string = phoneNumberInput?.value ?? '';
// const pass: string = passwordInput?.value ?? '';
//
// const createUserUrl = 'http://localhost:8080/api/users/create';
//
// function convertInputToJson(firstName: string, lastName: string, username: string, password: string, phoneNumber: string) {
//     const jsonObject = {
//         "firstName": firstName,
//         "lastName": lastName,
//         "username": username,
//         "password": password,
//         "phoneNumber": phoneNumber
//     };
//
//     return JSON.stringify(jsonObject);
// }
// console.log('First Name Input:', firstNameInput?.value);
// console.log('Last Name Input:', lastNameInput?.value);
// console.log('Username Input:', usernameInput?.value);
// console.log('Phone Number Input:', phoneNumberInput?.value);
// console.log('Password Input:', passwordInput?.value);
//
//
// async function fetchApi(url: string) {
//     try {
//         const response = await fetch(url, {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: convertInputToJson(firstName, lastName, username, phone, pass)
//         });
//         if (response.ok) {
//             const createdUser = await response.json();
//             console.log('User created:', createdUser);
//         } else {
//             console.error('Error creating user:', response.status, response.statusText);
//         }
//
//     } catch(error) {
//         console.error('Error creating user:', (error as Error).message);
//         throw error;
//     }
// }
//
// if (form) {
//     form.addEventListener('submit', async (event) => {
//         event.preventDefault(); // Prevent the default form submission behavior
//
//         try {
//             await fetchApi(createUserUrl);
//         } catch (error) {
//             console.error('Error:', error);
//         }
//     });
// }
// });
document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('createUserForm');
    const createUserUrl = 'http://localhost:8080/api/users/create';
    if (form) {
        form.addEventListener('submit', (event) => __awaiter(void 0, void 0, void 0, function* () {
            event.preventDefault();
            try {
                // Get the input values *after* the user has submitted the form
                const firstName = document.getElementById('firstNameInput');
                const lastName = document.getElementById('lastNameInput');
                const username = document.getElementById('usernameInput');
                const phone = document.getElementById('phoneNumberInput');
                const password = document.getElementById('passwordInput');
                const response = yield fetchApi(createUserUrl, {
                    firstName: firstName.value,
                    lastName: lastName.value,
                    username: username.value,
                    phone: phone.value,
                    password: password.value
                });
                console.log('User created:', yield response.json());
            }
            catch (error) {
                console.error('Error:', error);
            }
        }));
    }
});
function fetchApi(url, data) {
    return __awaiter(this, void 0, void 0, function* () {
        try {
            const response = yield fetch(url, {
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
    });
}
function convertInputToJson(data) {
    return JSON.stringify(data);
}
