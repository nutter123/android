package com.example.nutter.dongruancp.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nutter.dongruancp.activity.MainActivity;
import com.example.nutter.dongruancp.R;
import com.example.nutter.dongruancp.adapter.SearchAdapter;
import com.example.nutter.dongruancp.base.BaseFragment;
import com.example.nutter.dongruancp.bean.food;
import com.example.nutter.dongruancp.interf.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class Shop_searchFragment extends BaseFragment {


    public RecyclerView recyclerView;
    public SearchAdapter adapter;
    //new
    private ImageButton clearSearch;
    private EditText query;
    private TextView searchView;
    private ProgressDialog pd;
    private Button sousuoView;

    public Shop_searchFragment() {
        // Required empty public constructor
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        query = (EditText) view.findViewById(R.id.editText_search);
        // clear button
        TextView emptyView = (TextView) view.findViewById(R.id.tv_no_result);
        emptyView.setVisibility(View.INVISIBLE);
        sousuoView = (Button) view.findViewById(R.id.tv_sousuo);
        searchView = (TextView) view.findViewById(R.id.tv_search);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_View_search);
        clearSearch= (ImageButton) view.findViewById(R.id.imageButton_search);

        query.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    clearSearch.setVisibility(View.VISIBLE);
                } else {
                    clearSearch.setVisibility(View.INVISIBLE);
                }
                searchView.setVisibility(View.VISIBLE);
            }
        });
        clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query.getText().clear();
                searchView.setText("");
            }
        });
        sousuoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //请求数据->显示数据 retrofit
                String search=query.getText().toString();
                Retrofit retrofit=new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("http://60.205.189.39/")
                        .build();

                RetrofitService service=
                        ((retrofit2.Retrofit) retrofit).create(RetrofitService.class);
                Call<List<food>> call=service.getFoodBySearch(search);

                call.enqueue(new Callback<List<food>>() {

                    @Override
                    public void onResponse(Call<List<food>> arg0, Response<List<food>>arg1) {
                        // TODO Auto-generated method stub
                        if(getActivity()!=null) {
                            List<food> lista = arg1.body();
                            MainActivity mainActivity = (MainActivity) getActivity();
                            adapter = new SearchAdapter(mainActivity, lista);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(mainActivity);
                            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());

                        }
                    }

                    @Override
                    public void onFailure(Call<List<food>> arg0, Throwable arg1) {
                        // TODO Auto-generated method stub

                        //Toast

                    }
                });
            }
        });

    }

    private void searchMessages(){
        MainActivity mainActivity=(MainActivity)getActivity();
        pd = new ProgressDialog(mainActivity);
        pd.setMessage("正在搜索");
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        new Thread(new Runnable() {
            public void run() {

            }
        }).start();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop_search, container, false);
    }

}
