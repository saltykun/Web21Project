import { reactive, readonly } from "vue";

const loginstate = reactive({
    username : String(""),
    jwttocken :String(""),
    errormessage : String(""),
    isLoggedIn : Boolean(false) 
})

export function useLoginStore(){

    function doLogout(){
        loginstate.jwttocken = "";
        loginstate.username = "";
        loginstate.errormessage = "";
        loginstate.isLoggedIn = false;
    }
    
    async function doLogin(username: string, password: string): Promise<boolean>{
        const userobj = { username: username, password: password};
        fetch('/api/login',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Basic '
            },
            body: JSON.stringify(userobj)
        }).then( (response) => { if (!response.ok) {
            loginstate.errormessage = "error";
            throw new Error('schade'); 
        }
            return response.text(); 
        }).then(jsondata => {
            loginstate.username = username
            loginstate.jwttocken = jsondata
            loginstate.isLoggedIn = true
            loginstate.errormessage = ""
        }).catch(fehler => {
            doLogout()
            loginstate.errormessage = 'Fehler: '+ fehler
            console.log("DAS EINLOGGEN HAT NICHT GEKLAPPT")
            return false
        })
        console.log("DAS EINLOGGEN HAT GEKLAPPT")

        return true;    
    }
    return {
        loginstate: readonly(loginstate), doLogin, doLogout
    }
}
