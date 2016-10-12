package sense.nl.eevee.login;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sense.nl.eevee.R;
import sense.nl.eevee.fragments.ListUser;
import sense.nl.eevee.login.views.LoginView;
import sense.nl.eevee.model.User;
import sense.nl.eevee.model.UserSerialize;
import sense.nl.eevee.service.UserService;
import sense.nl.eevee.utils.RetrofitUtil;

public class LoginFragment extends Fragment implements LoginView.OnLoginPageListener {

    @BindView(R.id.view_login)
    LoginView viewLogin;
    View view;

    public LoginFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        viewLogin.setOnLoginPageListener(this);

    }

    @Override
    public void onLoginButtonClicked() {
        if(viewLogin.isEmailValidate() && viewLogin.isNumberValidate()) {
            // do login
            setViewLayout();
        } else {
            Snackbar snackbar = Snackbar
                    .make(getView(), "Email or pin not valid", Snackbar.LENGTH_SHORT);

            snackbar.show();
        }
    }

    private void setViewLayout(){

        // change the views
        ViewGroup viewGroup = (ViewGroup) getView();
        viewGroup.removeAllViews();

        ListUser fragment = new ListUser();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }
}
