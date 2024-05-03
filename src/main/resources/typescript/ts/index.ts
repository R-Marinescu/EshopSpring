document.addEventListener('DOMContentLoaded', () => {
    const profileBtn = document.getElementById('profileBtn') as HTMLButtonElement;
    const form = document.getElementById('createUserForm') as HTMLFormElement;
    const createUserUrl = 'http://localhost:8080/api/users/create';

    if (form) {
        form.addEventListener('submit', async (event) => {
            event.preventDefault();

            try {
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

