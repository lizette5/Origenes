package com.example.origenes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MenuCategoriasActivity extends AppCompatActivity {
    List<Categoria> categorias;
    String var;
    int pos;
    private RecyclerView reciclerCategorias;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static final List<Plato>PEDIDOS = new ArrayList<Plato>();
    public static final String  RESULT ="RESULT";
    public  List<Plato>pedidosGeneral = new ArrayList<Plato>();
    public TextView prueba;
    ListMask listMask;
    private static final int REQUES_CODE = 111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_categorias);

        reciclerCategorias = findViewById(R.id.reciclerCategorias);
        prueba = findViewById(R.id.prueba);

        //consulta: nombre y id
        Categoria categoria1 = new Categoria("Entradas");
            categoria1.addPlato(new Plato("Sarsa", (float) 10.00));
            categoria1.addPlato(new Plato("Escribano", (float) 10.00));
        Categoria categoria2 = new Categoria("Segundos");
            categoria2.addPlato(new Plato("Americano", (float)30.00));
            categoria2.addPlato(new Plato("Cuy Chactao", (float)45.00));
            categoria2.addPlato(new Plato("Seco de Cordero", (float)25.00));
        Categoria categoria3 = new Categoria("Bebidas");
            categoria3.addPlato(new Plato("Limonada", (float)15.00));
            categoria3.addPlato(new Plato("Gaseosa XDE", (float)6.00));
            categoria3.addPlato(new Plato("Jugo de Piña", (float)20.00));
        Categoria categoria4 = new Categoria("Postres");
            categoria4.addPlato(new Plato("Queso Helado", (float)10.00));
            categoria4.addPlato(new Plato("Helado de Fresa", (float)8.00));
        categorias = new ArrayList<Categoria>();
        categorias.add(categoria1);
        categorias.add(categoria2);
        categorias.add(categoria3);
        categorias.add(categoria4);


        reciclerCategorias.setHasFixedSize(true);//el tamaño es dinamico
        layoutManager = new LinearLayoutManager(this.getApplicationContext());
        reciclerCategorias.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter (categorias, this.getApplicationContext());



        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Nombre: "+categorias.get(reciclerCategorias.getChildAdapterPosition(v)).getNombre(),Toast.LENGTH_SHORT).show();
                Intent listPlatos = new Intent(MenuCategoriasActivity.this, ListPlatos.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("categoria",categorias.get(reciclerCategorias.getChildAdapterPosition(v)));
                listPlatos.putExtras(bundle);
                //startActivity(listPlatos);
                startActivityForResult(listPlatos,REQUES_CODE);
            }
        });
        reciclerCategorias.setAdapter(mAdapter);


    }
    @Override protected void onSaveInstanceState(Bundle guardarEstado) {
        super.onSaveInstanceState(guardarEstado);
        guardarEstado.putString("variable", var);
        guardarEstado.putInt("posicion", pos);
    }

    @Override protected void onRestoreInstanceState(Bundle recEstado) {
        super.onRestoreInstanceState(recEstado);
        var = recEstado.getString("variable");
        pos = recEstado.getInt("posicion");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUES_CODE){
            if(resultCode == RESULT_OK){
                //Bundle recibido = getIntent().getExtras();
                //categoria = (Categoria) recibido.getSerializable("categoria");//extraer del bundle
                Bundle recibido=data.getBundleExtra(RESULT);
                listMask = (ListMask) recibido.getSerializable("categoria");
                MergeArray((ArrayList<Plato>) listMask.pedidos);
            }
        }
    }

    public void iterarArray(){
        String tmp = "";
        for (int i = 0;i<pedidosGeneral.size() ;i++)
            tmp +=pedidosGeneral.get(i).getNombre()+";";
        prueba.setText(tmp);
    }
    public void MergeArray(ArrayList<Plato>A){
        for(int i = 0; i<A.size();i+=0){
            pedidosGeneral.add(A.remove(i));
        }
        iterarArray();
    }
}