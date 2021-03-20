<template>
  <q-layout view="hHh lpR fFf">
    <q-header style="position: sticky;" class="bg-primary text-white" height-hint="98">
      <q-toolbar>
        <q-toolbar-title>
          <q-btn
            flat
            round
            color="white"
            icon="arrow_back"
            @click="$router.push({ name: 'index' })"/>
          Cadastrar Buraco
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <div class="q-pa-md">
      <q-form
        ref="form"
        @submit="onSubmit"
        @reset="onReset"
        class="q-gutter-md">
        <q-input
          filled
          v-model="buraco.userCreation.name"
          label="Usuário de solicitação"/>

        <q-input
          filled
          v-model="buraco.userCreation.phone"
          type="tel"
          label="Telefone de contato"/>

        <q-select
          v-model="buraco.holeGravity"
          option-label="label"
          option-value="value"
          filled
          stack-label
          map-options
          emit-value
          use-input
          :options="gravidadeOptions"
          label="Gravidade"
        />

        <q-select
          v-model="buraco.status"
          option-label="label"
          option-value="value"
          filled
          disabled
          disable
          stack-label
          :options="statusOptions"
          use-input
          label="Status"
        />

        <q-select
          v-model="selectedAddressess"
          @filter="fetchSources"
          option-label="endereco"
          filled
          map-options
          emit-value
          use-chips
          stack-label
          :options="options"
          multiple
          use-input
          placeholder="Digite para pesquisar"
          label="Endereço"
        />
        <Gmap-Map
          style="height: 500px;"
          :zoom="15"
          :center="center"
          :options="{
            zoomControl: true,
            mapTypeControl: false,
            scaleControl: true,
            streetViewControl: false,
            rotateControl: true,
            fullscreenControl: false,
            disableDefaultUi: false
          }">
          <Gmap-Marker
            v-for="(marker, index) in selectedAddressess"
            :key="index"
            :position="marker.position"></Gmap-Marker>
          <Gmap-Marker v-if="this.place" label="&#x2605;" :position="{
              lat: this.place.geometry.location.lat(),
              lng: this.place.geometry.location.lng(),
            }"></Gmap-Marker>
        </Gmap-Map>

        <div align="right">
          <q-btn label="Salvar" type="submit" color="primary"/>
        </div>
      </q-form>
    </div>
  </q-layout>
</template>

<script>
export default {
  data() {
    return {
      geocoder: new google.maps.Geocoder(), //eslint-disable-line
      name: null,
      age: null,
      accept: false,
      markers: [],
      place: null,
      selectedAddressess: [],
      options: [],
      center: {},
      buraco: {
        status: 'ABERTO',
        userCreation: {},
      },
      gravidadeOptions: [
        {
          label: 'Alta',
          value: 'HIGH',
        },
        {
          label: 'Média',
          value: 'MEDIUM',
        },
        {
          label: 'Baixa',
          value: 'LOW',
        },
      ],
      statusOptions: [
        {
          label: 'Aberto',
          value: 'ABERTO',
        },
        {
          label: 'Fechado',
          value: 'FECHADO',
        },
        {
          label: 'Cancelado',
          value: 'CANCELADO',
        },
      ],
    };
  },
  computed: {
  },
  methods: {
    setDescription(description) {
      this.description = description;
    },
    setPlace(place) {
      this.place = place;
    },
    usePlace() {
      if (this.place) {
        this.markers.push({
          position: {
            lat: this.place.geometry.location.lat(),
            lng: this.place.geometry.location.lng(),
          },
        });
        this.place = null;
      }
    },
    onSubmit() {
      this.$refs.form.validate().then((success) => {
        if (success) {
          this.salvarBuraco();
        } else {
          this.$q.notify({
            color: 'red-5',
            textColor: 'white',
            icon: 'warning',
            message: 'You need to accept the license and terms first',
          });
        }
      });
    },
    fetchSources(val, update) {
      if (val === '') {
        update(() => {
          this.options = [];
        });
        return;
      }

      if (val.length < 5) {
        return;
      }

      update(() => {
        this.geocodeAddress(val.toLowerCase());
      });
    },
    onReset() {
      this.name = null;
      this.age = null;
      this.accept = false;
    },
    geocodeAddress(address) {
      const geocoder = new google.maps.Geocoder(); //eslint-disable-line
      return geocoder.geocode({ address }, (results, status) => {
        if (status === 'OK') {
          this.options = results.map((el) => ({
            endereco: el.formatted_address,
            position: {
              lat: el.geometry.location.lat(),
              lng: el.geometry.location.lng(),
            },
          }));
        }
        // alert(`Geocode was not successful for the following reason: ${status}`);
      });
    },
    geolocate() {
      navigator.geolocation.getCurrentPosition((position) => {
        const { latitude, longitude } = position.coords;
        this.center = {
          lat: latitude,
          lng: longitude,
        };
        this.reverseGeocoding();
      });
    },
    reverseGeocoding() {
      this.geocoder.geocode({ location: this.center }, (results, status) => {
        if (status === 'OK') {
          if (results[0]) {
            this.selectedAddressess.push({
              endereco: results[0].formatted_address,
              position: this.center,
            });
          }
        }
      });
    },
    salvarBuraco() {
      this.$q.loading.show();
      const listPayloads = this.selectedAddressess.map((el) => ({
        ...el,
        ...this.buraco,
        latitude: el.position.lat,
        longitude: el.position.lng,
      }));

      Promise.all(listPayloads.map((el) => this.$axios.post('/hole-locations', el)))
        .then(() => {
          this.$q.notify({
            color: 'green-4',
            textColor: 'white',
            icon: 'cloud_done',
            message: 'Buracos cadastrados',
          });
        })
        .finally(() => {
          this.$q.loading.hide();
          this.$router.push({ name: 'index' });
        });
    },
  },
  mounted() {
    this.geolocate();
  },
};
</script>
