<template>
  <div class="card column is-3 has-background-grey-lighter m-3">
    <div class="card-header">
      <p class="card-header-title is-centered">
        <!-- Dateinamen ausgeben -->
        {{foto.dateiname}}
      </p>
      <!-- Lösch-Button -->
      <button class="button card-header-icon has-background-grey-light" @click="delclicked()">
        <i class="fa fa-times" />
      </button>
    </div>
    
    <div class="card-content has-text-centered">
        <div class="navbar-menu is-active">
          <!-- Bild anzeigen -->
          <router-link :to="{name:'FotoDetailView', params: {fotoid: foto.id} }">
            <figure class="image is-inline-block">
              <img :src="url" />
            </figure>
          </router-link>
        </div>
      
      <div class="content">
        <foto-star-rating :maxsterne="5" />

      </div>
      <!-- Ort -->
      <div class="content" >{{foto.ort}}</div>
      <!-- Zeitstempel -->
      <div class="has-text-grey">{{foto.zeitstempel}}</div>
    </div>
  </div>
</template>


<script lang="ts">
import { defineComponent, PropType, ref } from "vue";
import FotoStarRating from "./FotoStarRating.vue";
import { Foto } from "@/services/Foto";

export default defineComponent({
  components: { FotoStarRating },
  name: "FotoGalerieBild",
  props : {
    foto : {type: Object as PropType<Foto>, required: true }
  },

  setup(props, context) {
    
    function delclicked(): void{
      context.emit("entferne-foto", props.foto.id)
    }
    
    return {
      
      url: 'foto/'+ props.foto.id,
      delclicked
      //url: require('@/assets/thumbnails/DerTupel.png')
    };
  }
});
</script>
