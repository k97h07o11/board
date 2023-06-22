<template>
  <b-card class="border-2">
    <template v-if="isAuthenticated">
      <div class="font-weight-bold mb-2">
        {{ getUser }}
      </div>

      <b-form-textarea
        :value="value"
        @update="onUpdate"
        placeholder="댓글"
        rows="3"
        max-rows="10"
        maxlength="3000"
        class="border-0 p-0 shadow-none mb-3"
      ></b-form-textarea>

      <div
        v-if="value"
        class="position-absolute top-0 end-0 mt-3 mr-4 text-muted"
      >
        {{ value.length }}/3000
      </div>

      <div class="d-flex justify-content-end gap-3">
        <b-button v-if="edit" variant="secondary" @click="cancel">
          취소
        </b-button>
        <b-button variant="primary" @click="register">등록</b-button>
      </div>
    </template>
    <b-card-text v-else class="text-muted font-weight-bold">
      <router-link to="/login">로그인</router-link>이 필요한 서비스입니다.
    </b-card-text>
  </b-card>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "CommentWriter",

  props: {
    value: {
      type: String,
    },

    edit: {
      type: Boolean,
      default: false,
    },
  },

  computed: {
    ...mapGetters(["isAuthenticated", "getUser"]),
  },

  methods: {
    onUpdate(value) {
      this.$emit("input", value);
    },

    cancel() {
      this.$emit("cancel");
    },

    register() {
      this.$emit("register");
    },
  },
};
</script>
