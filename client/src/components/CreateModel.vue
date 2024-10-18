<template>
  <div id="create-model">
    <form v-on:submit.prevent="createModel">
      <h1>Create Model</h1>
      <div id="fields">
        <label for="modelName">Model Name</label>
        <input type="text" id="modelName" placeholder="Model name..." v-model="model.modelName" required autofocus />
        <label for="manufacturerName">Manufacturer Name</label>
        <input type="text" id="manufacturerName" placeholder="Manufacturer name..." v-model="model.manufacturerName"
          required />
        <label for="maintenanceSchedule">Maintenance Schedule</label>
        <input type="text" id="maintenanceSchedule" placeholder="Maintenance schedule..."
          v-model="model.maintenanceSchedule" required />
        <label for="description">Description</label>
        <input type="text" id="description" placeholder="Description..." v-model="model.modelDescription" required />
        <div></div>
        <div>
          <button type="submit">Create Model</button>
        </div>
      </div>
      <hr />
      <p v-if="message">{{ message }}</p>
    </form>
  </div>
</template>
  
<script>
import ResourceService from '../services/ResourceService';

export default {
  data() {
    return {
      model: {
        modelName: "",
        manufacturerName: "",
        maintenanceSchedule: "",
        modelDescription: "",
      },
      message: ""
    };
  },
  methods: {
    createModel() {
      ResourceService.createModel(this.model).then((response) => {

        const newModel = response.data;
        this.$store.commit('ADD_MODEL', newModel);

        this.message = "Item successfully created... returning home";
        setTimeout(() => {
          this.$router.push({ name: 'home' });
        }, 3000);
      }).catch(() => {
        this.message = "Failed to create the item. Please try again.";
      })
    }
  }
}
</script>
  
<style scoped>
#create-model {
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

button {
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

button:hover {
  background-color: #0056b3;
}

hr {
  border: none;
  height: 1px;
  background-color: #e1e1e1;
  margin: 20px 0;
}
</style>