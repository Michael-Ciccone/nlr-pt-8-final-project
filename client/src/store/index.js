import { createStore as _createStore } from 'vuex';
import axios from 'axios';
import ResourceService from '../services/ResourceService';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      devices: [],
      departments: [],
      models: [],
      token: currentToken || '',
      user: currentUser || {},
    },
    mutations: {
      SET_DEVICES(state, devices) {
        state.devices = devices;
      },
      SET_DEPARTMENTS(state, departments) {
        state.departments = departments;
      },
      SET_MODELS(state, models) {
        state.models = models;
      },
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      }
    }
  })
  return store;
}