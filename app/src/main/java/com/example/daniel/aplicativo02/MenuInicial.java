package com.example.daniel.aplicativo02;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.daniel.aplicativo02.pool.PoolActivity;

public class MenuInicial extends ListActivity {

    private static final String[] atividades = new String[] {
            "swipe 1",
            "swipe 2",
            "swipe 3",
            "sair"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, atividades));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, Swipe01.class));
                break;
            case 1:
                startActivity(new Intent(this, Swipe02.class));
                break;
            case 2:
                startActivity(new Intent(this, PoolActivity.class));
                break;
            default:
                finish();
        }
    }
}
