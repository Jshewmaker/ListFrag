package com.yeti_llc.listfragtest1;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by jshew on 3/21/2018.
 */

public class CharacterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specific_class);

        Intent intent = getIntent();
        int whichClass = intent.getIntExtra("whichClass", -1);
        Fragment specificClass = null;

        if(whichClass == 0) {//Warrior
            specificClass = new WarriorFragment();
        }
        else if(whichClass == 1) { //Mage
            specificClass = new MageFragment();
        }
        else if (whichClass == 2) { //Healer
            specificClass = new HealerFragment();
        }
        else if(whichClass == 3) { //Hunter
            specificClass = new HunterFragment();
        }
        else if(whichClass == 4) { //Paladin
            specificClass = new PaladinFragment();
        }//end else ifs
        if(specificClass != null) {
            //load the fragment
            FragmentManager fragmentManager =
                    getFragmentManager();
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.class_frame,
                    specificClass);
            fragmentTransaction.commit();
        } //end if
    }//end CharacterActivity
}

