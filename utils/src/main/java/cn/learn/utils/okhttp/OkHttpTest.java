package cn.learn.utils.okhttp;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 常用请求方式 其他详见官网 https://square.github.io/okhttp/recipes/
 *
 * @author shaoyijiong
 * @date 2020/3/4
 */
@SuppressWarnings("all")
public class OkHttpTest {

  OkHttpClient client = new OkHttpClient();
  public static final String URI = "http://127.0.0.1:8080/";

  public static void main(String[] args) throws IOException {
    new OkHttpTest().postJsonExample();
  }


  public void clientBuild() {
    // 通过build创建client 可以设置超时时间
    new OkHttpClient.Builder().build();
  }

  /**
   * get 请求
   */
  public void getExample() throws IOException {
    String api = URI + "api/a";
    // 封装路径
    HttpUrl httpUrl = HttpUrl
        // 解析一个路径 并且封装到 HttpUrl 内部
        .parse(api).newBuilder()
        // 添加一个路径参数
        .addQueryParameter("param1", "a")
        .addQueryParameter("param2", "1").build();

    Request request = new Request.Builder()
        // 请求路径 可以为自带的 HttpUrl 也可以用String
        .url(httpUrl)
        .build();

    try (Response response = client.newCall(request).execute()) {
      // http code码 200
      response.code();
      // http 返回body
      response.body();
      // 返回值json {"code":200,"data":"hello world","msg":"ok"}
      response.body().string();

      // 请求头,返回
      // Content-Type: application/json;charset=UTF-8
      // Transfer-Encoding: chunked
      // Date: Wed, 04 Mar 2020 08:45:09 GMT
      response.headers().toString();

      response.networkResponse();

    }
  }

  /**
   * post 表单请求 1
   */
  public void postFormExample() throws IOException {
    String api = URI + "api/b";
    // 默认请求头 Content-Type:application/x-www-form-urlencoded
    FormBody formBody = new FormBody.Builder().add("param1", "a").add("param2", "1").build();
    Request request = new Request.Builder().url(api).post(formBody).build();
    try (Response response = client.newCall(request).execute()) {
      response.body().string();
    }
  }

  /**
   * post 表单请求 2
   */
  public void postMultiExample() throws IOException {
    String api = URI + "api/b";
    // 默认请求头 Content-Type:multipart/mixed
    MultipartBody formBody = new MultipartBody.Builder().addFormDataPart("param1", "a").addFormDataPart("param2", "1")
        .build();
    Request request = new Request.Builder().url(api).post(formBody).build();
    try (Response response = client.newCall(request).execute()) {
      response.body().string();
    }
  }

  /**
   * post json请求
   */
  public void postJsonExample() throws IOException {
    String api = URI + "api/c";
    MediaType json = MediaType.get("application/json; charset=utf-8");
    String s = JSONObject.toJSONString(Lists.newArrayList("a", "b", "c"));
    RequestBody body = RequestBody.create(json, s);
    Request request = new Request.Builder().url(api).post(body).build();
    try (Response response = client.newCall(request).execute()) {
      response.body().string();
    }
  }
}
