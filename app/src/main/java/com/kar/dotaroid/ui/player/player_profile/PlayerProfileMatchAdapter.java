package com.kar.dotaroid.ui.player.player_profile;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kar.dotaroid.R;
import com.kar.dotaroid.data.model.PlayerMatch;
import com.kar.dotaroid.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kemal Amru Ramadhan on 12/20/17.
 */

public class PlayerProfileMatchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<PlayerMatch> mMatchList;

    public PlayerProfileMatchAdapter () {
        mMatchList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_match_list, parent, false);

        return new PlayerMatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PlayerMatch match = mMatchList.get(position);
        ((PlayerMatchViewHolder) holder).bind(match);
    }

    @Override
    public int getItemCount() {
        return mMatchList.size();
    }

    public void addMatchList(List<PlayerMatch> matchList) {
        if (matchList.size() > 4) {
            mMatchList.addAll(new ArrayList<>(matchList.subList(0, 5)));
        } else {
            mMatchList.addAll(matchList);
        }

        this.notifyDataSetChanged();
        Log.d("Player Match Adapter", "Succes adding data");
    }

    class PlayerMatchViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvMatchHero;
        private TextView mTvMatchResult;
        private TextView mTvMatchKill;
        private TextView mTvMatchDeath;
        private TextView mTvMatchAssist;

        public PlayerMatchViewHolder(View itemView) {
            super(itemView);

            mIvMatchHero = itemView.findViewById(R.id.iv_match_hero);
            mTvMatchResult = itemView.findViewById(R.id.tv_match_result);
            mTvMatchKill = itemView.findViewById(R.id.tv_match_kill);
            mTvMatchDeath = itemView.findViewById(R.id.tv_match_death);
            mTvMatchAssist = itemView.findViewById(R.id.tv_match_assist);
        }

        public void bind(PlayerMatch match) {
            int playerSlot = match.getPlayerSlot();
            boolean RadiantWin = match.getRadiantWin();
            String matchResult = "Lose";
            if (playerSlot < 100 && RadiantWin) matchResult = "Win";
            if (playerSlot > 100 && !RadiantWin) matchResult = "Win";

            ImageUtils.setImageHeroSmall(mIvMatchHero, match.getHeroId());
            mTvMatchResult.setText(matchResult);
            mTvMatchKill.setText(String.valueOf(match.getKills()));
            mTvMatchDeath.setText(String.valueOf(match.getDeaths()));
            mTvMatchAssist.setText(String.valueOf(match.getAssists()));
        }
    }
}
