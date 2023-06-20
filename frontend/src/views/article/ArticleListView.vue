<template>
  <div>
    <b-table :fields="fields" :items="items">
      <template #cell(title)="data">
        <router-link :to="`/view?articleId=${data.item.id}`">
          {{ data.value }}
        </router-link>
      </template>
    </b-table>

    <div class="clearfix">
      <b-button to="/write" variant="primary" class="float-right">
        글쓰기
      </b-button>
    </div>

    <b-pagination
      align="center"
      :per-page="perPage"
      :total-rows="totalElements"
      v-model="currentPage"
    ></b-pagination>
  </div>
</template>

<script>
import { getArticleList } from "@/api/article";

export default {
  name: "ArticleListView",
  data() {
    return {
      fields: [
        { key: "id", label: "번호" },
        { key: "title", label: "제목" },
        { key: "username", label: "작성자" },
        { key: "createdDate", label: "작성일" },
        { key: "view", label: "조회" },
      ],
      items: [],
      currentPage: 1,
      totalElements: 0,
      perPage: 20,
    };
  },
  watch: {
    currentPage: "getArticleList",
  },
  created() {
    this.getArticleList();
  },
  methods: {
    getArticleList() {
      getArticleList(this.currentPage - 1)
        .then((response) => {
          let data = response.data;
          this.items = data.content;
          this.totalElements = data.totalElements;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>
