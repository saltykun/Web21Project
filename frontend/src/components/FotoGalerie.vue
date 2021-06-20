<template>
  <div class="container">
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
  </div>
</template>

<script lang="ts">
import { computed, defineComponent, reactive, ref, Ref } from "vue";
import FotoGalerieBild from "./FotoGalerieBild.vue";
import { Foto } from "@/services/Foto";
import { fotoliste } from "@/services/FotoListe";

export default defineComponent({
  name: "FotoGalerie",
  components:{FotoGalerieBild},

  props:{},
  setup(props){
    const fotos = reactive({a: [] as Array<Foto>})
    const suchwort = ref("");

    const listitems = computed(() => {
      const n: number = suchwort.value.length; 
      if (suchwort.value.length < 3) {
        return fotos.a;
      } else {
        return fotos.a.filter(e => e.ort.toLowerCase().includes(suchwort.value.toLowerCase()));
      }
    
    });

    function delFoto(id: number): void{
      fotos.a = fotos.a.filter(e => e.id !== id)
    }

    function geklickt(): void{
        let lenght = fotos.a.length
        if(lenght == fotoliste.length)
          alert('Keine Fotos mehr')
        else{
          fotos.a.push(fotoliste[lenght])

        }    
        }
    
    return {suchwort, fotos, geklickt, listitems, delFoto};
  }
  
});
</script>


<style scoped>

</style>
