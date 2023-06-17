import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
import Cookies from "js-cookie";
import jwtDecode from "jwt-decode";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    token: null,
    user: null,
  },
  getters: {
    isAuthenticated: (state) => state.token !== null,
    getUser: (state) => state.user,
  },
  mutations: {
    setToken(state, token) {
      state.token = token;
    },
    setUser(state, user) {
      state.user = user;
    },
    logout(state) {
      state.token = null;
      state.user = null;
    },
  },
  actions: {
    login({ commit }, token) {
      commit("setToken", token);
      const decodedToken = jwtDecode(token);
      const user = decodedToken.sub;
      commit("setUser", user);
    },
    logout({ commit }) {
      commit("logout");
    },
  },
  modules: {},
  plugins: [
    createPersistedState({
      storage: {
        getItem: (key) => Cookies.get(key),
        setItem: (key, value) => Cookies.set(key, value),
        removeItem: (key) => Cookies.remove(key),
      },
    }),
  ],
});
