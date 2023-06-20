<template>
  <div>
    <b-card>
      <b-form>
        <b-form-input
          type="text"
          id="title"
          v-model="title"
          placeholder="제목"
        ></b-form-input>

        <b-form-textarea
          id="content"
          v-model="content"
          placeholder="내용"
          rows="6"
          no-resize
        ></b-form-textarea>

        <div class="clearfix">
          <b-button variant="primary" class="float-right" @click="register">
            등록
          </b-button>
        </div>
      </b-form>
    </b-card>
  </div>
</template>

<script>
import { getArticle, writeArticle, editArticle } from "@/api/article";

export default {
  name: "ArticleWriteView",
  data() {
    return {
      title: "",
      content: "",
    };
  },
  created() {
    if (this.$route.query.articleId !== undefined) {
      getArticle(this.$route.query.articleId)
        .then((response) => {
          let data = response.data;
          this.title = data.title;
          this.content = data.content;
        })
        .catch((error) => {
          console.log(error);
        });
    }
  },
  methods: {
    register: function () {
      if (this.$route.query.articleId === undefined) {
        this.writeArticle();
      } else {
        this.editArticle();
      }
    },
    writeArticle() {
      writeArticle({
        title: this.title,
        content: this.content,
      })
        .then((response) => {
          const articleId = response.data;
          this.$router.push(`/view?articleId=${articleId}`);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    editArticle() {
      editArticle(this.$route.query.articleId, {
        title: this.title,
        content: this.content,
      })
        .then((response) => {
          console.log(response);
          this.$router.push(`/view?articleId=${this.$route.query.articleId}`);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
