package sense.nl.eevee.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sense.nl.eevee.R;
import sense.nl.eevee.adapters.ListAdapter;
import sense.nl.eevee.model.User;
import sense.nl.eevee.model.UserSerialize;
import sense.nl.eevee.service.UserService;
import sense.nl.eevee.utils.RetrofitUtil;

/**
 * Created by budi-ahmad-syidiq on 12/10/16.
 * purpose  : contain data from server
 */
public class ListUser extends Fragment {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle){
        View v = inflater.inflate(R.layout.list_user, viewGroup, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(),2));

        getUsers();
        return v;
    }

    private void doLogin(){
        try {
            UserService service = RetrofitUtil.doIntercepted("https://randomuser.me/").create(UserService.class);
            Call<User> call = service.getUser();
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();

                    Toast.makeText(getActivity(), user.getLogin().getUsername() + " " + user.getLogin().getPassword(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(getActivity(),"Failure "+t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e){
            Toast.makeText(getActivity(),"Failure "+e.getMessage(),Toast.LENGTH_LONG).show();
        }


    }

    // receive data from server with retrofit library
    private void getUsers(){
        swipeRefreshLayout.measure(View.MEASURED_SIZE_MASK, View.MEASURED_HEIGHT_STATE_SHIFT);
        swipeRefreshLayout.setRefreshing(true);
        try {
            UserService service = RetrofitUtil.doIntercepted("https://randomuser.me/").create(UserService.class);
            Call<UserSerialize> call = service.getUsers();
            call.enqueue(new Callback<UserSerialize>() {
                @Override
                public void onResponse(Call<UserSerialize> call, Response<UserSerialize> response) {
                    UserSerialize user = response.body();
                    List<User> data = user.getResults();
                    setAdapter(data);
                }

                @Override
                public void onFailure(Call<UserSerialize> call, Throwable t) {
                    Toast.makeText(getActivity(),"Failure "+t.getMessage(),Toast.LENGTH_LONG).show();
                    swipeRefreshLayout.setRefreshing(false);
                }
            });
        }catch (Exception e){
            Toast.makeText(getActivity(),"Error " + e.getMessage(),Toast.LENGTH_LONG).show();
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void setAdapter(List<User> d){
        recyclerView.setAdapter(new ListAdapter(d, getContext()));
        swipeRefreshLayout.setRefreshing(false);
    }
}
