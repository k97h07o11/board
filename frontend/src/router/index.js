import Vue from "vue";
import VueRouter from "vue-router";
import store from "@/store/index";

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
    beforeEnter: (to, from, next) => {
      to.query.url ??= from.fullPath;
      next();
    },
  },
  {
    path: "/articles",
    name: "list",
    component: () => import("@/views/article/ArticleListView"),
  },
  {
    path: "/view",
    name: "view",
    component: () => import("@/views/article/ArticleView"),
  },
  {
    path: "/write",
    name: "write",
    component: () => import("@/views/article/ArticleWriteView"),
    meta: { requiresAuth: true },
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach((to, from, next) => {
  if (
    to.matched.some((record) => record.meta.requiresAuth) &&
    !store.getters.isAuthenticated
  ) {
    alert("로그인이 필요한 서비스입니다.");
    next({
      path: "/login",
      query: { url: to.fullPath },
    });
  } else {
    next();
  }
});

export default router;
