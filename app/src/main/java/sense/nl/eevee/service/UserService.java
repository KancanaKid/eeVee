package sense.nl.eevee.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import sense.nl.eevee.model.User;
import sense.nl.eevee.model.UserSerialize;

/**
 * Created by ahmadmuhsin on 8/2/16.
 */
public interface UserService {
    @GET("api/")
    Call<User> getUser();

    @GET("api?results=10")
    Call<UserSerialize> getUsers();
}
