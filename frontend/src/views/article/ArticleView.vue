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

        <CommentWriter v-model="newComment" @register="writeComment" />

        <b-list-group flush>
          <b-list-group-item v-for="comment in comments" :key="comment.id">
            <template
              v-if="!isAuthenticated || comment.id != editingComment.id"
            >
              <div class="font-weight-bold">{{ comment.username }}</div>
              <div>{{ comment.content }}</div>
              <div>{{ comment.createdDate }}</div>
              <b-dropdown
                v-if="comment.userId == getUser"
                right
                variant="link"
                toggle-class="text-decoration-none shadow-none p-0"
                no-caret
                class="position-absolute top-0 end-0 mt-3 mr-3"
              >
                <template #button-content>
                  <b-icon
                    icon="three-dots-vertical"
                    variant="secondary"
                  ></b-icon>
                </template>
                <b-dropdown-item @click="editComment(comment)">
                  수정
                </b-dropdown-item>
                <b-dropdown-item @click="deleteComment(comment)">
                  삭제
                </b-dropdown-item>
              </b-dropdown>
            </template>
            <CommentWriter
              v-else
              v-model="editingComment.content"
              edit
              @cancel="cancelEditComment"
            />
          </b-list-group-item>
        </b-list-group>
      </div>
    </b-card>
  </div>
</template>

<script>
import { getArticle, deleteArticle } from "@/api/article";
import { getComments, writeComment, deleteComment } from "@/api/comment";
import { mapGetters } from "vuex";
import CommentWriter from "@/components/CommentWriter.vue";

export default {
  name: "ArticleView",
  components: {
    CommentWriter,
  },
  data() {
    return {
      articleId: null,
      title: null,
      content: null,
      writerId: null,
      newComment: "",
      comments: [],
      editingComment: {
        id: null,
        content: null,
      },
    };
  },
  computed: {
    ...mapGetters(["isAuthenticated", "getUser"]),
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

    this.getComments();
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
    getComments() {
      getComments(this.articleId)
        .then((response) => {
          this.comments = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    writeComment() {
      writeComment(this.articleId, {
        content: this.newComment,
      })
        .then((response) => {
          console.log(response);
          this.newComment = "";
          this.getComments();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    editComment(comment) {
      this.editingComment.content = comment.content;
      this.editingComment.id = comment.id;
    },
    cancelEditComment() {
      this.editingComment.id = null;
      this.editingComment.content = null;
    },
    deleteComment(comment) {
      if (confirm("댓글을 삭제하시겠습니까?")) {
        deleteComment(comment.id)
          .then((response) => {
            console.log(response);
            this.getComments();
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

.top-0 {
  top: 0 !important;
}

.end-0 {
  right: 0 !important;
}
</style>
