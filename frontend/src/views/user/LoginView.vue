<template>
  <div>
    <h2 class="text-center">로그인</h2>

    <b-card>
      <b-form>
        <b-form-group>
          <b-input-group size="lg">
            <b-input-group-prepend is-text>
              <b-icon icon="person"></b-icon>
            </b-input-group-prepend>
            <b-form-input
              type="text"
              id="username"
              name="username"
              v-model="username"
              placeholder="아이디"
            >
            </b-form-input>
          </b-input-group>
        </b-form-group>

        <b-form-group>
          <b-input-group size="lg">
            <b-input-group-prepend is-text>
              <b-icon icon="lock"></b-icon>
            </b-input-group-prepend>
            <b-form-input
              type="password"
              id="password"
              name="password"
              v-model="password"
              placeholder="비밀번호"
            ></b-form-input>
          </b-input-group>
        </b-form-group>

        <b-button variant="primary" block @click="login" size="lg">
          로그인
        </b-button>
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
          this.$router.push(this.$route.query.url ?? "/");
        })
        .catch((error) => {
          console.log(error);
          alert("아이디 또는 비밀번호를 확인해주세요.");
        });
    },
  },
};
</script>
