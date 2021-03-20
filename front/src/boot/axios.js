import Vue from 'vue';
import axios from 'axios';

Vue.prototype.$axios = axios.create({
  baseURL: 'http://localhost:8081/api/',
  timeout: 10000,
});
