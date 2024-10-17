<template>
  <div class="devices">
    <device-list :departmentName="departmentName" />
  </div>
</template>
  
<script>
import DeviceList from '../components/DeviceList.vue';
import ResourceService from '../services/ResourceService';


export default {
  components: { DeviceList },
  data() {
    return {
      departmentName: '',
    };
  },
  created() {

    try {
      const departmentId = parseInt(this.$route.params.departmentId);
      ResourceService.getDepartmentById(departmentId).then((response) => {
        this.departmentName = response.data.departmentName;
      });
      ResourceService.getDevicesByDepartment(departmentId).then((response) => {
        this.$store.commit("SET_DEVICES", response.data);
      });
    } catch (error) {
      console.error("Error fetching department or devices:", error);
    }
  },
}
</script>
  
<style></style>