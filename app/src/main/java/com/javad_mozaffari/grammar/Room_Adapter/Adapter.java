package com.javad_mozaffari.grammar.Room_Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.javad_mozaffari.grammar.R;
import com.javad_mozaffari.grammar.Room_Database.NameModel;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyviewHolder>{

    List<NameModel>nameModels;
    private HandleNames handleNames;

    public Adapter(HandleNames handleNames) {
        this.handleNames=handleNames;
    }


    public void setAdapter(List<NameModel>nameModels){

        this.nameModels = nameModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new MyviewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        NameModel nameModel=nameModels.get(position);
        holder.name.setText(nameModel.getName());
        holder.family.setText(nameModel.getFamily());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleNames.editItem(nameModel);

            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleNames.removeItem(nameModel);
            }
        });



    }


    @Override
    public int getItemCount() {
        return nameModels.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{

        TextView name,family;
        Button btnDelete;


        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            family=itemView.findViewById(R.id.family);
            btnDelete=itemView.findViewById(R.id.btnDelete);
        }
    }

    public interface HandleNames{

        void removeItem(NameModel nameModel);
        void editItem(NameModel nameModel);

    }

}
