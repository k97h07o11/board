import http from "@/api/index";
import store from "@/store/index";

export function getArticleList(pageNum) {
  return http.get(`/articles?page=${pageNum}`);
}

export function getArticle(articleId) {
  return http.get(`/articles/${articleId}`);
}

export function writeArticle(data) {
  return http.post(`/articles`, data, {
    headers: {
      Authorization: `Bearer ${store.state.token}`,
    },
  });
}

export function editArticle(articleId, data) {
  return http.put(`/articles/${articleId}`, data, {
    headers: {
      Authorization: `Bearer ${store.state.token}`,
    },
  });
}

export function deleteArticle(articleId) {
  return http.delete(`/articles/${articleId}`, {
    headers: {
      Authorization: `Bearer ${store.state.token}`,
    },
  });
}
