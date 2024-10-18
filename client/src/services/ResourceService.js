import axios from "axios";


const ResourceService = {
  getDepartments() {
    return axios.get('/departments');
  },
  getDevices() {
    return axios.get('/devices');
  },
  getModels() {
    return axios.get('models');
  },
  getDeviceById(deviceId) {
    return axios.get(`/devices/${deviceId}`);
  },
  deleteDeviceById(deviceId) {
    return axios.delete(`/devices/${deviceId}`)
  },
  updateDeviceById(deviceId, device) {
    return axios.put(`/devices/${deviceId}`, device)
  },
  getDepartmentById(departmentId) {
    return axios.get(`/departments/${departmentId}`);
  },
  deleteDepartmentById(departmentId) {
    return axios.delete(`/departments/${departmentId}`)
  },
  updateDepartmentById(departmentId, department) {
    return axios.put(`/departments/${departmentId}`, department)
  },
  getModelById(modelId) {
    return axios.get(`/models/${modelId}`);
  },
  updateModelById(modelId, model) {
    return axios.put(`/models/${modelId}`, model)
  },
  getDevicesByDepartment(departmentId) {
    return axios.get(`/departments/${departmentId}/devices`);
  },
  createDepartment(department) {
    return axios.post('/departments', department);
  },
  createDevice(device) {
    return axios.post('/devices', device);
  },
  createModel(model) {
    return axios.post('/models', model);
  }
};

export default ResourceService;
