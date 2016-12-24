package github.nisrulz.sample.learnretrofit.data;

import github.nisrulz.sample.learnretrofit.data.remote.RetrofitClient;
import github.nisrulz.sample.learnretrofit.data.remote.SOService;

public class ApiUtils {

  public static final String BASE_URL = "https://api.stackexchange.com/2.2/";

  public static SOService getSOService() {
    return RetrofitClient.getClient(BASE_URL).create(SOService.class);
  }
}
