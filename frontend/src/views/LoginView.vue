<template>
   <h1>Login zur MI Foto - Community</h1>
   <form @submit.prevent="doLogin(username, passwort)">
       
        <input type="text" v-model="username"><br>
        <input type="text" v-model="passwort"><br>
        <router-link v-if="doLogin(username, passwort)!= false" to="/">
            <input type="submit"/>
        </router-link>      

   </form>
  
</template>

<script lang="ts">
import {useLoginStore} from "@/services/LoginStore";
import { defineComponent, ref } from "vue";
export default defineComponent({
    name: "LoginView",
    setup() {
        const username = ref("");
        const passwort = ref(""); 
        const {loginstate, doLogout, doLogin} = useLoginStore()
        doLogout();
        
        return{doLogin, username, passwort
        }
    },
});
</script>