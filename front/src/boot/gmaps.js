import Vue from 'vue';
import * as GmapVue from 'gmap-vue';

Vue.use(GmapVue, {
  load: {
    key: 'AIzaSyBsaEZyU-ojs7QS5w7mkrCOcyhVCSFVZs0',
    libraries: 'places,visualization,drawing',
    region: 'BR',
    language: 'pt-BR',
    autobindAllEvents: false,
  },
  installComponents: true,
});
