package com.example.macbookair.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.inputmethodservice.Keyboard;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageButton searchButton;
    private EditText searchBox;
    private RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        searchButton = findViewById(R.id.imgButton);
        searchBox = findViewById(R.id.searchBox);

        searchBox.setVisibility(View.INVISIBLE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerAdapter(this, getContactList()));

        search();
        f();
    }

    private List<Contact> getContactList() {

        List<Contact> list = new ArrayList<>();
        list.add(new Contact("Martin", SocialNetworks.FACEBOOK));
        list.add(new Contact("Vaxo", SocialNetworks.TWITTER));
        list.add(new Contact("Ashot", SocialNetworks.FACEBOOK));
        list.add(new Contact("Armen", SocialNetworks.TWITTER));
        list.add(new Contact("Bob", SocialNetworks.TWITTER));
        list.add(new Contact("Yana Xadavik", SocialNetworks.FACEBOOK));
        list.add(new Contact("Anna", SocialNetworks.FACEBOOK));
        list.add(new Contact("Artur", SocialNetworks.FACEBOOK));
        list.add(new Contact("Ben", SocialNetworks.TWITTER));
        list.add(new Contact("Mike", SocialNetworks.TWITTER));
        return list;
    }


    private void search() {

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String query = searchBox.getText().toString().toLowerCase();

                final List<Contact> filteredList = new ArrayList<>();

                for (int i = 0; i < getContactList().size(); i++) {

                    final String text = getContactList().get(i).getName().toLowerCase();

                    if (text.contains(query))
                        filteredList.add(getContactList().get(i));
                }

                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                mAdapter = new RecyclerAdapter(MainActivity.this, filteredList);
                recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void f() {

        searchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    searchBox.setVisibility(View.INVISIBLE);
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });
    }

    public void setVisible(View v) {
        searchBox.setVisibility(View.VISIBLE);
    }
}
