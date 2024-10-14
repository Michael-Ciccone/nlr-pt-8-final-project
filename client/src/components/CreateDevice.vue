<template>
  <section id="full-page">
    <div id="create-device">
      <form v-on:submit.prevent="createDevice">
        <h1>Create Device</h1>
        <div id="fields">
          <label for="serialNumber">Serial Number</label>
          <input type="text" id="serialNumber" placeholder="Serial number..." v-model="device.serialNumber" required
            autofocus />
          <label for="modelId">Model ID</label>
          <input type="text" id="modelId" placeholder="Model ID..." v-model="device.modelId" required />
          <label for="owningDepartment">Owning Department</label>
          <input type="text" id="owningDepartment" placeholder="Owning department..." v-model="device.owningDepartment"
            required />
          <label for="installDate">Install Date</label>
          <input type="text" id="installDate" placeholder="Install date..." v-model="device.installDate" required />
          <label for="picture">Image URL</label>
          <input type="text" id="picture" placeholder="Image URL..." v-model="device.picture" required />
          <div></div>
          <div>
            <button type="submit">Create Device</button>
          </div>
        </div>
        <hr />
      </form>
    </div>
    <section id="edit-model-button-container">
      <h3 class="technician-tools">Technician Tools:</h3>
      <p>New or updated model?</p>
      <div class="vertical-buttons">
        <router-link id="create-model-button" :to="{ name: 'UpdateDepartment' }">
          <button>Create New Model</button>
        </router-link>
        <router-link id="update-model-button" :to="{ name: 'DeleteDepartment' }">
          <button>Update Existing Model</button>
        </router-link>
      </div>

    </section>
  </section>
</template>
  
<script>
import ResourceService from '../services/ResourceService';

export default {
  data() {
    return {
      device: {
        serialNumber: "",
        modelId: null,
        owningDepartment: null,
        installDate: "",
        pic: ""
      },
    };
  },
  methods: {
    createDevice() {
      ResourceService.createDevice(this.device).then(() => {
        this.$router.push({ name: 'home' });
      }).catch((error) => {
        console.error('Failed to create device:', error);
      });
    }
  }
};
</script>
  
<style scoped>
#full-page {
  position: relative;
}

#create-device {
  max-width: 500px;
  margin: 80px auto;
  padding: 40px;
  background-color: rgb(178, 222, 161);
  border-radius: 10px;
  box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  color: #333;
  font-size: 26px;
  margin-bottom: 20px;
}

#fields {
  display: flex;
  flex-direction: column;
}

label {
  font-size: 14px;
  margin-bottom: 8px;
  color: #333;
}

input {
  padding: 10px;
  margin-bottom: 15px;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
  width: 100%;
  box-sizing: border-box;
}

input:focus {
  border-color: #0056b3;
  outline: none;
}

#fields button {
  width: 100%;
  padding: 12px;
  background-color: #0775ec;
  color: white;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover,
#fields button:hover {
  background-color: #0056b3;
}

hr {
  border: none;
  height: 1px;
  background-color: #e1e1e1;
  margin: 20px 0;
}

.technician-tools {
  font-size: 15px;
}

.vertical-buttons {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.vertical-buttons button {
  margin-bottom: 10px;
}

#edit-model-button-container {
  position: absolute;
  bottom: 20px;
  left: 20px;
}
</style>