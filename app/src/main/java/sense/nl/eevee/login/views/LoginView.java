package sense.nl.eevee.login.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sense.nl.eevee.R;

/**
 * Created by ahmadmuhsin on 8/2/16.
 */
public class LoginView extends LinearLayout {

    /*
    TODO : Validate email for email text field
    TODO : Validate number only for pin text field
    TODO : Change login button to appearance to rounded
    TODO : Load new image from web for logo
     */

    public interface OnLoginPageListener {
        void onLoginButtonClicked();
    }

    @BindView(R.id.imv_logo)
    ImageView imvLogo;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_pin)
    EditText edtPin;
    @BindView(R.id.btn_login)
    Button btnLogin;

    public void setOnLoginPageListener(OnLoginPageListener onLoginPageListener) {
        this.onLoginPageListener = onLoginPageListener;
    }

    private OnLoginPageListener onLoginPageListener;

    public LoginView(Context context) {
        super(context);
        init();
    }

    public LoginView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoginView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_login, this, true);
    }

    public boolean isEmailValidate() {
        CharSequence email = getEdtEmail().getText().toString();
        if(email.length() > 0) {
            if(Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean isNumberValidate() {
        String inputs = getEdtPin().getText().toString();
        if(inputs.matches("-?\\d+(.\\d+)?")) {
            return true;
        }else {
            return false;
        }
    }

    public EditText getEdtEmail() {
        return edtEmail;
    }

    public void setEdtEmail(EditText edtEmail) {
        this.edtEmail = edtEmail;
    }

    public EditText getEdtPin() {
        return edtPin;
    }

    public void setEdtPin(EditText edtPin) {
        this.edtPin = edtPin;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        if (onLoginPageListener != null) {
            onLoginPageListener.onLoginButtonClicked();
        }
    }
}
