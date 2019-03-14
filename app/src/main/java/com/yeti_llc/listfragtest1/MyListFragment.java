package com.yeti_llc.listfragtest1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

/**
 * Created by jshew on 3/21/2018.
 */

public class MyListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //get and return the view representing this fragment
        View view = inflater.inflate(R.layout.list_frag, container, false);
        return view;
    }//end onCreateView

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.RPG_Classes,
                android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }//end onActivityCreated

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        View placeholder = getActivity().findViewById(R.id.class_frame);


        if(placeholder == null) {
            Intent intent = new Intent(getActivity(), CharacterActivity.class);
            intent.putExtra("whichClass", i);  //send position int
            startActivity(intent);
        }
        else{
            Fragment specificClass = null;
            int whichClass = i;

            if(whichClass == 0){
                specificClass = new WarriorFragment();
            }
            else if(whichClass == 1){
                specificClass = new MageFragment();
            }
            else if(whichClass == 2){
                specificClass = new HealerFragment();
            }
            else if(whichClass == 3){
                specificClass = new HunterFragment();
            }
            else if(whichClass == 4){
                specificClass = new PaladinFragment();
            }
            if(specificClass != null) {
                FragmentManager fragmentManager =getFragmentManager();
                FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.class_frame,specificClass);
                fragmentTransaction.commit();
            }
        }
    }//end onItemClick
}
