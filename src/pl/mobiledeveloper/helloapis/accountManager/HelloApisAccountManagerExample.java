package pl.mobiledeveloper.helloapis.accountManager;

import com.facebook.android.R;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class HelloApisAccountManagerExample extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_manager);

	}

	public void loginButtonOnClick(View v) {
		AccountManager accountManager = AccountManager.get(this);
		Account[] accounts = accountManager.getAccountsByType("com.google");
		final String AUTH_TOKEN_TYPE = "oauth2:https://www.googleapis.com/auth/moderator";
		final int REQUEST_AUTHENTICATE = 0;

		accountManager.getAuthToken(accounts[0], AUTH_TOKEN_TYPE, true,
				new AccountManagerCallback<Bundle>() {

					public void run(AccountManagerFuture<Bundle> future) {
					      try {
					          // If the user has authorized your application to use the tasks API
					          // a token is available.
					          String token = future.getResult().getString(AccountManager.KEY_AUTHTOKEN);
					          // Now you can use the Tasks API...
					          Log.d("token: ",token);
					        } catch (OperationCanceledException e) {
					          // TODO: The user has denied you access to the API, you should handle that
					        } catch (Exception e) {
					        //  handleException(e);
					        }
					}
				}, null);

	}

}