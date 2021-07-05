<template>
<div class="photo">
    <h1 class="title">Detailansicht</h1>
    <p>{{foto.dateiname}}</p>
    <p>{{foto.zeitstempel}}</p>

    <img :src="url" />
    
  </div>
</template>

<script lang="ts">
import Vue, { defineComponent, PropType } from 'vue'
import { useFotoStore } from "@/services/FotoStore";
import { Foto } from '@/services/Foto';


export default defineComponent({
    props : {
        fotoid :{type : String, required: true }
    },

    setup(props){
        console.log("fotoid", props.fotoid)
        const {fotostate , updateFotos, deleteFotos} = useFotoStore()
        return {
            foto : fotostate.fotos.find(e => e.id == parseInt(props.fotoid)), 
            url: '/foto/'+parseInt(props.fotoid)   
        }
    }
})
</script>