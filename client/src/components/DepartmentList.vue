<template>
    <section id="department-list">
        <section id="titles">
            <h2>Welcome to Your Local Healthcare Facility!</h2>
            <h3>Select a department to anonymously browse the inventory. Please <router-link
                    v-bind:to="{ name: 'login' }">Login</router-link> to make any changes.</h3>
        </section>

        <ul id="departments-button-container">
            <li v-for="department in departments" :key="department.id">
                <router-link id="department-button"
                    v-bind:to="{ name: 'DeviceList', params: { departmentId: department.id } }">
                    <button>{{ department.departmentName }}</button>
                </router-link>

            </li>
        </ul>
    </section>
</template>

<script>

export default {
    computed: {
        departments() {
            return this.$store.state.departments;
        }
    }
}
</script>

<style>
#department-list {
    position: relative;
    margin-bottom: 40px;
}

#departments-button-container {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: repeat(5, auto);
    grid-gap: 15px;
    list-style-type: none;
    padding: 0;
    margin: 0 auto;
    max-width: 70%;
}

.department-item {
    display: flex;
    justify-content: center;
}

#department-button button {
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
    text-decoration: none;
}

#department-button button:hover {
    background-color: #0056b3;
}

#department-button {
    text-decoration: none;
}

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
    margin-bottom: 20px;
    margin-top: 40px;
}
</style>