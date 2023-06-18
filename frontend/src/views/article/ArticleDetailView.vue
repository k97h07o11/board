<template>
  <div>
    <b-card :title="title">
      <b-card-text>
        {{ content }}
      </b-card-text>

      <div v-if="isWriter" class="d-flex justify-content-end gap-3">
        <b-button href="#" variant="warning">수정</b-button>
        <b-button href="#" variant="danger">삭제</b-button>
      </div>
    </b-card>
  </div>
</template>

<script>
import { getArticle } from "@/api/article";
import { mapGetters } from "vuex";

export default {
  name: "ArticleDetailView",
  data() {
    return {
      articleId: null,
      title: null,
      content: null,
      writerId: null,
    };
  },
  computed: {
    ...mapGetters(["getUser"]),
    isWriter() {
      return this.getUser == this.writerId;
    },
  },
  created() {
    this.articleId = this.$route.query.articleId;
    getArticle(this.articleId)
      .then((response) => {
        let data = response.data;
        this.title = data.title;
        this.content = data.content;
        this.writerId = data.user.id;
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>

<style scoped>
.gap-3 {
  gap: 1rem !important;
}
</style>
