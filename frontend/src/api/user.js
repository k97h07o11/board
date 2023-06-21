import http from "@/api/index";

export function join(data) {
  return http.post(`/users`, data);
}

export function login(username, password) {
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
