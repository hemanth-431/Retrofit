package com.example.retro;

import androidx.appcompat.app.AppCompatActivity;


        import android.os.Bundle;
        import android.widget.TextView;

        import com.google.gson.Gson;
        import com.google.gson.GsonBuilder;

        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;

    private JsonPlaceHolderApi jsonPlaceHolderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        Gson gson = new GsonBuilder().serializeNulls().create();

        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://localhost:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

     //   getPosts();
        //getComments();
    //    createPost();
        updatePost();
      //  deletePost();
    }

    private void getPosts() {
        Call<List<Post>> call=jsonPlaceHolderApi.getPosts();//(4)
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful())
                {
                   textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Post> posts=response.body();
                for (Post post:posts) {
                    String content = "";
                    //     if (post.getId() == 1) {
                    content += "id: " + post.getId()+ "\n";
                    content += "Pin_Code: " + post.getUserId() + "\n";
                    content += "product_name: " + post.getTitle() + "\n";
                    content += "product_brand: " + post.getText() + "\n";
                    content += "street_name: " + post.getAvatar() + "\n";
                    content += "category: " + post.getName1() + "\n";
                    content += "shop_details: " + post.getName2() + "\n";
                    content += "shop_name: " + post.getName3() + "\n" + "\n";

                    textViewResult.append(content);
                    //   }
                }
            }



            /*  Map<String, String> parameters = new HashMap<>();
                   parameters.put("userId", "1");
                    parameters.put("product_name", "id");
                    parameters.put("product_brand", "desc");

                    Call<List<Post>> call = jsonPlaceHolderApi.getPosts(parameters);

                    call.enqueue(new Callback<List<Post>>() {
                        @Override
                        public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                            if (!response.isSuccessful()) {
                                textViewResult.setText("Code: " + response.code());
                                return;
                            }

                            List<Post> posts = response.body();

                            for (Post post : posts) {
                                String content = "";
                                content += "ID: " + post.getId() + "\n";
                                content += "User ID: " + post.getUserId() + "\n";
                                content += "Title: " + post.getTitle() + "\n";
                                content += "Text: " + post.getText() + "\n\n";

                                textViewResult.append(content);
                            }
                        }
            */
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getComments() {
        Call<List<Comment>> call = jsonPlaceHolderApi
                .getComments("https://jsonplaceholder.typicode.com/posts/3/comments");

        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Comment> comments = response.body();

                for (Comment post : comments) {
                    String content = "";
                    content += "id: " + post.getId()+ "\n";
                    content += "Pin_Code: " + post.getUserId() + "\n";
                    content += "product_name: " + post.getTitle() + "\n";
                    content += "product_brand: " + post.getText() + "\n";
                    content += "street_name: " + post.getAvatar() + "\n";
                    content += "category: " + post.getName1() + "\n";
                    content += "shop_details: " + post.getName2() + "\n";
                    content += "shop_name: " + post.getName3() + "\n" + "\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void createPost() {
      //  Post post = new Post("vnng","jmrng", "New", "New");

        Map<String, String> fields = new HashMap<>();
        fields.put("category", "12");
        fields.put("product_name","jj");
        fields.put("product_brand","New");
        fields.put("shop_name", "Newkjrkvj");
        fields.put("street_name", "Newkjrkvj");
        fields.put("pin_code", "Newkjrkvj");
        fields.put("shop_details", "Newkjrkvj");
        Call<Post> call = jsonPlaceHolderApi.createPost(fields);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResponse.getId() + "\n";
                content += "email: " + postResponse.getAvatar() + "\n";
                content += "firstname: " +postResponse.getUserId() + "\n";
                content += "lastname: " + postResponse.getTitle() + "\n";
                content += "avatar: " + postResponse.getText() + "\n\n";

                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void updatePost() {
        Post post = new Post( "jnvjnkb", "New Text","knkfb","fuuf","rocks","ncmjnvnb","kvnkdv");

        Call<Post> call = jsonPlaceHolderApi.putPost(12,post);

        call.enqueue(new Callback<com.example.retro.Post>() {
            @Override
            public void onResponse(Call<com.example.retro.Post> call, Response<com.example.retro.Post> response) {

                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                Post postResponse = response.body();

                String content = "";
                content += "id: " + postResponse.getId()+ "\n";
                content += "Pin_Code: " + postResponse.getUserId() + "\n";
                content += "product_name: " + postResponse.getTitle() + "\n";
                content += "product_brand: " + postResponse.getText() + "\n";
                content += "street_name: " + postResponse.getAvatar() + "\n";
                content += "category: " + postResponse.getName1() + "\n";
                content += "shop_details: " + postResponse.getName2() + "\n";
                content += "shop_name: " + postResponse.getName3() + "\n" + "\n";

                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void deletePost() {
        Call<Void> call = jsonPlaceHolderApi.deletePost(5);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textViewResult.setText("Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }


}