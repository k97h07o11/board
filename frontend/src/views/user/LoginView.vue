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
    login: function () {
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
