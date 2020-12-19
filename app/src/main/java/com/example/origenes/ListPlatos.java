package com.example.origenes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.origenes.MenuCategoriasActivity.RESULT;

public class ListPlatos extends AppCompatActivity {
    Categoria categoria=null;
    private RecyclerView ReciclerPlatos;
    private MyAdapter2 mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    TextView titulo,elecciones;
    List<Plato>pedidos;
    Button back;
    Button listar;
    ListMask listMask;
    //id consulta platos.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_platos);
        Bundle recibido = getIntent().getExtras();//extraer bundle
        ReciclerPlatos = findViewById(R.id.recicletPlatos);
        titulo = findViewById(R.id.tituloMenu);
        elecciones = findViewById(R.id.elecciones);
        listar = findViewById(R.id.listar);
        back  = findViewById(R.id.ButtonBack);
        pedidos = new ArrayList<Plato>();
        if(recibido !=null){
            categoria = (Categoria) recibido.getSerializable("categoria");//extraer del bundle
            titulo.setText(categoria.getNombre());
            ReciclerPlatos.setHasFixedSize(false);//el tamaño es dinamico
            layoutManager = new LinearLayoutManager(this.getApplicationContext());
            ReciclerPlatos.setLayoutManager(layoutManager);
            mAdapter = new MyAdapter2 (categoria.platos, this.getApplicationContext(), pedidos);

            mAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"Nombre: "+categoria.platos.get(ReciclerPlatos.getChildAdapterPosition(v)).getNombre(),Toast.LENGTH_SHORT).show();
                }
            });


            ReciclerPlatos.setAdapter(mAdapter);
        }
        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llenar(pedidos);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                listMask = new ListMask(pedidos);
                bundle.putSerializable("categoria", listMask);
                intent.putExtra(RESULT,bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
    void llenar (List<Plato>a){
        String tmp="";
        for (int i = 0 ; i<a.size(); i++){
            tmp +=a.get(i).getNombre();
        }
        elecciones.setText(tmp);
    }
}