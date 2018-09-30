package com.ioasys.italo.appempresas.RecycleViewEmpresas;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ioasys.italo.appempresas.Activities.DetalhesEmpresaActivity;
import com.ioasys.italo.appempresas.Glide.GlideApp;
import com.ioasys.italo.appempresas.R;
import com.ioasys.italo.appempresas.RetrofitResources.remote.ApiUtils;

import java.util.ArrayList;

public class EmpresaAdapter extends RecyclerView.Adapter<EmpresaAdapter.ViewHolderEmpresa> {

    private ArrayList<Empresa> mEmpresas;
    Context mContext;

    public EmpresaAdapter(ArrayList<Empresa> empresas, Context applicationContext) {
        this.mContext = applicationContext;
        this.mEmpresas = empresas;
    }

    @NonNull
    @Override
    public EmpresaAdapter.ViewHolderEmpresa onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.layout_listitem, parent, false);

        ViewHolderEmpresa holderEmpresa = new ViewHolderEmpresa(view);

        return holderEmpresa;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEmpresa holder, int position) {

        Empresa empresa = mEmpresas.get(position);
        holder.empresaNome.setText(empresa.getmNomeEmpresa());
        holder.localidade.setText(empresa.getmLocalidade());
        holder.negocio.setText(empresa.getmNegocio());

        if (empresa.getmUrlImage() == null) return;

        GlideApp.with(mContext)
                .load(ApiUtils.BASE_IMAGE + empresa.getmUrlImage())
                .into(holder.empresaImagem);

    }

    @Override
    public int getItemCount() {
        return mEmpresas != null ? mEmpresas.size() : 0;
    }

    public class ViewHolderEmpresa extends RecyclerView.ViewHolder {
        ImageView empresaImagem;
        TextView empresaNome;
        TextView negocio;
        TextView localidade;

        public ViewHolderEmpresa(View itemView) {
            super(itemView);
            empresaImagem = itemView.findViewById(R.id.listItem_empresa_imageView);
            empresaNome = itemView.findViewById(R.id.listItem_nomeEmpresa_textView);
            negocio = itemView.findViewById(R.id.listItem_negocio_textView);
            localidade = itemView.findViewById(R.id.listItem_localidade_textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (mEmpresas.size() <= 0) {
                        return;
                    }

                    Empresa empresa = mEmpresas.get(getLayoutPosition());
                    Intent intent = new Intent(mContext, DetalhesEmpresaActivity.class);
                    //intent.putExtra("",""); IMAGEM
                    intent.putExtra("enterprise_name", empresa.getmNomeEmpresa());
                    intent.putExtra("description", empresa.getmDescription());
                    mContext.startActivity(intent);

                }
            });
        }
    }
}
