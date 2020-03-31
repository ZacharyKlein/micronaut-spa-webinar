<template>
  <div id="app">
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
      loggedIn: false
    }
  },
  methods: {
    login(response) {
      if (response.access_token) {
        localStorage.setItem("access_token", response.access_token);
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
  margin-top: 60px;
}
</style>
