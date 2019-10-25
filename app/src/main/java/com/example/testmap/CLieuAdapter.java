package com.example.testmap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import javax.sql.StatementEvent;

public class CLieuAdapter extends ArrayAdapter<CLieu> {
    public CLieuAdapter(Context context, ArrayList<CLieu> Lieu) {
        super(context, 0, Lieu);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CLieu lieu = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_lieu, parent, false);

        TextView tvNom = (TextView) convertView.findViewById(R.id.tvNomLieu);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.tvDescriptionPreuve);

        tvNom.setText(lieu.getM_nom());
        tvDescription.setText(lieu.getM_preuve().getM_description());

        return convertView;
    }
}
