package ss21_design_patterns.ly_thuyet_va_vi_du.creational_patterns.builder.other_example;

import java.util.HashMap;
import java.util.Map;

/*
*   5. Builder Pattern - ⭐⭐⭐⭐⭐
    Tần suất sử dụng: Rất cao (70% dự án với complex objects)

    Builder đặc biệt phổ biến trong:

    StringBuilder/StringBuffer: Java built-in
    SQL Query Builders: Hibernate Criteria, MyBatis
    HTTP Client Builders: OkHttp, Apache HttpClient
    Configuration Objects: Spring Boot @ConfigurationProperties
*
* */

public class HttpRequest {
    private final String url;
    private final String method;
    private final Map<String, String> headers;
    private final String body;
    private final int timeout;

    private HttpRequest(Builder builder) {
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers;
        this.body = builder.body;
        this.timeout = builder.timeout;
    }

    public static class Builder {
        private String url;
        private String method = "GET";
        private Map<String, String> headers = new HashMap<>();
        private String body;
        private int timeout = 30000;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder header(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder timeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public HttpRequest build() {
            if (url == null) throw new IllegalStateException("URL is required");
            return new HttpRequest(this);
        }
    }
}

class Main {
    public static void main(String[] args) {
        HttpRequest request = new HttpRequest.Builder()
                .url("https://api.example.com/users")
                .method("POST")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer token123")
                .body("{\"name\":\"John\",\"email\":\"john@example.com\"}")
                .timeout(10000)
                .build();
    }
}
