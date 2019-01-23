package com.c4k.emnumbers;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class NumbersFragment extends Fragment  {
    DBHelper dbHelper;
    RecyclerView recyclerView;
    NumberRecycler recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<ENumbersTable> eNumbersTableList = new ArrayList<>();
    SearchView mySearchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.numbers_fragment, null);

        recyclerView = view.findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        String gover_name=getArguments().getString("gover_name","default");
        dbHelper = new DBHelper(getContext());

        eNumbersTableList.clear();
        switch ( gover_name) {
            case "default":
                eNumbersTableList = dbHelper.GetAll().subList(0, 11);
                break;
            case "كركوك":
                eNumbersTableList = getByGover("كركوك");

                break;
            case "بغداد":
                eNumbersTableList = getByGover("بغداد");

                break;
            case "النجف":
                eNumbersTableList = getByGover("النجف");

                break;
            case "اربيل":
                eNumbersTableList = getByGover("اربيل");

                break;
            case "البصرة":
                eNumbersTableList = getByGover("البصرة");

                break;
            case "الموصل":
                eNumbersTableList = getByGover("الموصل");

                break;
            case "كربلاء":
                eNumbersTableList = getByGover("كربلاء");

                break;
            case "صلاح الدين":
                eNumbersTableList = getByGover("صلاح الدين");
                break;
            case "ديالى":
                eNumbersTableList = getByGover("ديالى");
                break;
            case "بابل":
                eNumbersTableList = getByGover("بابل");
                break;
            case "المثنى":
                eNumbersTableList = getByGover("المثنى");
                break;
            case "ميسان":
                eNumbersTableList = getByGover("ميسان");
                break;
            case "واسط":
                eNumbersTableList = getByGover("واسط");
                break;
            case "ذي قار":
                eNumbersTableList = getByGover("ذي قار");
                break;
            case "القادسية":
                eNumbersTableList = getByGover("القادسية");
                break;
            case "دهوك":
                eNumbersTableList = getByGover("دهوك");
                break;
        }

        recyclerAdapter=new NumberRecycler(getContext(),eNumbersTableList);
        recyclerView.setAdapter(recyclerAdapter);

        return view;
    }

    private List<ENumbersTable> getByGover(String goverName) {
        List<ENumbersTable> newList=new ArrayList<>();
        for (ENumbersTable item : dbHelper.GetAll()) {
            if (item.getDescription().contains(goverName)) {
                newList.add(item);
            }
        }
        return newList;
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
                List<ENumbersTable> newList = new ArrayList<>();
                for (ENumbersTable item : eNumbersTableList) {
                    if (item.getDescription().contains(newText)) {
                        newList.add(item);
                    }
                }
                recyclerAdapter = new NumberRecycler(getContext(), newList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}