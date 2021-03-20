<template>
  <div
    style="padding-top: 30px;">
    <GmapMap
      ref="mapRef"
      :center="center"
      :zoom="15"
      style="width: 100%; height: 800px"
      :options="{
        zoomControl: true,
        mapTypeControl: false,
        scaleControl: true,
        streetViewControl: false,
        rotateControl: true,
        fullscreenControl: false,
        disableDefaultUi: false
      }">
      <GmapInfoWindow
        :options="infoOptions"
        :position="infoWindowPos"
        :opened="infoWinOpen"
        @closeclick="infoWinOpen=false"/>
      <GmapMarker
        :key="index"
        v-for="(m, index) in getMarkers"
        :position="m.position"
        :clickable="true"
        :draggable="false"
        @click="toggleInfoWindow(m, index)"
      />
    </GmapMap>
  </div>
</template>

<script>
import { gmapApi } from 'gmap-vue';

export default {
  props: {
    holes: Array,
  },
  data() {
    return {
      center: { },
      places: [],
      currentPlace: null,
      markers: [
      ],
      infoWindowPos: null,
      infoWinOpen: false,
      currentMidx: null,
      infoOptions: {
        content: '',
        pixelOffset: {
          width: 0,
          height: -35,
        },
      },
    };
  },
  computed: {
    google: gmapApi,
    getMarkers() {
      return this.holes.map((el) => ({
        ...el,
        position: {
          lat: el.latitude,
          lng: el.longitude,
        },
      }));
    },
  },
  methods: {
    toggleInfoWindow(marker, idx) {
      this.infoWindowPos = marker.position;
      const content = `
        <div>Solicitado por: ${marker.userCreation.name || ''}</div>
        <div>Telefone: ${marker.userCreation.phone || ''}</div>
        <div>Status: ${marker.status || ''}</div>
        <div>Endereço: ${marker.endereco || ''}</div>
        `;

      this.infoOptions.content = content;
      if (this.currentMidx === idx) {
        this.infoWinOpen = !this.infoWinOpen;
      } else {
        this.infoWinOpen = true;
        this.currentMidx = idx;
      }
    },
    setPlace(place) {
      this.currentPlace = place;
    },
    addMarker() {
      if (this.currentPlace) {
        const marker = {
          lat: this.currentPlace.geometry.location.lat(),
          lng: this.currentPlace.geometry.location.lng(),
        };
        this.places.push(this.currentPlace);
        this.center = marker;
        this.currentPlace = null;
      }
    },
    geolocate() {
      navigator.geolocation.getCurrentPosition((position) => {
        this.center = {
          lat: position.coords.latitude,
          lng: position.coords.longitude,
        };
      });
    },
  },
  mounted() {
    this.geolocate();
  },
};
</script>
