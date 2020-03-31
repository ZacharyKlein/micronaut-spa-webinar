<template>
    <form @submit="login">
        <h1>Login</h1>

        <div v-if="message" class="alert">{{message}}</div>

        <label for="username">Username:</label>
        <input type="text" id="username" v-model="username" />
        <br/>
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" />

        <br />

        <input type="submit" value="Login" />
    </form>
</template>
<script>
    export default {
        name: 'LoginForm',
        data() {
            return {
                username: '',
                password: '',
                message: null
            }
        },
        methods: {
            login(e) {
                e.preventDefault();
                this.message = null;

                fetch('http://localhost:8080/login', {
                    method: "POST",
                    headers: new Headers({
                        Accept: "application/json",
                        "Content-Type": "application/json"
                    }),
                    body: JSON.stringify({username: this.username, password: this.password})
                })
                    .then(response => {
                        if (response.ok) {
                            return response.json();
                        } else {
                            this.message = "Invalid credentials"
                        }
                    })
                    .then(json => this.$emit('login', json))
                    .catch(error => this.message = error.message);
            }
        }
    }
</script>
<style scoped>
    div.alert {
        border: 1px solid red;
        color: red;
    }
</style>