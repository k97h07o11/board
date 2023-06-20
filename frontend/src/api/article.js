import http from "@/api/index";
import store from "@/store/index";

function getArticleList(pageNum) {
  return http.get(`/articles?page=${pageNum}`);
}

function getArticle(articleId) {
  return http.get(`/articles/${articleId}`);
}

function writeArticle(data) {
  return http.post(`/articles`, data, {
    headers: {
      Authorization: `Bearer ${store.state.token}`,
    },
  });
}

function editArticle(articleId, data) {
  return http.put(`/articles/${articleId}`, data, {
    headers: {
      Authorization: `Bearer ${store.state.token}`,
    },
  });
}

function deleteArticle(articleId) {
  return http.delete(`/articles/${articleId}`, {
    headers: {
      Authorization: `Bearer ${store.state.token}`,
    },
  });
}

export { getArticleList, getArticle, writeArticle, editArticle, deleteArticle };
