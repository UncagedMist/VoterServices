package tbc.uncagedmist.voterservices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 5152;
    AppCompatButton btnVoter,btnLang;

    AdView bottomBanner,aboveBanner;

    private InterstitialAd mInterstitialAd;

    ReviewManager manager;
    ReviewInfo reviewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkAppUpdate();
        loadLocale();
        setContentView(R.layout.activity_main);

        manager = ReviewManagerFactory.create(MainActivity.this);

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(
                MainActivity.this,
                "ca-app-pub-7920815986886474/1259546558",
                adRequest, new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Log.d("TAG", "The ad was dismissed.");
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                Log.d("TAG", "The ad failed to show.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                mInterstitialAd = null;
                                Log.d("TAG", "The ad was shown.");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        btnVoter = findViewById(R.id.btnVoter);
        btnLang = findViewById(R.id.btnChange);

        aboveBanner =findViewById(R.id.aboveBanner);
        bottomBanner = findViewById(R.id.belowBanner);

        aboveBanner.loadAd(adRequest);
        bottomBanner.loadAd(adRequest);

        btnVoter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(MainActivity.this);
                }
                else {
                    startActivity(new Intent(MainActivity.this,VoterActivity.class));
                }
            }
        });


        btnLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAppLang();
            }
        });

        adMethod();
    }

    private void checkAppUpdate() {
        final AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(MainActivity.this);
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        appUpdateInfoTask.addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo result) {

                if (result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                        result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE))    {

                    try {
                        appUpdateManager.startUpdateFlowForResult(result,AppUpdateType.IMMEDIATE,MainActivity.this,REQUEST_CODE);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void adMethod() {
        bottomBanner.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });
    }

    private void selectAppLang() {

        LayoutInflater inflater = this.getLayoutInflater();
        View language_layout = inflater.inflate(R.layout.lang_layout,null);

        final AppCompatButton btnEnglish = language_layout.findViewById(R.id.btnEnglish);
        final AppCompatButton btnHindi = language_layout.findViewById(R.id.btnHindi);
        final AppCompatButton btnUrdu = language_layout.findViewById(R.id.btnUrdu);
        final AppCompatButton btnBangla = language_layout.findViewById(R.id.btnBangla);
        final AppCompatButton btnPunjabi = language_layout.findViewById(R.id.btnPunjabi);
        final AppCompatButton btnKannada = language_layout.findViewById(R.id.btnKannada);

        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);
        dialogBuilder
                .withTitle("Choose Language / भाषा चुनें")
                .withTitleColor("#FFFFFF")
                .withMessage("Select Language...")
                .withDialogColor("#FFFFFF")
                .withDialogColor("#2ea4e7")
                .withEffect(Effectstype.Newspager)
                .isCancelableOnTouchOutside(false)
                .setCustomView(language_layout,this)
                .show();

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("en");
                recreate();
            }
        });

        btnHindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("hi");
                recreate();
            }
        });

        btnUrdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("ur");
                recreate();
            }
        });

        btnBangla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("bn");
                recreate();
            }
        });

        btnPunjabi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("pa");
                recreate();
            }
        });

        btnKannada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("kn");
                recreate();
            }
        });
    }

    //set language
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

        //shared prefs
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }

    //load language
    private void loadLocale()   {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setLocale(language);
    }

    @Override
    public void onBackPressed() {
        Task<ReviewInfo> request = manager.requestReviewFlow();

        request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(@NonNull Task<ReviewInfo> task) {
                if (task.isSuccessful())    {
                    reviewInfo = task.getResult();

                    Task<Void> flow = manager.launchReviewFlow(MainActivity.this,reviewInfo);

                    flow.addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void result) {

                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "ERROR...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}