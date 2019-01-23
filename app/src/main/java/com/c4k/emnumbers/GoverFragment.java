package com.c4k.emnumbers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class GoverFragment extends Fragment {
    RecyclerView recyclerView;
    GoverRecyclers recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    String[] goverNames;
    SearchView mySearchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.govers_fragment, null);


        recyclerView = view.findViewById(R.id.goverRecyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        goverNames = getResources().getStringArray(R.array.governorateArray);

        recyclerAdapter = new GoverRecyclers(getContext(), goverNames);
        recyclerView.setAdapter(recyclerAdapter);

        getActivity().setTitle("المحافظات");

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.searchItem);
        mySearchView = (SearchView) searchItem.getActionView();

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                String[]newArray=new String[goverNames.length];
                int i=0;
                for (String item : goverNames) {
                    if (item.contains(newText)) {
                        newArray[i]=item;
                        i++;
                    }
                }
                recyclerAdapter = new GoverRecyclers(getContext(), newArray);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}