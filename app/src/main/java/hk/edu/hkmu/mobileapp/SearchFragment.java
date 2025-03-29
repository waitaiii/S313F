package hk.edu.hkmu.mobileapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;

public class SearchFragment extends Fragment {
    private RadioGroup rgSearchType;
    private EditText etSearchInput;
    private Button btnSearch;
    private RecyclerView rvResults;
    private boolean isSearchByStop = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initViews(view);
        setupListeners();
        return view;
    }

    private void initViews(View view) {
        rgSearchType = view.findViewById(R.id.rgSearchType);
        etSearchInput = view.findViewById(R.id.etSearchInput);
        btnSearch = view.findViewById(R.id.btnSearch);
        rvResults = view.findViewById(R.id.rvResults);

        rvResults.setLayoutManager(new LinearLayoutManager(getContext()));
        rvResults.setAdapter(new BusRouteAdapter());
    }

    private void setupListeners() {
        rgSearchType.setOnCheckedChangeListener((group, checkedId) -> {
            isSearchByStop = (checkedId == R.id.rbStop);
            etSearchInput.setHint(isSearchByStop
                    ? "Please enter station name"
                    : "Please enter bus number");
        });

        btnSearch.setOnClickListener(v -> performSearch());
    }

    private void performSearch() {
        String searchTerm = etSearchInput.getText().toString().trim();
        if (searchTerm.isEmpty()) {
            Toast.makeText(getContext(), "Please enter a search term", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            try {
                String jsonResponse = ApiClient.getBusRoutes(searchTerm, isSearchByStop);
                requireActivity().runOnUiThread(() -> {
                    rvResults.setVisibility(View.VISIBLE);
                });
            } catch (IOException e) {
                requireActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
            }
        }).start();
    }
}
