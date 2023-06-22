<template>
  <div id="app">
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand to="/">Home</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item to="/articles">게시판</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item v-if="!isAuthenticated" to="/login">로그인</b-nav-item>
          <b-nav-item-dropdown v-else right>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <em>{{ getUser }}님</em>
            </template>
            <b-dropdown-item to="/mypage">마이페이지</b-dropdown-item>
            <b-dropdown-item @click="logout">로그아웃</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <b-container class="py-4">
      <router-view />
    </b-container>
  </div>
</template>

<script>
import { mapGetters, mapActions } from "vuex";

export default {
  computed: {
    ...mapGetters(["isAuthenticated", "getUser"]),
  },

  methods: {
    ...mapActions(["logout"]),
  },
};
</script>

<style>
.vr {
  display: inline-block;
  align-self: stretch;
  width: 1px;
  min-height: 1em;
  background-color: currentColor;
  opacity: 0.25;
}

.gap-3 {
  gap: 1rem !important;
}

.border-2 {
  border-width: 2px;
}

.top-0 {
  top: 0 !important;
}

.end-0 {
  right: 0 !important;
}
</style>
