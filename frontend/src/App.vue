<template>
  <div id="app">
    <div class="user-info" v-if="loggedIn">
      <span><label>Username:</label> {{username}}</span>
      <span><label>Token Type:</label> {{tokenType}}</span>
      <span><label>Expiration:</label> {{expirationTime}} ms</span>
    </div>
    <img alt="Micronaut logo" src="./assets/logo.png" style="max-width: 240px;">
    <LoginForm @login="login" v-if="!loggedIn"/>
    <ProductList v-else />

  </div>
</template>

<script>
import LoginForm from './components/LoginForm.vue'
import ProductList from "./components/ProductList";

export default {
  name: 'App',
  components: {
    ProductList,
    LoginForm
  },
  data() {
    return {
      loggedIn: false,
      username: null,
      tokenType: null,
      expirationTime: null,
    }
  },
  methods: {
    login(response) {
      if (response.access_token) {
        localStorage.setItem("access_token", response.access_token);
        localStorage.setItem("refresh_token", response.refresh_token);

        this.username = response.username;
        this.tokenType = response.token_type;
        this.expirationTime = response.expires_in;

        this.loggedIn = true;
      }
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

.user-info {
  height: 25px;
  padding-top: 6px;
  background-color: aquamarine;
  font-size: smaller;
}

.user-info span {
  margin-left: 20px;
  margin-right: 20px;
}

.user-info span label {
  font-weight: bold;
}
</style>
