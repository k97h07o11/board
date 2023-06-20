<template>
  <div>
    <b-card>
      <b-form>
        <b-form-input
          type="text"
          id="username"
          name="username"
          v-model="username"
          placeholder="아이디"
        ></b-form-input>

        <b-form-input
          type="password"
          id="password"
          name="password"
          v-model="password"
          placeholder="비밀번호"
        ></b-form-input>

        <b-button variant="primary" block @click="login">로그인</b-button>
      </b-form>
    </b-card>
    <div class="d-flex justify-content-center gap-3 mt-3">
      <b-link href="#">아이디 찾기</b-link>
      <div class="vr"></div>
      <b-link href="#">비밀번호 찾기</b-link>
      <div class="vr"></div>
      <b-link to="/join">회원가입</b-link>
    </div>
  </div>
</template>

<script>
import { login } from "@/api/user";

export default {
  name: "LoginView",
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    login() {
      login(this.username, this.password)
        .then((response) => {
          const token = response.data;
          this.$store.dispatch("login", token);
          this.$router.push("/");
        })
        .catch((error) => {
          console.log(error);
          alert("아이디 또는 비밀번호를 확인해주세요.");
        });
    },
  },
};
</script>

<style scoped>
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
</style>
