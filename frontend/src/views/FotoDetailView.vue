<template>
<div class="photo">
    <h1 class="title">Detailansicht</h1>
    <p>{{foto.dateiname}}</p>
    <p>{{foto.zeitstempel}}</p>

    <img :src="url" />
    
    <p>{{foto.ort}}</p>

    <router-link to="/">
    <button class="button - is-primary">Zurück</button>
    </router-link>

  </div>
</template>

<script lang="ts">
import Vue, { defineComponent, PropType } from 'vue'
import { useFotoStore } from "@/services/FotoStore";
//import { Foto } from '@/services/Foto';


export default defineComponent({
    name: "FotoDeteilView",
    props : {
        fotoid :{type : String, required: true }
    },

    setup(props){
        console.log("fotoid", props.fotoid)
        const {fotostate} = useFotoStore()
        return {
            foto : fotostate.fotos.find(e => e.id == parseInt(props.fotoid)), 
            url: '/foto/' + parseInt(props.fotoid)   
        }
    }
})
</script>