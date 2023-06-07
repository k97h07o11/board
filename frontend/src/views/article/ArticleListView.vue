<template>
  <div>
    <b-table :fields="fields" :items="items">
      <template #cell(title)="data">
        <a href="#">
          {{ data.value }}
        </a>
      </template>
    </b-table>
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
        { key: "writer", label: "작성자" },
        { key: "createdDate", label: "작성일" },
        { key: "view", label: "조회" },
      ],
      items: [
        {
          id: 1,
          title: "제목 1",
          writer: "작성자 1",
          createdDate: "2023.06.08.",
          view: "100",
        },
        {
          id: 2,
          title: "제목 2",
          writer: "작성자 2",
          createdDate: "2023.06.09.",
          view: "200",
        },
        {
          id: 3,
          title: "제목 3",
          writer: "작성자 3",
          createdDate: "2023.06.10.",
          view: "300",
        },
      ],
    };
  },
  created() {
    getArticleList()
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  },
};
</script>
