package com.pickapp.ui.auth;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.PagerAdapter;

import com.pickapp.R;
import com.pickapp.databinding.FragmentAuthGetStartedBinding;
import com.pickapp.databinding.ItemGetStartedBinding;
import com.pickapp.ui.model.GetStarted;

import java.util.ArrayList;
import java.util.List;

public class GetStartedFragment extends Fragment {

    private FragmentAuthGetStartedBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAuthGetStartedBinding.inflate(inflater, container, false);

        Navigation.findNavController(getActivity(), R.id.auth_host_fragment)
                .getGraph().setStartDestination(R.id.auth_get_started);

        GetStartedAdapter getStartedAdapter = new GetStartedAdapter(getContext());
        binding.pager.setAdapter(getStartedAdapter);
        binding.indicator.setupWithViewPager(binding.pager);

        binding.btnGetStarted.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.auth_host_fragment)
                    .navigate(R.id.auth_login);
        });

        return binding.getRoot();
    }

    public class GetStartedAdapter extends PagerAdapter {

        private Context mContext;
        private LayoutInflater mLayoutInflater;
        private List<GetStarted> getStartedList = new ArrayList<>();

        public GetStartedAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            getStartedList.add(new GetStarted(
                    "ONLINE SHOPPING",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam."
            ));
            getStartedList.add(new GetStarted(
                    "ADD TO CART",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam"
            ));
            getStartedList.add(new GetStarted(
                    "PAYMENT SUCCESSFUL",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam"
            ));
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ItemGetStartedBinding binding = ItemGetStartedBinding.inflate(mLayoutInflater, container, false);
            binding.title.setText(getStartedList.get(position).getTitle());
            binding.description.setText(getStartedList.get(position).getDescription());
            container.addView(binding.getRoot());
            return binding.getRoot();
        }

        @Override
        public int getCount() {
            return getStartedList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((LinearLayout) object);
        }
    }

}
