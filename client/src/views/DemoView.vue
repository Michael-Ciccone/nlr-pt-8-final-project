<template>
  <div class="home">
  <section id="titles">
      <h2>Welcome to Your Local Healthcare Facility!</h2>
      <h3>Select a department to anonymously browse the inventory, but please <router-link v-bind:to="{ name: 'login' }">Login</router-link> to make any changes.</h3>
    </section>

    <ul id="departments-button-container">
      <li v-for="department in departments" :key="department.departmentName">
        <!-- <router-link :to="{ name: department.departmentName }"></router-link> -->
          <button>{{ department.departmentName }}</button>
      
      </li>
    </ul>
  </div>
</template>

<script>
import ResourceService from "../services/ResourceService";

export default {
  data() {
    return {
      departments: []
    };
  },
  created() {

    this.departments = ResourceService.getDepartments();
    console.log(this.departments);

  }

}
</script>

<style>

h2 {
  font-family:Tahoma, sans-serif;
  font-size: 25px;
} 

h3 {
  font-family:Tahoma, sans-serif;
  font-size: 17px;
}

#titles {
  text-align: center;
  margin-bottom: 20px;
}

#departments-button-container {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: repeat(5, auto);
  grid-gap: 15px;
  list-style-type: none;
  padding: 0;
  margin: 0 auto;
  max-width: 50%;
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
  font-family:Tahoma, sans-serif;
}

button:hover {
  background-color: #0056b3;
}

</style>