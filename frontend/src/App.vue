<template>
  <section id="app">
    <div class="hero is-primary">
      <div class="hero-body">
        <h1 class="title">MI Foto Community</h1>
        <p class="">Eingeloggt als {{loginstate.username}}</p>
      </div>
    </div>

    <div class="block">
      <nav class="navbar">
        <div class="navbar-menu is-active">
          <router-link to="/" class="navbar-item">Übersicht</router-link>
          <router-link to="/about" class="navbar-item">About</router-link>
        </div>
        <div class="navbar-end">
          <div class="navbar-item">
            <router-link to="/Login/Logout" class="navbar-item">Logout/Login</router-link>
          </div>
        </div>
      </nav>
    </div>

    <div class="block">
      <div class="container">
          <router-view />
      </div>
    </div>
  </section>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import { useLoginStore } from "./services/LoginStore";

export default defineComponent({
  name: "App",
  components: {

  },
  setup(){
    const {loginstate, doLogout, doLogin} = useLoginStore();
    console.log("loginstate, Username", loginstate.username," isLoggedin " ,loginstate.isLoggedIn)
    return{loginstate}
  }

});
</script>
