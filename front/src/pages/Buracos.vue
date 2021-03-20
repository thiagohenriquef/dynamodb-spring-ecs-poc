<template>
  <div>
    <q-card
      v-for="(hole, index) in holes"
      :key="index"
      flat
      bordered
      class="q-ma-md my-card bg-grey-1">
      <q-card-section>
        <div class="row items-center no-wrap">
          <div class="col">
            <div class="text-h6">{{ `${$tc('label.status', 1)} - ${hole.status}` }}</div>
            <div class="text-subtitle2">{{ `${$tc('label.code', 1)} - ${hole.id}` }}</div>
          </div>

          <div class="col-auto">
            <q-btn color="grey-7" round flat icon="more_vert">
              <q-menu cover auto-close>
                <q-list>
                  <q-item clickable>
                    <q-item-section>Remover item</q-item-section>
                  </q-item>
                </q-list>
              </q-menu>
            </q-btn>
          </div>
        </div>
        <div class="row items-center no-wrap">
          <div class="col">
            <div class="text-subtitle2">{{ `${$tc('label.address', 1)} - ${hole.endereco}` }}</div>
            <div class="text-subtitle2">
              {{ `${$tc('label.hora_solicitacao', 1)} - ${formatDate(hole.createdAt)}` }}
            </div>
          </div>
        </div>
      </q-card-section>

      <q-separator />

      <q-card-actions align="right">
        <q-btn color="red" @click="iniciarNavegacao(hole)" icon="gps_fixed">Abrir no mapa</q-btn>
        <q-btn color="secondary" label="Concluir" icon="done"></q-btn>
      </q-card-actions>
    </q-card>
    <q-page-sticky position="bottom-right" :offset="[18, 18]">
      <q-btn @click="createHole" fab icon="add" color="primary" />
    </q-page-sticky>
  </div>
</template>

<script>
import moment from 'moment';

export default {
  props: {
    holes: Array,
  },
  data() {
    return {
      tab: 'mapa',
    };
  },
  methods: {
    formatDate(date) {
      return moment(date).format('DD/MM/YYYY HH:mm');
    },
    iniciarNavegacao(hole) {
      const { latitude, longitude } = hole;
      const url = `http://www.google.com/maps/place/${latitude},${longitude}`;
      window.open(url, '_blank', 'location=yes');
    },
    createHole() {
      this.$router.push({ name: 'cadastroBuraco' });
    },
  },
};
</script>
