<template>
  <div>
    <h2 class="text-center">회원가입</h2>

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
            ></b-form-input>
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

        <b-button variant="primary" block @click="join" size="lg">
          회원가입
        </b-button>
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
    join() {
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
