package com.halo.loginui2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import model.listaservico;

public class ListaServAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<listaservico> listaServ;

    Context context;

    public ListaServAdapter(Context context, List<listaservico> listaServ) {
        super();
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.listaServ = listaServ;
    }

    @Override
    public int getCount() {
        return listaServ.size();
    }

    @Override
    public Object getItem(int position) {
        return listaServ.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaServ.get(position).getId_servico();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemSuporte itemHolder;

        // se a view estiver nula (nunca criada), adicionamos o layout nela.
        if (convertView == null) {

            // adiciona o layout para podermos pegar as views
            convertView = mInflater.inflate(R.layout.activity_item_servico, null);

            // cria um item de suporte para n�o precisarmos sempre
            // adicionar as mesmas informacoes
            itemHolder = new ItemSuporte();
            itemHolder.lblTitulo = ((TextView) convertView.findViewById(R.id.lblTitulo));
            itemHolder.lblDesc = ((TextView) convertView.findViewById(R.id.lblDesc));

            // define os itens na view;
            convertView.setTag(itemHolder);
        } else {
            // se a view j� existe pega os itens.
            itemHolder = (ItemSuporte) convertView.getTag();
        }

        // pega os dados da lista
        listaservico serv = listaServ.get(position);

        // adiciona ao TEXTVIEW o nome do item
        itemHolder.lblTitulo.setText(serv.getTitulo());

        itemHolder.lblDesc.setText(serv.getDescricao());


        // retorna a view com as informa��es
        return convertView;
    }

    private class ItemSuporte {


        TextView lblTitulo,lblDesc;

    }
}
