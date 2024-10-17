<template>
  <div class="home">
    <department-list />
  </div>
</template>

<script>
import DepartmentList from '../components/DepartmentList.vue';
import ResourceService from '../services/ResourceService';

export default {
  components: { DepartmentList },

  created() {
    ResourceService.getDepartments().then((response) => {
      this.$store.commit("SET_DEPARTMENTS", response.data);
    });
    ResourceService.getModels().then((response) => {
      this.$store.commit("SET_MODELS", response.data);
    });
  }
}
</script>

<style></style>