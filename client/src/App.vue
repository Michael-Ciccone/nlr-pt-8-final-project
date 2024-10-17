<template>
  <div id="medical-app">
    <header>
      <img id="logo" src="img/Health-Medical-Logo-design-on-transparent-background-PNG.png" alt="Generic Medical Logo">
    <h1>Medical Device Inventory</h1>
    <nav id="navigation">
      <div class="button-container">
        <router-link v-bind:to="{ name: 'home' }">
          <button>Home</button>
        </router-link>
        <router-link v-bind:to="{ name: 'home' }">
          <button>About</button>
        </router-link>
        <router-link v-bind:to="{ name: 'home' }">
          <button>Contact Us</button>
        </router-link>
        <router-link v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token">
          <button>Logout</button>
        </router-link>
        <router-link v-bind:to="{ name: 'login' }" v-else>
          <button>Login</button>
        </router-link>
      </div>
        <input type="text" placeholder="Search By Device ID..." v-model="deviceId"
          @keyup.enter="navigateToDevice"/>
    </nav>
    </header>
    <main>
      <router-view />
    </main>
    <footer>
      <p>Copyright: Medical Equipment Services LLC, 2024 &copy;</p>
    </footer>
  </div>
</template>

<script>
import ResourceService from './services/ResourceService';

export default {
  data() {
    return {
      deviceId: '',
    };
  },
  methods: {
    navigateToDevice() {
      const deviceId = parseInt(this.deviceId);
      ResourceService.getDeviceById(deviceId).then(response => {
        
          if (response && response.data) {
            this.$router.push({ name: 'DevicePage', params: { deviceId } });
          } else {
            this.$router.push({ name: 'Error' });
          }
        });
    },
  },
}
</script>



<style scoped>

#medical-app {
    background-color: white;
    height: 100%;
    margin: 0;
    padding: 0;
    min-height: 100vh;
}

h1 {
    font-family:Tahoma, sans-serif;
    font-size: 45px;
    flex-grow: 2;
    margin-left: auto;
  }

header {
    display: flex;
    grid-area: head;
    background-color: rgb(178, 222, 161);
    border-radius: 5px;
    width: 100%;
    height: auto;
    align-items: center;
    gap: 50px;
}

#logo {
    width: 150px;
    height: 150px;
  }

footer {
    grid-area: foot;
    background-color: rgb(178, 222, 161);
    text-align: center;
    width: 100%;
    border-radius: 5px;
    margin-bottom: 5px;
    height: 25px;
}

main {
  flex-grow: 1;
}

button {
  background-color: #0775ec;
  width: 140px;
  border-radius: 5px;
  text-align: center;
  line-height: 50px;
  box-shadow: 3px 3px 3px rgb(99, 98, 98);
  color: aliceblue;
  border: none;
  cursor: pointer;
  justify-items: end;
  
}

button:hover {
  background-color: #0056b3;
}

.button-container {
  display: flex;
  gap: 10px;
}

input[type="text"] {
  width: 200px;
  height: 30px;
  border-radius: 5px;
  border: 1px solid #ccc;
  padding-left: 10px;
}

input:focus {
  border-color: #0775ec;
  outline: none;
}


nav {
    grid-area: nav;
    margin-left: auto;
    display: flex;
    margin: 0;
    gap: 10px;
    flex-direction: column;
    align-items: center;
    justify-content: flex-end;
    padding-right: 20px;
}

</style>