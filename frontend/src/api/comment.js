import http from "@/api/index";

function getComments(articleId) {
  return http.get(`/articles/${articleId}/comments`);
}

function writeComment(articleId, data) {
  http.defaults.headers.common["Authorization"] = AUTH_TOKEN;
  return http.post(`/articles/${articleId}/comments`, data);
}

function editComment(commentId, data) {
  return http.put(`/comments/${commentId}`, data);
}

function deleteComment(commentId) {
  return http.delete(`/comments/${commentId}`);
}

export { getComments, writeComment, editComment, deleteComment };
