package com.example.hyojunglee20172979;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.hyojunglee20172979.databinding.FragmentOneBinding;

public class FragmentOne extends Fragment {
    private FragmentOneBinding binding;
    onClickListener activityCallback;

    public interface onClickListener{
        public void onButtonClick(String text);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentOneBinding.inflate(inflater, container, false);
        binding.frag1ButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonClicked(v);
            }
        });

        return binding.getRoot();
    }

    public void buttonClicked(View view){
        activityCallback.onButtonClick(binding.frag1Text.getText().toString());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            activityCallback=(onClickListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString()+"must Implement OnClickListener");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding=null;
    }
}