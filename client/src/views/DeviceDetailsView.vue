<template>
  <div class="devices">
    <device-details />
  </div>
</template>
  
<script>
import DeviceDetails from '../components/DeviceDetails.vue';
import ResourceService from '../services/ResourceService';

export default {
  components: { DeviceDetails },

  created() {
    this.handleDeviceInfo();
  },
  methods: {
    handleDeviceInfo() {
      try {
        const deviceId = parseInt(this.$route.params.deviceId);

        if (deviceId) {
          ResourceService.getDeviceById(deviceId).then((response) => {

            const device = response.data;
            this.$store.commit("SET_DEVICE", device);


            const departments = this.$store.state.departments;
            const models = this.$store.state.models;

            const departmentMatch = departments.find(department => department.id === device.owningDepartment);
            if (departmentMatch) {
              this.$store.commit("SET_DEPARTMENT", departmentMatch);
            }

            const modelMatch = models.find(model => model.id === device.modelId);
            if (modelMatch) {
              this.$store.commit("SET_MODEL", modelMatch);
            }

          });
        }
      } catch (error) {
        console.error("Error in handleDeviceInfo method:", error);
      }
    }
  },
  watch: {
    '$route.params.deviceId': function (newDeviceId) {
      this.handleDeviceInfo(newDeviceId);
    }
  }
}

</script>
  
<style></style>