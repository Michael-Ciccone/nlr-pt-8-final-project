<template>
    <section id="titles">
    <h2>Sorry, the device you are looking for does not exist.</h2>
    <h3>Please <router-link v-bind:to="{ name: 'CreateDevice' }">Create Device</router-link> or try a different ID.</h3>
</section>
  </template>

  <style scoped>
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
    margin-top: 50px;
    margin-bottom: 50px;
}
  </style>