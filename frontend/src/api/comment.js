import http from "@/api/index";
import store from "@/store/index";

export function getComments(articleId) {
  return http.get(`/articles/${articleId}/comments`);
}

export function writeComment(articleId, data) {
  return http.post(`/articles/${articleId}/comments`, data, {
    headers: {
      Authorization: `Bearer ${store.state.token}`,
    },
  });
}

export function modifyComment(commentId, data) {
  return http.put(`/comments/${commentId}`, data, {
    headers: {
      Authorization: `Bearer ${store.state.token}`,
    },
  });
}

export function deleteComment(commentId) {
  return http.delete(`/comments/${commentId}`, {
    headers: {
      Authorization: `Bearer ${store.state.token}`,
    },
  });
}
