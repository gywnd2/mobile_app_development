package com.example.hyojunglee20172979;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.example.hyojunglee20172979.databinding.ActivityMainBinding;

public class MainActivity extends FragmentActivity implements FragmentOne.onClickListener{
    private ActivityMainBinding binding;
    private static final String TAG="StateChange";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        Log.i(TAG, "onCreate");
    }

    @Override
    public void onButtonClick(String text) {
        FragmentTwo fragmentTwo=(FragmentTwo) getSupportFragmentManager().findFragmentById(R.id.frag2);

        fragmentTwo.changeText(text);
    }
}