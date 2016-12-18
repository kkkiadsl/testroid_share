package com.andrew.testroid_share;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button shared;
    private Button page;
    private Spinner city;
    private Spinner level;
    private List targetedShareIntents;
asdjasdpasjd
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shared = (Button)findViewById(R.id.shared);
        city = (Spinner)findViewById(R.id.city);
        level = (Spinner)findViewById(R.id.level);
        page = (Button)findViewById(R.id.page);


        page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent page = new Intent(getApplication(), LoginActivity.class );
                startActivity(page);
            }
        });


        shared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city_text =  city.getSelectedItem().toString();
                String level_text =  level.getSelectedItem().toString();

                String subject = "스탬프 투어";
                String text = "여행자님은 " + city_text + " 여행중입니다.\n" + "현재 등급은 " + level_text + "입니다.\n\n"
                        +"https://play.google.com/store/apps/details?id=com.thatzit.kjw.stamptour_kyj_client";

                targetedShareIntents = new ArrayList<>();

                Intent twitterIntent = getShareIntent("twitter", subject, text);
                if(twitterIntent != null)
                    targetedShareIntents.add(twitterIntent);

                Intent bandIntent = getShareIntent("band", subject, text);
                if(bandIntent != null)
                    targetedShareIntents.add(bandIntent);

                Intent kakaoIntent = getShareIntent("kakao", subject, text);
                if(kakaoIntent != null)
                    targetedShareIntents.add(kakaoIntent);

                Intent facbookIntent = getShareIntent("facebook", subject, text);
                if(facbookIntent != null)
                    targetedShareIntents.add(facbookIntent);


                Intent chooser = Intent.createChooser((Intent) targetedShareIntents.remove(0), "SNS");
                chooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[]{}));
                startActivity(chooser);
            }
        });


    }

    private Intent getShareIntent(String name, String subject, String text){
        boolean found = false;

        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");

        List<ResolveInfo> resInfos = getPackageManager().queryIntentActivities(intent, 0);

        if(resInfos == null || resInfos.size() == 0)
            return null;

        for(ResolveInfo info : resInfos){
            if(info.activityInfo.packageName.toLowerCase().contains(name) ||
                    info.activityInfo.name.toLowerCase().contains(name)){

                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, text);
                intent.setPackage(info.activityInfo.packageName);

                found = true;
                break;
            }
        }

        if(found)
            return intent;

        return null;
    }

}
