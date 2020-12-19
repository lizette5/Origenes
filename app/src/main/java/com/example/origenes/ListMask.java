package com.example.origenes;

import java.io.Serializable;
import java.util.List;

public class ListMask  implements Serializable {
    List<Plato>pedidos;

    public ListMask(List<Plato> pedidos) {
        this.pedidos = pedidos;
    }
}
