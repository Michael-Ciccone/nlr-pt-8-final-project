<template>
    <div id="create-department">
      <form v-on:submit.prevent="createDepartment">
        <h1>Create Department</h1>
        <div id="fields">
          <label for="departmentName">Department Name</label>
          <input
            type="text"
            id="departmentName"
            placeholder="Department name..."
            v-model="department.departmentName"
            required
            autofocus
          />
          <label for="maintenanceMonth">Maintenance Month</label>
          <input
            type="text"
            id="maintenanceMonth"
            placeholder="Maintenance month..."
            v-model="department.maintenanceMonth"
            required
          />
          <label for="assignedTechnician">Assigned Technician</label>
          <input
            type="text"
            id="assignedTechnician"
            placeholder="Assigned technician..."
            v-model="department.assignedTechnician"
            required
          />
          <div></div>
          <div>
            <button type="submit">Create Department</button>
          </div>
        </div>
        <hr />
      </form>
    </div>
  </template>
  
  <script>
  import ResourceService from '../services/ResourceService';
  
  export default {
    data() {
      return {
        department: {
          departmentName: "",
          maintenanceMonth: null,
          assignedTechnician: "",
        },
      };
    },
    methods: {
        createDepartment() {
      ResourceService.createDepartment(this.department).then(() => {
        this.$router.push({ name: 'home' });
      }).catch((error) => {
        console.error('Failed to create department:', error);
      });
    }
  }
  };
  </script>
  
  <style scoped>
  #create-department {
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