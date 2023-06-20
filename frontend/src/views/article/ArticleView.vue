<template>
  <div>
    <b-card :title="title">
      <b-card-text>
        {{ content }}
      </b-card-text>

      <div v-if="isWriter" class="d-flex justify-content-end gap-3">
        <b-button :to="`/write?articleId=${articleId}`" variant="warning">
          수정
        </b-button>
        <b-button variant="danger" @click="deleteArticle">삭제</b-button>
      </div>
    </b-card>
  </div>
</template>

<script>
import { getArticle, deleteArticle } from "@/api/article";
import { mapGetters } from "vuex";

export default {
  name: "ArticleView",
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
        this.writerId = data.userId;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    deleteArticle() {
      if (confirm("게시글을 삭제하시겠습니까?")) {
        deleteArticle(this.articleId)
          .then((response) => {
            console.log(response);
            alert("게시글 삭제 완료");
            this.$router.push("/articles");
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
  },
};
</script>

<style scoped>
.gap-3 {
  gap: 1rem !important;
}
</style>
