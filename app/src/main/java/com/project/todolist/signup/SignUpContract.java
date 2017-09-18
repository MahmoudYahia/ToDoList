package com.project.todolist.signup;


/**
 * Created by mah_y on 9/14/2017.
 */

public interface SignUpContract {

    interface SignupView {

        void navigateToSignInActivity();

        void showSignUpFailedMessage();

        void ResetConfirmPass();

        void showNotValidPassword();
        void showEmptyFieldsMessage();
    }

    interface Presenter {
        void onRegisterButtonClicked(String email, String pass, String confirmPass);
    }

}
