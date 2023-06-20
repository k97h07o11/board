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
          <b-button variant="primary" class="float-right" @click="writeArticle">
            등록
          </b-button>
        </div>
      </b-form>
    </b-card>
  </div>
</template>

<script>
import { writeArticle } from "@/api/article";

export default {
  name: "ArticleWriteView",
  data() {
    return {
      title: "",
      content: "",
    };
  },
  methods: {
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
  },
};
</script>
