<template>
  <div>
    <b-table :fields="fields" :items="items">
      <template #cell(title)="data">
        <router-link :to="`/view?articleId=${data.item.id}`">
          {{ data.value }}
        </router-link>
      </template>

      <template #cell(createdDate)="data">
        {{ data.value | formatDate }}
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

function isToday(date) {
  const today = new Date();
  return (
    date.getFullYear() === today.getFullYear() &&
    date.getMonth() === today.getMonth() &&
    date.getDate() === today.getDate()
  );
}

export default {
  name: "ArticleListView",

  filters: {
    formatDate(dateString) {
      const date = new Date(dateString);
      if (isToday(date)) {
        const hours = String(date.getHours()).padStart(2, "0");
        const minutes = String(date.getMinutes()).padStart(2, "0");
        return `${hours}:${minutes}`;
      } else {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, "0");
        const day = String(date.getDate()).padStart(2, "0");
        return `${year}-${month}-${day}`;
      }
    },
  },

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
