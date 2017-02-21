package nyc.c4q.jordansmith.finefree;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import static nyc.c4q.jordansmith.finefree.ActivityMain.SWITCH_KEY;

/**
 * Created by jordansmith on 2/18/17.
 */

public class FragmentSettings extends Fragment {

    View rootView;
    Switch parkingRulesSwitch;
    SharedPreferences sharedPref;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.settings_fragment_layout, container, false);
        parkingRulesSwitch = (Switch) rootView.findViewById(R.id.parking_rules_switch);


        if(getSwitchState()){
            parkingRulesSwitch.setChecked(true);
        }
        else {
            parkingRulesSwitch.setChecked(false);
        }

        parkingRulesSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    if(!getSwitchState()){
                        setSwtichState(true);
                    }
                }
                else{
                    if(getSwitchState()){
                        setSwtichState(false);
                    }
                }
            }
        });

        return rootView;
    }

    public boolean getSwitchState(){
        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getBoolean(SWITCH_KEY, true);
    }

    public void setSwtichState(Boolean state){
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(ActivityMain.SWITCH_KEY, state);
        editor.apply();

    }
}
