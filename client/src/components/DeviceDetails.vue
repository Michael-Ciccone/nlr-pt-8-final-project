<template>
    <section id="titles">
        <h2>Device Details</h2>
        <h3>Please <router-link v-bind:to="{ name: 'login' }">Login</router-link> to make any changes.</h3>
    </section>
    <section id="device-details">
        <section id="model-info" v-if="device && model && department">
            <h3>Model Information</h3>
            <p><strong>Device ID:</strong> {{ device.id }}</p>
            <p><strong>Manufacturer:</strong> {{ model.manufacturerName }}</p>
            <p><strong>Model:</strong> {{ model.modelName }}</p>
            <p><strong>Description:</strong> {{ model.modelDescription }}</p>
            <p><strong>Serial Number:</strong> {{ device.serialNumber }}</p>
        </section>

        <section id="maintenance-info" v-if="device">
            <h3>Maintenance Information</h3>
            <p><strong>Owning Department:</strong> {{ department.departmentName }}</p>
            <p><strong>Maintenance Month:</strong> {{ department.maintenanceMonth }}</p>
            <p><strong>Maintenance Schedule:</strong> {{ model.maintenanceSchedule }}</p>
            <p><strong>Assigned Technician:</strong> {{ department.assignedTechnician }}</p>
            <p><strong>Install Date:</strong> {{ device.installDate }}</p>
        </section>

        <section id="device-pic" v-if="device">
            <img :src="`/img/${device.picture}`" alt="Device Picture">
        </section>

        <section id="edit-device-button-container">
            <h3 class="technician-tools">Technician Tools:</h3>
            <div class="vertical-buttons">
                <router-link id="create-device-button" :to="{ name: 'CreateDevice' }">
                    <button>Create New Device</button>
                </router-link>
                <router-link id="update-device-button" :to="{ name: 'UpdateDevice' }">
                    <button>Update Current Device</button>
                </router-link>
                <button @click="deleteDevice">Delete Current Device</button>
                <p v-if="message">{{ message }}</p>
                <p>New or updated model?</p>
                <router-link id="create-model-button" :to="{ name: 'CreateModel' }">
                    <button>Create New Model</button>
                </router-link>
                <router-link id="update-model-button" :to="{ name: 'UpdateModel' }">
                    <button>Update Current Model</button>
                </router-link>
            </div>

        </section>

    </section>
</template>

<script>
import ResourceService from "../services/ResourceService";

export default {
    data() {
        return {
            deviceId: null,
            message: ""
        };
    },
    computed: {
        department() {
            return this.$store.state.department;
        },
        device() {
            return this.$store.state.device;
        },
        model() {
            return this.$store.state.model;
        }
    },
    methods: {

        deleteDevice() {

            this.deviceId = this.$route.params.deviceId;
            ResourceService.deleteDeviceById(this.deviceId).then(() => {
                this.$store.commit('DELETE_DEVICE', this.deviceId);
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
#device-details {
    display: grid;
    grid-template-columns: 1fr 1fr;
    gap: 20px;
    text-align: center;
    position: relative;
}

#model-info,
#maintenance-info {
    background-color: #0f3358;
    color: white;
    border: none;
    border-radius: 5px;
    padding: 20px;
    font-family: Tahoma, sans-serif;
}

#device-pic {
    grid-column: 1 / -1;
    display: flex;
    justify-content: center;
    margin-top: 20px;
}

#device-pic img {
    width: auto;
    height: 500px;
    border-radius: 10px;
}

h2 {
    font-family: Tahoma, sans-serif;
    font-size: 25px;
}

#model-info h3,
#maintenance-info h3 {
    font-family: Tahoma, sans-serif;
    font-size: 20px;
    text-decoration-color: white;
    text-decoration: underline;
}

#titles {
    text-align: center;
    margin-bottom: 20px;
}

#device-details p {
    font-family: Arial, sans-serif;
    font-size: 17px;
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

#edit-device-button-container {
    position: absolute;
    bottom: 50px;
    left: 20px;
}

#edit-device-button-container h3 {
    font-family: Tahoma, sans-serif;
    font-size: 20px;
}
</style>