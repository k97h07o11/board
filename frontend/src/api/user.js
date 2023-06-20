import http from "@/api/index";

function join(data) {
  return http.post(`/users`, data);
}

function login(username, password) {
  return http.post(
    `/auth/token`,
    {
      grant_type: "authorization_code",
    },
    {
      auth: {
        username: username,
        password: password,
      },
    }
  );
}

export { join, login };
