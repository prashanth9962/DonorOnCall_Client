package com.donor.oncall.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.donor.oncall.R;

/**
 * Created by prashanth on 29/1/16.
 */
public class RequestBloodFragment extends BaseFragment {

    private  View rootView=null;
    private  EditText hospitalField,patientField,purposeField,unitsField,howsoonField;
    private  Spinner bloodGroupField;
    private  String hospital,patient,purpose,howsoon,bloodGrp;
    private  int units;
    private static ProgressDialog progressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.request_blood, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //strict ordering
        setUpViews();
        setUpRequestButton();
        setUpDropDown();
        setupProgressDialog();
    }
    public void setupProgressDialog(){
        progressDialog= new ProgressDialog(RequestBloodFragment.this.getActivity());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Hang on ! Registering your Details with us");
        progressDialog.setCancelable(false);
    }
    public void setUpDropDown(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.blood_group, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroupField.setAdapter(adapter);
    }

    public void setUpViews(){
        hospitalField = (EditText) rootView.findViewById(R.id.hospital);
        patientField = (EditText) rootView.findViewById(R.id.patient);
        purposeField = (EditText) rootView.findViewById(R.id.purpose);
        unitsField = (EditText) rootView.findViewById(R.id.units);
        howsoonField = (EditText) rootView.findViewById(R.id.howsoon);
        bloodGroupField = (Spinner) rootView.findViewById(R.id.bloodgrp);
    }

    public void setFields(){
        hospital=hospitalField.getText().toString();
        patient=patientField.getText().toString();
        purpose=purposeField.getText().toString();
        howsoon=howsoonField.getText().toString();
        bloodGrp =bloodGroupField.getSelectedItem().toString();
    }


    public boolean checkHospital(){
        boolean status = true;
        if (!isNullOrEmpty(hospital)){
            hospitalField.setError("Hospital Name / Doctor Name cannot be empty");
            status = false;
        }
        return status;
    }
    public boolean checkPatient(){
        boolean status = true;
        if (!isNullOrEmpty(patient)){
            patientField.setError("Patient Name cannot be empty");
            status = false;
        }
        return status;
    }
    public boolean checkPurpose(){
        boolean status = true;
        if (!isNullOrEmpty(purpose)){
            purposeField.setError("Hospital Name / Doctor Name cannot be empty");
            status = false;
        }
        return status;
    }

    public boolean checkUnits(){
        boolean status = true;
        String _units = unitsField.getText().toString();
        if (!isNullOrEmpty(_units)){
            unitsField.setError("Units  cannot be empty");
            status = false;
        }else{
            try {
                units=Integer.parseInt(_units);
            }catch (NumberFormatException e){
                status = false;
                unitsField.setError("Units Field cannot con");
            }
        }

        return status;
    }


    public boolean checkHowsoon(){
        boolean status = true;
        if (!isNullOrEmpty(howsoon)){
            howsoonField.setError("Howsoon  cannot be empty");
            status = false;
        }
        return status;
    }


    public boolean validateFields(){
        boolean status = true;

        if (checkHospital()
                && checkPatient()
                && checkPurpose()
                && checkUnits()
                && checkHowsoon()) {

        }else {
            status = false;
        }
        return status;
    }

    public void setUpRequestButton(){
        rootView.findViewById(R.id.requestBlood).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFields();
               if (validateFields()){
                   Log.d("ASDSF","test");
               }
            }
        });
    }
}
