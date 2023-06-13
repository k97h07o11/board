import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("@/views/HomeView"),
  },
  {
    path: "/join",
    name: "join",
    component: () => import("@/views/user/JoinView"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/user/LoginView"),
  },
  {
    path: "/article/list",
    name: "list",
    component: () => import("@/views/article/ArticleListView"),
  },
  {
    path: "/article/detail",
    name: "detail",
    component: () => import("@/views/article/ArticleDetailView"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
