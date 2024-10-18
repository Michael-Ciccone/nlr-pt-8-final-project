import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/LoginView.vue'
import LogoutView from '../views/LogoutView.vue'
import RegisterView from '../views/RegisterView.vue'
import DevicesView from '../views/DevicesView.vue'
import DeviceDetailsView from '../views/DeviceDetailsView.vue'
import CreateDepartmentView from '../views/CreateDepartmentView.vue'
import UpdateDepartmentView from '../views/UpdateDepartmentView.vue'
import CreateDeviceView from '../views/CreateDeviceView.vue'
import UpdateDeviceView from '../views/UpdateDeviceView.vue'
import CreateModelView from '../views/CreateModelView.vue'
import UpdateModelView from '../views/UpdateModelView.vue'
import ErrorView from '../views/ErrorView.vue'


/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/devices/:departmentId',
    name: 'DeviceList',
    component: DevicesView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/device/:deviceId',
    name: 'DevicePage',
    component: DeviceDetailsView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/error',
    name: 'Error',
    component: ErrorView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: '/departments/create',
    name: 'CreateDepartment',
    component: CreateDepartmentView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/department/update/:departmentId',
    name: 'UpdateDepartment',
    component: UpdateDepartmentView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/devices/create',
    name: 'CreateDevice',
    component: CreateDeviceView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/device/update/:deviceId',
    name: 'UpdateDevice',
    component: UpdateDeviceView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/models/create',
    name: 'CreateModel',
    component: CreateModelView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: '/model/update/:deviceId',
    name: 'UpdateModel',
    component: UpdateModelView,
    meta: {
      requiresAuth: true
    }
  }
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return { name: "login" };
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
