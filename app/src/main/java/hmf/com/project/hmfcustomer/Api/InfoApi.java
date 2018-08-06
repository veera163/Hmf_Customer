package hmf.com.project.hmfcustomer.Api;

import hmf.com.project.hmfcustomer.domains.Information;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by home on 6/14/2018.
 */

public interface InfoApi {
    @GET
    Call<Information> getData(@Url String url);
}
