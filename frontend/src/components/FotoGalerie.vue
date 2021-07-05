<template>
  <div class="container">
    
    <!-- Ist irgendwie Komsisch-->
    <div v-if="fotostate.errormessage!=''" class="notification is-danger">{{fotostate.errormessage}}</div>
    
    <h1>{{ msg }}</h1>
    <!-- Button zum Hinzufügen des nächsten Bildes -->
    <button class="button" v-on:click="geklickt()">
      <i class="fas fa-camera" />
    </button>
    <!-- Eingabefeld für inkrementelle Suche -->
    <section class="section">
      <input type="text" class="input" v-model="suchwort" placeholder="Suche" />
    </section>
    <section class="section">
      <div class="columns is-multiline">
        <!-- Hier alle Bilder mit Hilfe der FotoGalerieBild-Komponente anzeigen -->
          <FotoGalerieBild :foto="i" v-for="i in listitems" v-bind:key="i.id"
          @entferne-foto="delFoto($event)" />
        <!-- flexibel natürlich - nicht die fünf Beispielbilder hardcoden! -->
      </div>
    </section>
  <p> Es sind Fotos {{fotostate.fotos}} da</p>
  <p> Es sind Fotos {{fotostate.fotos.length}} da</p>
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, onMounted, ref } from "vue";
import FotoGalerieBild from "./FotoGalerieBild.vue";
//import { Foto } from "@/services/Foto";
//import { fotoliste } from "@/services/FotoListe";
import { useFotoStore } from "@/services/FotoStore";
//import { updateFotos }  from "@/services/FotoStore";
export default defineComponent({
  name: "FotoGalerie",
  components:{FotoGalerieBild},

  props:{},
  setup(props){
    
    const suchwort = ref("");
    
    const {fotostate , updateFotos, deleteFotos} = useFotoStore()
    
    const listitems = computed(() => {
      const n: number = suchwort.value.length; 
      if (suchwort.value.length < 3) {
        return fotostate.fotos;
      } else {
        return fotostate.fotos.filter(e => e.ort.toLowerCase().includes(suchwort.value.toLowerCase()));
      }
    
    });
    onMounted(async() => {
      await updateFotos() 
    });

  
   function delFoto(id: number): void{
      //fotos.a = fotos.a.filter(e => e.id !== id)
      deleteFotos(id);
    }
  
    /*
    function geklickt(): void{
        let lenght = fotostate.fotos.length
        if(lenght == fotoliste.length)
          alert('Keine Fotos mehr')
        else{
          
          fotostate.fotos.push(fotoliste[lenght])

        }    
        }
   */ 
    return {suchwort, fotostate ,fotos: fotostate.fotos, listitems, delFoto};
  }
  
});
</script>


<style scoped>

</style>

function fotostate(): { fotoss: any; errormessage: any; } {
  throw new Error("Function not implemented.");
}

function useFotoStore(): { fotos: any; errormessage: any; } {
  throw new Error("Function not implemented.");
}

function updateFotos() {
  throw new Error("Function not implemented.");
}
