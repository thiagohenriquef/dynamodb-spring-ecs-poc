<template>
  <q-layout view="hHh lpR fFf">

    <q-header style="position: sticky;" elevated class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>
          <q-avatar>
            <img src="https://cdn.quasar.dev/logo/svg/quasar-logo.svg">
          </q-avatar>
          Localização dos buracos
        </q-toolbar-title>
      </q-toolbar>

      <q-tabs align="justify"
       v-model="tab">
        <q-tab name="mapa" :label="$t('label.map')" />
        <q-tab name="buracos" :label="$tc('label.hole', 2)" />
      </q-tabs>
    </q-header>

    <!-- <q-page-container> -->
    <q-tab-panels v-model="tab" animated>
      <q-tab-panel class="q-px-none" name="mapa">
        <mapa :holes="holes"/>
      </q-tab-panel>

      <q-tab-panel name="buracos">
        <buracos @ATUALIZA_BURACOS="getAllHoles" :holes="holes"/>
      </q-tab-panel>
    </q-tab-panels>
    <!-- </q-page-container> -->
  </q-layout>
</template>

<script>
import Mapa from './Mapa';
import Buracos from './Buracos';

export default {
  data() {
    return {
      tab: 'mapa',
      holes: [],
    };
  },
  components: {
    Mapa,
    Buracos,
  },
  methods: {
    getAllHoles() {
      this.$q.loading.show();
      this.$axios.get('/hole-locations')
        .then(({ data }) => {
          this.holes = data.map((el) => ({
            ...el,
            position: {
              lat: el.latitude,
              lng: el.longitude,
            },
          }));
          this.$q.loading.hide();
        });
      // this.holes = JSON.parse('[{"id":"6b75a830-3576-443f-b56d-97656f039df2",
      // "latitude":-18.475005,"longitude":-47.2049417,"createdAt":null,
      // "userCreation":{"id":1,"name":"Thiago","cpf":null},"status":"ABERTO","h
      // oleGravity":"HIGH","position":{"lat":-18.475005,"lng":-47.2049417}}
      // ,{"id":"81daa1ad-197d-4669-9043-1d13545e6d9b","latitude":-18.4695972,"l
      // ongitude":-47.1957341,"createdAt":null,"userCreation":{"id":1,"name":
      // "Thiago","cpf":"7777777"},"status":"ABERTO","holeGravity":null,"positio
      // n":{"lat":-18.4695972,"lng":-47.1957341}}]');
    },
  },
  mounted() {
    this.getAllHoles();
  },
};
</script>
