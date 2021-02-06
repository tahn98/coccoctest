package com.example.coccoctest.ui;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.coccoctest.MainActivity;
import com.example.coccoctest.adapter.NewsAdapter;
import com.example.coccoctest.databinding.FragmentHomeBinding;
import com.example.coccoctest.model.RssItem;
import com.example.coccoctest.module.news.INewsView;
import com.example.coccoctest.module.news.NewsPresenter;

import java.util.List;

import static com.example.coccoctest.util.Constant.BUNDLE_URL;

public class HomeFragment extends Fragment implements INewsView {

    private FragmentHomeBinding binding;
    private NewsAdapter newsAdapter;
    private NewsPresenter newsPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        newsPresenter = new NewsPresenter();
        newsPresenter.attachView(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        bindEvents();

        newsPresenter.loadNews();
    }

    public void initView(){
        newsAdapter = new NewsAdapter();
        newsAdapter.setNewsItemClickListener(this::onNavigateToDetailFragment);
        binding.recyclerView.setAdapter(newsAdapter);

        if ((getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES) {
            binding.swDarkTheme.setChecked(true);
        }
    }

    public void bindEvents(){
        binding.swDarkTheme.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if(isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }

    public void onNavigateToDetailFragment(RssItem item){
        Bundle args = new Bundle();
        args.putString(BUNDLE_URL, item.getLink());
        ((MainActivity) getActivity()).changeFragment(DetailFragment.newInstance(args));
    }

    @Override
    public void onDestroy() {
        newsPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onLoadNewsSuccess(List<RssItem> listItem) {
        newsAdapter.setNewsList(listItem);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoading() {

    }
}
