<template>
  <div>
    <b-card :title="title">
      <b-card-text>
        {{ content }}
      </b-card-text>

      <b-button href="#" variant="warning">수정</b-button>
      <b-button href="#" variant="danger">삭제</b-button>
    </b-card>
  </div>
</template>

<script>
import { getArticle } from "@/api/article";

export default {
  name: "ArticleDetailView",
  data() {
    return {
      title: "",
      content: "",
    };
  },
  created() {
    let articleId = this.$route.query.articleId;
    getArticle(articleId)
      .then((response) => {
        let data = response.data;
        this.title = data.title;
        this.content = data.content;
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>
