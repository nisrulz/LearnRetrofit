package github.nisrulz.sample.learnretrofit.data.remote;

import github.nisrulz.sample.learnretrofit.data.model.SOAnswersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SOService {

  @GET("/answers?order=desc&sort=activity&site=stackoverflow")
  Call<SOAnswersResponse> getAnswers();

  @GET("/answers?order=desc&sort=activity&site=stackoverflow")
  Call<SOAnswersResponse> getAnswers(@Query("tagged") String tags);
}
