package github.nisrulz.sample.learnretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import github.nisrulz.sample.learnretrofit.data.ApiUtils;
import github.nisrulz.sample.learnretrofit.data.model.Item;
import github.nisrulz.sample.learnretrofit.data.model.SOAnswersResponse;
import github.nisrulz.sample.learnretrofit.data.remote.SOService;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";
  private AnswersAdapter answersAdapter;
  private RecyclerView recyclerView;
  private SOService soService;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    soService = ApiUtils.getSOService();

    recyclerView = (RecyclerView) findViewById(R.id.rv_answers);

    answersAdapter =
        new AnswersAdapter(this, new ArrayList<Item>(0), new AnswersAdapter.PostItemListener() {

          @Override
          public void onPostClick(long id) {
            Toast.makeText(MainActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();
          }
        });

    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(answersAdapter);
    recyclerView.setHasFixedSize(true);
    RecyclerView.ItemDecoration itemDecoration =
        new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
    recyclerView.addItemDecoration(itemDecoration);

    loadAnswers();
  }

  private void loadAnswers() {

    soService.getAnswers().enqueue(new Callback<SOAnswersResponse>() {
      @Override
      public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {
        if (response.isSuccessful()) {
          answersAdapter.updateAnswers(response.body().getItems());
        }
        else {
          int statuscode = response.code();
          Log.d(TAG, "onResponse: Status >" + statuscode);
        }
      }

      @Override
      public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
      }
    });
  }
}
