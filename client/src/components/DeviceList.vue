<template>
  <section id="device-list">
    <section id="titles">
      <h2>Devices in {{ departmentName }}</h2>
      <h3>Select a device to anonymously browse the inventory. Please <router-link
          v-bind:to="{ name: 'login' }">Login</router-link> to make any changes.</h3>
    </section>
    <ul id="devices-button-container">
      <li v-for="device in devices" :key="device.id" @click="navigateToDevice(device.id)" style="cursor: pointer;">
        <button>Asset {{ device.id }}</button>
      </li>
    </ul>
  </section>
</template>

<script>
import ResourceService from "../services/ResourceService";

export default {
  data() {
    return {
      devices: [],
      departmentName: ''
    };
  },
  created() {
    try {
      const departmentId = parseInt(this.$route.params.departmentId);
      const department = ResourceService.getDepartmentById(departmentId);

      this.departmentName = department.departmentName;

      this.devices = ResourceService.getDevicesByDepartment(departmentId);
    } catch (error) {
      console.error("Error fetching department or devices:", error);
    }
  },
  methods: {
    navigateToDevice(deviceId) {
      this.$router.push({ name: 'DevicePage', query: { deviceId } });
    }
  }
}
</script>

<style>
#device-list {
  position: relative;
}

#devices-button-container {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: repeat(5, auto);
  grid-gap: 15px;
  list-style-type: none;
  padding: 0;
  margin: 0 auto;
  max-width: 70%;
}

.department-item {
  display: flex;
  justify-content: center;
}

ul button {
  padding: 30px 40px;
  width: 100%;
  height: 150px;
  font-size: 30px;
  background-color: #0f3358;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s;
  display: flex;
  align-items: center;
  justify-content: center;
  font-family: Tahoma, sans-serif;
}

button:hover {
  background-color: #0056b3;
}

h2 {
  font-family: Tahoma, sans-serif;
  font-size: 25px;
}

h3 {
  font-family: Tahoma, sans-serif;
  font-size: 17px;
}

#titles {
  text-align: center;
  margin-bottom: 20px;
}
</style>