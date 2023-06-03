import http from "@/api/index";

function getArticleList() {
  return http.get(`/articles`);
}

function getArticle(articleId) {
  return http.get(`/articles/${articleId}`);
}

function writeArticle(data) {
  http.defaults.headers.common["Authorization"] = AUTH_TOKEN;
  return http.post(`/articles`, data);
}

function editArticle(articleId, data) {
  return http.put(`/articles/${articleId}`, data);
}

function deleteArticle(articleId) {
  return http.delete(`/articles/${articleId}`);
}

export { getArticleList, getArticle, writeArticle, editArticle, deleteArticle };
