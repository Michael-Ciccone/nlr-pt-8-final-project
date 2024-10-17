import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      devices: [],
      device: null,
      departments: [],
      department: null,
      models: [],
      model: null,
      token: currentToken || '',
      user: currentUser || {},
    },
    mutations: {
      SET_DEVICES(state, devices) {
        state.devices = devices;
      },
      SET_DEVICE(state, device) {
        state.device = device;
      },
      ADD_DEVICE(state, device) {
        state.departments.push(device);
      },
      UPDATE_DEVICE(state, updatedDevice) {
        const index = state.devices.findIndex(device => device.id === updatedDevice.id);
        if (index !== -1) {
          state.devices.splice(index, 1, updatedDevice);
        }
      },
      DELETE_DEVICE(state, deviceId) {
        state.devices = state.devices.filter(device => device.id !== deviceId);
      },
      SET_DEPARTMENTS(state, departments) {
        state.departments = departments;
      },
      SET_DEPARTMENT(state, department) {
        state.department = department;
      },
      ADD_DEPARTMENT(state, department) {
        state.departments.push(department);
      },
      UPDATE_DEPARTMENT(state, updatedDepartment) {
        const index = state.departments.findIndex(department => department.id === updatedDepartment.id);
        if (index !== -1) {
          state.departments.splice(index, 1, updatedDepartment);
        }
      },
      DELETE_DEPARTMENT(state, departmentId) {
        state.departments = state.departments.filter(department => department.id !== departmentId);
      },
      SET_MODELS(state, models) {
        state.models = models;
      },
      SET_MODEL(state, model) {
        state.model = model;
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