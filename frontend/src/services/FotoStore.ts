import { reactive, readonly } from "vue";
import { Foto } from "./Foto";
import { Client, Message } from '@stomp/stompjs';
import { FotoMessage } from "./FotoMessage";

const fotostate = reactive({
    fotos: Array<Foto>(),
    errormessage: String("")
 })



export function useFotoStore(){
    const wsurl = "ws://localhost:9090/messagebroker"; 
    const DEST = "/topic/foto";
    const stompclient = new Client({ brokerURL: wsurl })
    stompclient.onConnect = (frame) => {
        console.log("onConnect wurde erreicht.")
        stompclient.subscribe(DEST, (message) => {
            console.log("message", message.body)
            const messageParsed = JSON.parse(message.body) as FotoMessage
            
        
            if (messageParsed.operation == "fotoGespeichert" || messageParsed.operation == "fotoGeloescht"){
                console.log("hallo ich bin hier")
                updateFotos()
            }
        });     
    };
    stompclient.onDisconnect = () => { /* Verbindung abgebaut*/ } // Verbindung zum Broker aufbauen
    stompclient.activate();
    
    
    async function updateFotos(){
        fetch('/api/foto', {
            method: 'GET',
            headers: {

            }, 
        }).then( (response) => { if (!response.ok) {
            fotostate.errormessage = "error";
            throw new Error('schade'); 
            }
            // empfangene Payload -> JSON
            return response.json(); 
        }).then(jsondata => {
            console.log("asdf")
            fotostate.fotos = jsondata
            console.log("hallo",fotostate.fotos)
        }).catch(fehler =>{
            fotostate.errormessage = 'Fehler: '+ fehler;
        })
        
    }
    async function deleteFotos(id :number){
        fetch('api/foto/'+ id, { 
        method: 'DELETE'}
        ).then( (response) => { if (!response.ok) {
            fotostate.errormessage = "error";
            throw new Error('schade'); 
            }
            // empfangene Payload -> JSON
            return response.json(); 
        }
        ).then(jsondata =>{
            fotostate.fotos = jsondata
            fotostate.errormessage = ""

        }).catch(fehler => {
            fotostate.errormessage = 'Fehler: '+ fehler})
    }
    
    return {
        fotostate: readonly(fotostate), updateFotos, deleteFotos
    }
}

