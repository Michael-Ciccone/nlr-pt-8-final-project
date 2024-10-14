<template>
    <section id="tech-tools">
        <section id="titles">
            <h2>Technician Tools</h2>
            <h3>Please select an action to make changes to the system.</h3>
        </section>

        <ul id="tools-button-container">
            <li>
                <router-link id="edit-button" :to="{ name: 'CreateDepartment' }">
                    <button>Create New Department</button>
                </router-link>
            </li>
            <li>
                <router-link id="edit-button" :to="{ name: 'CreateDevice' }">
                    <button>Create New Technician</button>
                </router-link>
            </li>
            <li>
                <router-link id="edit-button" :to="{ name: 'UpdateDepartment' }">
                    <button>Update Existing Department</button>
                </router-link>
            </li>
            <li>
                <router-link id="edit-button" :to="{ name: 'CreateDevice' }">
                    <button>Update Existing Technician</button>
                </router-link>
            </li>
        </ul>
    </section>
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
    }
}
</script>

<style>

#tools-button-container {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    grid-template-rows: repeat(5, auto);
    grid-gap: 15px;
    list-style-type: none;
    padding: 0;
    margin: 0 auto;
    max-width: 70%;
}


#edit-button button {
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

#edit-button button:hover {
    background-color: #0056b3;
}

#edit-button {
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
}

</style>