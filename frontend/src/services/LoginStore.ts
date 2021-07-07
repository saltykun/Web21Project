import { reactive, readonly } from "vue";

const loginstate = reactive({
    username : String(""),
    jwttoken :String(""),
    errormessage : String(""),
    isLoggedIn : Boolean(false) 
})

export function useLoginStore(){

    function doLogout(){
        loginstate.jwttoken = "";
        loginstate.username = "";
        loginstate.errormessage = "";
        loginstate.isLoggedIn = false;
    }
    
    async function doLogin(username: string, password: string): Promise<boolean>{
        const userobj = { username: username, password: password};
        const credentials = btoa('${username, passwort}')
        
        const response = await fetch('/api/login',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userobj),
        });
        if(!response.ok){
            console.log("Da lief was schief")
            
            doLogout()
            loginstate.isLoggedIn = false
            loginstate.errormessage = ("Das Einloggen hat leider nicht Geklappt, du Looser !!!!") 
        }else{
            console.log("es hat geklappt")
            const jsondata = await response.text();
            loginstate.username = username
            loginstate.jwttoken = jsondata
            loginstate.isLoggedIn = true
            loginstate.errormessage = ""
        }
        return loginstate.isLoggedIn;
    }
    
    return {
        loginstate: readonly(loginstate), doLogin, doLogout
    }
}
