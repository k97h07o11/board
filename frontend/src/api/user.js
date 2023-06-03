import http from "@/api/index";

function join(data) {
  return http.post(`/users/join`, data);
}

function login(data) {
  return http.post(`/users/login`, data);
}

export { join, login };
