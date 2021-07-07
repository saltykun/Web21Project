<template>
    
   <div v-if="loginstate.errormessage!='' " class="notification is-danger">{{loginstate.errormessage}}</div>

   <h1>Login zur MI Foto - Community</h1>
   <div >
        <input class="input" type="text" v-model="username"><br>
        <input class="input" type="password" v-model="password"><br><br>
        <button v-on:click="kannEinLoggen()" class="button is-primary">Login</button>

   </div>
  
</template>

<script lang="ts">
import {useLoginStore} from "@/services/LoginStore";
import { computed, defineComponent, ref } from "vue";
import {useRouter} from "vue-router"
export default defineComponent({
    name: "LoginView",
    setup() {
        const username = ref("");
        const password = ref(""); 
        const {loginstate, doLogout, doLogin} = useLoginStore();
        doLogout()
        const router = useRouter();
        //console.log("kann man sich einloggen?", doLogin(username.value, password.value))  
        async function kannEinLoggen(){
            if(!loginstate.isLoggedIn){
                if(await doLogin(username.value, password.value)){
                    router.replace("/")
                }
            }else{
                doLogout()
            }
            
        }

        return{loginstate, kannEinLoggen, username, password}
    },
});
</script>