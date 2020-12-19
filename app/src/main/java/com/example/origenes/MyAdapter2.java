package com.example.origenes;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> implements View.OnClickListener{
    public int prueba=0;
    private List<Plato> mData;
    private List<Plato> pedidos;
    private LayoutInflater mInflater;
    private Context context;
    private View.OnClickListener listener;
    CardView card;
    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    // Proporcione una referencia a las vistas para cada elemento de datos
    // Los elementos de datos complejos pueden necesitar más de una vista por elemento, y
    // proporciona acceso a todas las vistas para un elemento de datos en un titular de vista
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        //Variables ubicadas dentro del card
        int tmp=0;
        TextView name, descripcion, precio, cantidad;
        ImageView add,remove;


        private List<Plato> pedidos;
        // each data item is just a string in this cas
        public MyViewHolder(View v, List<Plato>pedidos) {
            super(v);
            //inicializacion de dichas variebles
            this.pedidos = pedidos;
            name = itemView.findViewById(R.id.platoTitulo);
            descripcion = itemView.findViewById(R.id.platoDescripcion);
            precio = itemView.findViewById(R.id.platoPrecio);
            cantidad = itemView.findViewById(R.id.cantidad);
            add = itemView.findViewById(R.id.add);
            remove = itemView.findViewById(R.id.remove);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tmp++;
                   cantidad.setText("cantidad: "+tmp);
                   pedidos.add(new Plato(name.getText().toString(), Float.parseFloat(precio.getText().toString())));

                }
            });
            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(tmp > 0 )
                        cantidad.setText("cantidad: "+(--tmp));
                }
            });

        }
        void bindData(final Plato item){//
            //seteo de valores de las variables
            //iconImage.setColorFilter(Color.parseColor(item.getArtist()));
            name.setText(item.getNombre());
            descripcion.setText(item.getDescripcion());
            precio.setText(Float.toString(item.getPrecio()));


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter2(List<Plato>myDataset, Context context, List<Plato>pedidos ) {
        this.mData = myDataset;
        this.context = context;
        this.pedidos = pedidos;
        mInflater = LayoutInflater.from(context);


    }
    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter2.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View view = mInflater.inflate(R.layout.elemento_platos, null);//seleccionar el molde del card

        view.setOnClickListener(this);
        return new MyAdapter2.MyViewHolder(view, pedidos);


    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.textView.setText(mDataset[position]);
        holder.bindData(mData.get(position));
    }

    public void setitem(List<Plato>items){
        mData = items;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mData.size();
    }



}