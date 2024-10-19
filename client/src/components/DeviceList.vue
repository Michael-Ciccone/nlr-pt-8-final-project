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
    <section id="edit-department-button-container">
      <h3 class="technician-tools">Technician Tools:</h3>
      <div class="vertical-buttons">
        <router-link id="create-department-button" :to="{ name: 'CreateDepartment' }">
          <button>Create New Department</button>
        </router-link>
        <router-link id="update-department-button" :to="{ name: 'UpdateDepartment' }">
          <button>Update Current Department</button>
        </router-link>
        <button @click="deleteDepartment">Delete Current Department</button>
        <p v-if="message">{{ message }}</p>
      </div>

    </section>
  </section>
</template>

<script>
import ResourceService from '../services/ResourceService';

export default {
  data() {
    return {
      departmentId: null,
      message: ""
    };
  },
  props: {
    departmentName: {
      type: String,
      required: true
    },
  },
  computed: {
    devices() {
      return this.$store.state.devices;
    }
  },
  methods: {
    navigateToDevice(deviceId) {
      this.$router.push({ name: 'DevicePage', params: { deviceId } });
    },
    deleteDepartment() {

      this.departmentId = this.$route.params.departmentId;
      ResourceService.deleteDepartmentById(this.departmentId).then(() => {
        this.$store.commit('DELETE_DEPARTMENT', this.departmentId);
        this.message = "Item successfully deleted... returning home";
        setTimeout(() => {
          this.$router.push({ name: 'home' });
        }, 3000);
      }).catch(() => {
        this.message = "Failed to delete the item. Please try again.";
      })
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
  max-width: 60%;
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
  font-size: 27px;
}

h3 {
  font-family: Tahoma, sans-serif;
  font-size: 17px;
}

#titles {
  text-align: center;
  margin-bottom: 20px;
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
  cursor: pointer;
}

#edit-department-button-container {
  position: absolute;
  bottom: 50px;
  left: 20px;
}

#edit-department-button-container h3 {
  font-family: Tahoma, sans-serif;
  font-size: 17px;
  text-align: left;
}


@media only screen and (max-width: 450px) {
  #devices-button-container {
    grid-template-columns: 1fr;
    max-width: 100%;
    grid-gap: 10px;
  }

  ul button {
    padding: 20px;
    height: auto;
    font-size: 20px;
  }

  h2 {
    font-size: 20px;
  }

  h3 {
    font-size: 15px;
  }

  #titles {
    margin-bottom: 15px;
  }

  #edit-department-button-container {
    margin-top: 30px;
    text-align: center;
    position: static;
  }

  .vertical-buttons {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    gap: 10px;
    max-width: 90%;
  }

  .vertical-buttons button {
    font-size: 12px;
    padding: 4px;
    width: 100%;
    max-width: 200px;
  }

  #edit-department-button-container h3 {
    font-family: Tahoma, sans-serif;
    font-size: 12px;
  }
}
</style>