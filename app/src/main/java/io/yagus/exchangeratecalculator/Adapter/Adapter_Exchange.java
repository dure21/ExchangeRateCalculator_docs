package io.yagus.exchangeratecalculator.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import io.yagus.exchangeratecalculator.Model.Model_Exchange_trans;
import io.yagus.exchangeratecalculator.R;

public class Adapter_Exchange extends RecyclerView.Adapter<Adapter_Exchange.ViewHolder> {

    private Model_Exchange_trans modelExchange;
    private ArrayList<Model_Exchange_trans> model_exchange_trans= new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exchange, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        holder.tradingStandardRate.setText(model_exchange_trans.get(i).getTradingStandardRate());
        holder.currencyName.setText(model_exchange_trans.get(i).getCurrencyName());
        holder.sellCash.setText(model_exchange_trans.get(i).getSellCash());
        holder.buyCash.setText(model_exchange_trans.get(i).getBuyCash());

    }

    @Override
    public int getItemCount() {
        return model_exchange_trans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView currencyName, tradingStandardRate, buyCash, sellCash;

        ViewHolder(View itemView) {
            super(itemView);

            currencyName = (TextView) itemView.findViewById(R.id.tv_currencyName);
            tradingStandardRate = (TextView) itemView.findViewById(R.id.tv_tradingStandardRate);
            buyCash = (TextView) itemView.findViewById(R.id.tv_buyCash);
            sellCash = (TextView) itemView.findViewById(R.id.tv_sellCash);

        }
    }
    public void setModelExchanges(ArrayList<Model_Exchange_trans> _model_exchange_trans) {
        model_exchange_trans = _model_exchange_trans;
        notifyDataSetChanged();
    }
}
