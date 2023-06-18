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

        <b-button variant="primary" block @click="join">회원가입</b-button>
      </b-form>
    </b-card>
  </div>
</template>

<script>
import { join } from "@/api/user";

export default {
  name: "JoinView",
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    join: function () {
      join({
        username: this.username,
        password: this.password,
      })
        .then((response) => {
          console.log(response);
          alert("회원가입 완료");
          this.$router.push("/login");
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
