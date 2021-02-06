package com.example.coccoctest.ui;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coccoctest.MainActivity;
import com.example.coccoctest.databinding.FragmentDetailBinding;

import static com.example.coccoctest.util.Constant.BUNDLE_URL;

public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private String url;

    public static DetailFragment newInstance(Bundle bundle) {
        DetailFragment fragment = new DetailFragment();
        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(BUNDLE_URL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void initWebView() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            if (((MainActivity) getActivity()).checkNightMode()) {
                binding.wvExpress.getSettings().setForceDark(WebSettings.FORCE_DARK_ON);
            } else {
                binding.wvExpress.getSettings().setForceDark(WebSettings.FORCE_DARK_OFF);
            }
        }

        binding.wvExpress.loadUrl(url);
        binding.wvExpress.getSettings().setJavaScriptEnabled(true);
    }
}
