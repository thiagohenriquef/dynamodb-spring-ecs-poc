import Vue from 'vue';
import axios from 'axios';

Vue.prototype.$axios = axios.create({
  baseURL: 'http://18.231.145.36:8081/api/',
  timeout: 10000,
});
