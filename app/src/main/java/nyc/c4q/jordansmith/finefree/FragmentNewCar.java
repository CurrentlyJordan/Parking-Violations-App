package nyc.c4q.jordansmith.finefree;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by jordansmith on 2/18/17.
 */

public class FragmentNewCar extends Fragment {
    View rootView;
    EditText newCarLicenseEditText;
    EditText newCarNameEditText;
    Button submitButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.new_car_fragment_layout, container, false);
        newCarLicenseEditText = (EditText) rootView.findViewById(R.id.new_car_licence_edit_text);
        newCarNameEditText = (EditText) rootView.findViewById(R.id.new_car_name_edit_text);
        submitButton = (Button) rootView.findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String savedPlate = newCarLicenseEditText.getText().toString();
                String savedCarName = newCarNameEditText.getText().toString();
                newCarNameEditText.setText("");
                newCarLicenseEditText.setText("");
                Car car = new Car(savedCarName, savedPlate);

                Car.getCarlist().add(car);
                Toast.makeText(v.getContext(),"New Car Added",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(),ActivityMain.class);
                getContext().startActivity(intent);
            }
        });




        return rootView;
    }








}
