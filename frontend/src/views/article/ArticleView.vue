<template>
  <div>
    <b-card :title="title" title-tag="h2">
      <b-card-text>
        {{ content }}
      </b-card-text>

      <div v-if="isWriter" class="d-flex justify-content-end gap-3">
        <b-button :to="`/write?articleId=${articleId}`" variant="warning">
          수정
        </b-button>
        <b-button variant="danger" @click="deleteArticle">삭제</b-button>
      </div>

      <hr />

      <div>
        <h3>댓글</h3>

        <b-card class="border-2">
          <div class="font-weight-bold mb-2">{{ getUser }}</div>

          <b-form-textarea
            id="comment"
            v-model="newComment"
            placeholder="댓글"
            rows="3"
            max-rows="10"
            maxlength="3000"
            class="border-0 p-0 shadow-none mb-3"
          ></b-form-textarea>

          <div class="clearfix">
            <b-button variant="primary" class="float-right">등록</b-button>
          </div>
        </b-card>

        <b-list-group flush>
          <b-list-group-item v-for="comment in comments" :key="comment.id">
            <div class="font-weight-bold">{{ comment.username }}</div>
            <div>{{ comment.content }}</div>
            <div>{{ comment.createdDate }}</div>
            <b-dropdown
              right
              variant="link"
              toggle-class="text-decoration-none shadow-none p-0"
              no-caret
              class="position-absolute top-0 end-0 mt-3 mr-3"
            >
              <template #button-content>
                <b-icon icon="three-dots-vertical" variant="secondary"></b-icon>
              </template>
              <b-dropdown-item href="#">수정</b-dropdown-item>
              <b-dropdown-item href="#">삭제</b-dropdown-item>
            </b-dropdown>
          </b-list-group-item>
        </b-list-group>
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
      newComment: "",
      comments: [
        {
          content: "댓글 1",
          userId: 1,
          username: "username",
          createdDate: "YYYY-MM-DD",
        },
        {
          content: "댓글 2",
          userId: 2,
          username: "k97h07o11",
          createdDate: "YYYY-MM-DD",
        },
      ],
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

.border-2 {
  border-width: 2px;
}

.top-0 {
  top: 0 !important;
}

.end-0 {
  right: 0 !important;
}
</style>
