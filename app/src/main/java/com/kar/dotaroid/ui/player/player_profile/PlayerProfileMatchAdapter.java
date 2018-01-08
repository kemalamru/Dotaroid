package com.kar.dotaroid.ui.player.player_profile;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kar.dotaroid.data.model.PlayerMatch;
import com.kar.dotaroid.databinding.ItemMatchListBinding;
import com.kar.dotaroid.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kemal Amru Ramadhan on 12/20/17.
 */

public class PlayerProfileMatchAdapter extends RecyclerView.Adapter<PlayerProfileMatchAdapter.PlayerMatchViewHolder>  {

    private List<PlayerMatch> mMatchList;

    public PlayerProfileMatchAdapter () {
        mMatchList = new ArrayList<>();
    }

    @Override
    public PlayerProfileMatchAdapter.PlayerMatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMatchListBinding binding = ItemMatchListBinding.inflate(layoutInflater, parent, false);
        return new PlayerProfileMatchAdapter.PlayerMatchViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PlayerProfileMatchAdapter.PlayerMatchViewHolder holder, int position) {
        PlayerMatch match = mMatchList.get(position);
        holder.bind(match);
    }

    @Override
    public int getItemCount() {
        return mMatchList.size();
    }

    public void addMatchList(List<PlayerMatch> matchList) {
        mMatchList.clear();
        if (matchList.size() > 4) {
            mMatchList.addAll(new ArrayList<>(matchList.subList(0, 5)));
        } else {
            mMatchList.addAll(matchList);
        }

        this.notifyDataSetChanged();
        Log.d("Player Match Adapter", "Succes adding data");
    }

    class PlayerMatchViewHolder extends RecyclerView.ViewHolder {

        private ItemMatchListBinding mBinding;

        public PlayerMatchViewHolder(ItemMatchListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(PlayerMatch match) {
            int playerSlot = match.getPlayerSlot();
            boolean RadiantWin = match.getRadiantWin();
            String matchResult = "Lose";
            if (playerSlot < 100 && RadiantWin) matchResult = "Win";
            if (playerSlot > 100 && !RadiantWin) matchResult = "Win";

            ImageUtils.setImageHeroSmall(mBinding.imageHero, match.getHeroId());
            mBinding.tvResult.setText(matchResult);
            mBinding.tvKill.setText(String.valueOf(match.getKills()));
            mBinding.tvDeath.setText(String.valueOf(match.getDeaths()));
            mBinding.tvAssist.setText(String.valueOf(match.getAssists()));
        }
    }
}
