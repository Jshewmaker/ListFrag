package com.yeti_llc.listfragtest1;

/**
 * Created by jshew on 3/21/2018.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jshew on 3/21/2018.
 */

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

public class MageFragment extends Fragment implements RatingBar.OnRatingBarChangeListener {

    private  RatingBar sBar;
    private  RatingBar iBar;
    private  RatingBar wBar;
    private  RatingBar dBar;
    private float strengthRating = 0;
    private float intellectRating = 0;
    private float wisdomRating = 0;
    private float dexterityRating = 0;
    private float totalPoints = 10;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mage_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        sBar = view.findViewById(R.id.strengthRatingBar);
        iBar = view.findViewById(R.id.intellectRatingBar2);
        wBar = view.findViewById(R.id.wisdomRatingBar3);
        dBar = view.findViewById(R.id.dexterityRatingBar4);

        sBar.setOnRatingBarChangeListener(this);
        iBar.setOnRatingBarChangeListener(this);
        wBar.setOnRatingBarChangeListener(this);
        dBar.setOnRatingBarChangeListener(this);

        prefs = this.getActivity().getSharedPreferences("preferences", Context.MODE_PRIVATE);

        strengthRating = prefs.getFloat("mageStrength", 0);
        sBar.setRating(strengthRating);
        intellectRating = prefs.getFloat("mageIntellect", 0);
        iBar.setRating(intellectRating);
        wisdomRating = prefs.getFloat("mageWisdom", 0);
        wBar.setRating(wisdomRating);
        dexterityRating = prefs.getFloat("mageDexterity", 0);
        dBar.setRating(dexterityRating);
        totalPoints = 10 - (strengthRating + intellectRating + wisdomRating + dexterityRating);

    }


    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

        // Check total points first
        totalPoints = 10 - (strengthRating + intellectRating +
                wisdomRating + dexterityRating);
        // If good on points, proceed
        if(totalPoints >= 0)
        {
            switch (ratingBar.getId())
            {
                /*
                    For each case, check to see if the user is allowed to perform that
                        change. If so, commit it to memory, otherwise, tell the user.
                */
                case R.id.strengthRatingBar:
                    if(sBar.getRating() < strengthRating ||
                            (((totalPoints + strengthRating) - sBar.getRating()) >= 0))
                    {
                        strengthRating = sBar.getRating();
                        editor = prefs.edit();
                        editor.putFloat("mageStrength", sBar.getRating());
                        editor.apply();
                    }
                    else
                    {
                        Toast.makeText(this.getActivity().getApplicationContext(),
                                "You have used all your points.",
                                Toast.LENGTH_SHORT).show();
                    }
                    sBar.setRating(strengthRating);
                    break;
                case R.id.intellectRatingBar2:
                    if(iBar.getRating() < intellectRating ||
                            (((totalPoints + intellectRating) - iBar.getRating()) >= 0))
                    {
                        intellectRating = iBar.getRating();
                        editor = prefs.edit();
                        editor.putFloat("mageIntellect", iBar.getRating());
                        editor.apply();
                    }
                    else
                    {
                        Toast.makeText(this.getActivity().getApplicationContext(),
                                "You have used all your points.",
                                Toast.LENGTH_SHORT).show();
                    }
                    iBar.setRating(intellectRating);
                    break;
                case R.id.wisdomRatingBar3:
                    if(wBar.getRating() < wisdomRating ||
                            (((totalPoints + wisdomRating) - wBar.getRating()) >= 0))
                    {
                        wisdomRating = wBar.getRating();
                        editor = prefs.edit();
                        editor.putFloat("mageWisdom", wBar.getRating());
                        editor.apply();
                    }
                    else
                    {
                        Toast.makeText(this.getActivity().getApplicationContext(),
                                "You have used all your points.",
                                Toast.LENGTH_SHORT).show();
                    }
                    wBar.setRating(wisdomRating);
                    break;
                case R.id.dexterityRatingBar4:
                    if(dBar.getRating() < dexterityRating ||
                            (((totalPoints + dexterityRating) - dBar.getRating()) >= 0))
                    {
                        dexterityRating = dBar.getRating();
                        editor = prefs.edit();
                        editor.putFloat("mageDexterity", dBar.getRating());
                        editor.apply();
                    }
                    else
                    {
                        Toast.makeText(this.getActivity().getApplicationContext(),
                                "You have used all your points.",
                                Toast.LENGTH_SHORT).show();
                    }
                    dBar.setRating(dexterityRating);
                    break;
            }
        }
        // If the user somehow got over 10 points, reset
        else
        {
            Toast.makeText(this.getActivity().getApplicationContext(),
                    "Something went wrong. Resetting the ratings.",
                    Toast.LENGTH_SHORT).show();
            sBar.setRating(0);
            iBar.setRating(0);
            wBar.setRating(0);
            dBar.setRating(0);
            totalPoints = 10;
            editor = prefs.edit();
            editor.putFloat("mageStrength", sBar.getRating());
            editor.putFloat("mageIntellect", iBar.getRating());
            editor.putFloat("mageWisdom", wBar.getRating());
            editor.putFloat("mageDexterity", dBar.getRating());
            editor.apply();

        }
    }
}
