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
    const form = document.getElementById('createUserForm') as HTMLFormElement;
    const createUserUrl = 'http://localhost:8080/api/users/create';

    if (form) {
        form.addEventListener('submit', async (event) => {
            event.preventDefault();

            try {
                // Get the input values *after* the user has submitted the form
                const firstName = document.getElementById('firstNameInput') as HTMLInputElement;
                const lastName = document.getElementById('lastNameInput') as HTMLInputElement;
                const username = document.getElementById('usernameInput') as HTMLInputElement;
                const phone = document.getElementById('phoneNumberInput') as HTMLInputElement;
                const password = document.getElementById('passwordInput') as HTMLInputElement;

                const response = await fetchApi(createUserUrl, {
                    firstName: firstName!.value,
                    lastName: lastName!.value,
                    username: username!.value,
                    phone: phone!.value,
                    password: password!.value
                });

                console.log('User created:', await response.json());
            } catch (error) {
                console.error('Error:', error);
            }
        });
    }
});

async function fetchApi(url: string, data: { firstName: string, lastName: string, username: string, phone: string, password: string }) {
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
    } catch (error) {
        throw new Error(`Error creating user: ${(error as Error).message}`);
    }
}

function convertInputToJson(data: { firstName: string; lastName: string; username: string; phone: string; password: string }) {
    return JSON.stringify(data);
}

