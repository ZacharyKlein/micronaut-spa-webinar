<template>
  <div id="app">
    <div :class="userInfoClass" v-if="loggedIn">
      <span><label>Username:</label> {{ username }}</span>
      <span><label>Token Type:</label> {{ tokenType }}</span>
      <span><label>Expiration:</label> {{ expirationTime }} ms</span>
      <span><a href="#" @click="loggedIn = false">[Logout]</a></span>
    </div>
    <img alt="Micronaut logo" src="./assets/logo.png" style="max-width: 480px;">
    <LoginForm @login="login" v-if="!loggedIn"/>
    <ProductList v-else :username="username"/>

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
      console.log({response});

      if (response.access_token) {
        localStorage.setItem("access_token", response.access_token);
        localStorage.setItem("refresh_token", response.refresh_token);

        const parsedJwt = this.parseJwt(response.access_token);

        console.log({parsedJwt});

        this.username = parsedJwt.sub;
        this.tokenType = response.iss;
        this.expirationTime = response.exp;

        this.loggedIn = true;
      }
    },
    parseJwt(token) {
      const base64Url = token.split('.')[1];
      const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
      const jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
      }).join(''));

      return JSON.parse(jsonPayload);

    }
  },
  computed: {
    userInfoClass() {
      return {
        'user-info': true,
        'admin-info': this.username === 'Admin'
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

.admin-info {
  background-color: lightcoral !important;
}

.user-info span {
  margin-left: 20px;
  margin-right: 20px;
}

.user-info span label {
  font-weight: bold;
}
</style>
