package tbc.uncagedmist.voterservices;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;

import java.util.Locale;

import am.appwise.components.ni.NoInternetDialog;
import tbc.uncagedmist.voterservices.Common.Common;

public class VoterActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    AdView aboveBanner, bottomBanner;
    NoInternetDialog noInternetDialog;

    AppCompatButton btnSearch, btnVoterPortal, btnDownloadRoll, btnKnowBooth,btnTrackApp, btnOfficial, btnApply;
    AppCompatButton btnOverseas, btnDelete, btnEdit, btnTrans, btnMigration, btnReprint,btnResult,btnForms;
    AppCompatButton btnEVoter,btnEKYC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_voter);

        noInternetDialog = new NoInternetDialog.Builder(VoterActivity.this).build();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7920815986886474/1259546558");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        btnSearch = findViewById(R.id.btnSearch);
        btnVoterPortal = findViewById(R.id.btnVoterPortal);

        btnDownloadRoll = findViewById(R.id.btnDownloadRoll);
        btnKnowBooth = findViewById(R.id.btnKnowBooth);
        btnTrackApp = findViewById(R.id.btnTrack);
        btnResult = findViewById(R.id.btnResult);
        btnForms = findViewById(R.id.btnForms);

        btnOfficial = findViewById(R.id.btnOfficial);
        btnApply = findViewById(R.id.btnNew);
        btnOverseas = findViewById(R.id.btnOverseas);
        btnDelete = findViewById(R.id.btnDelete);

        btnEdit = findViewById(R.id.btnEdit);
        btnTrans = findViewById(R.id.btnTrans);
        btnMigration = findViewById(R.id.btnMigration);
        btnReprint = findViewById(R.id.btnReprint);
        btnEVoter = findViewById(R.id.btnEVoter);
        btnEKYC = findViewById(R.id.btnEKYC);

        aboveBanner = findViewById(R.id.aboveBanner);
        bottomBanner = findViewById(R.id.belowBanner);

        AdRequest adRequest = new AdRequest.Builder().build();

        aboveBanner.loadAd(adRequest);
        bottomBanner.loadAd(adRequest);

        onclickImplement();

        adMethod();
    }

    private void adMethod() {
        aboveBanner.setAdListener(new AdListener() {
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
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

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
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        mInterstitialAd.setAdListener(new AdListener() {
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
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
            }
        });
    }

    private void onclickImplement() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(VoterActivity.this,ResultActivity.class);
                    intent.putExtra("url", Common.SEARCH_VOTER);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnVoterPortal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(VoterActivity.this,ResultActivity.class);
                    intent.putExtra("url", Common.VOTER_PORTAL);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnDownloadRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(VoterActivity.this,ResultActivity.class);
                    intent.putExtra("url", Common.DOWNLOAD_ROLL);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnKnowBooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(VoterActivity.this,ResultActivity.class);
                    intent.putExtra("url", Common.KNOW_BOOTH);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnForms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(VoterActivity.this,ResultActivity.class);
                    intent.putExtra("url", Common.FORMS);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(VoterActivity.this,ResultActivity.class);
                    intent.putExtra("url", Common.RESULT);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnTrackApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(VoterActivity.this,ResultActivity.class);
                    intent.putExtra("url", Common.TRACK_APP);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnOfficial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(VoterActivity.this,ResultActivity.class);
                    intent.putExtra("url", Common.OFFICIAL_PAGE);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(VoterActivity.this,ResultActivity.class);
                    intent.putExtra("url", Common.NEW_REGISTER);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnOverseas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(VoterActivity.this,ResultActivity.class);
                    intent.putExtra("url", Common.OVERSEAS_REGISTER);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(VoterActivity.this,ResultActivity.class);
                    intent.putExtra("url", Common.DELETE_ELECTOR);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(VoterActivity.this,ResultActivity.class);
                    intent.putExtra("url", Common.EDIT_ELECTOR);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(VoterActivity.this,ResultActivity.class);
                    intent.putExtra("url", Common.TRANSPOSITION);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnMigration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(VoterActivity.this,ResultActivity.class);
                    intent.putExtra("url", Common.MIGRATION);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnReprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(VoterActivity.this,ResultActivity.class);
                    intent.putExtra("url", Common.REPRINT);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnEVoter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(VoterActivity.this,ResultActivity.class);
                    intent.putExtra("url", Common.E_VOTER);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnEKYC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Intent intent = new Intent(VoterActivity.this,ResultActivity.class);
                    intent.putExtra("url", Common.E_KYC);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void loadLocale()   {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setLocale(language);
    }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noInternetDialog.onDestroy();
    }
}