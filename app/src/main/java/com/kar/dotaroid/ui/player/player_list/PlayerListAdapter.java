package com.kar.dotaroid.ui.player.player_list;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kar.dotaroid.R;
import com.kar.dotaroid.data.model.PlayerSearchReponse;
import com.kar.dotaroid.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kemal Amru Ramadhan on 12/13/17.
 */

public class PlayerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PlayerSearchReponse> mPlayerList;

    public PlayerListAdapter () {
        mPlayerList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_player_list, parent, false);
        return new PlayerListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PlayerSearchReponse player = mPlayerList.get(position);
        ((PlayerListViewHolder) holder).bind(player);
    }

    @Override
    public int getItemCount() {
        return mPlayerList.size();
    }

    public void addPlayerList(List<PlayerSearchReponse> playerList) {
        mPlayerList.clear();
        mPlayerList.addAll(playerList);
        this.notifyDataSetChanged();
        Log.d("Player List Adapter", "Succes adding data");
    }

    private PlayerListClickListener mClickListener;

    public void setOnItemClickListener(PlayerListClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    interface PlayerListClickListener {
        void onItemClick(View view, int accountId);
    }

    class PlayerListViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvPlayerName;
        private TextView mTvPlayerId;
        private ImageView mIvPlayer;

        public PlayerListViewHolder(View itemView) {
            super(itemView);
            mTvPlayerName = itemView.findViewById(R.id.tv_player_name);
            mTvPlayerId = itemView.findViewById(R.id.tv_player_id);
            mIvPlayer = itemView.findViewById(R.id.iv_player);

            itemView.setOnClickListener(view -> {
                if (mClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mClickListener.onItemClick(itemView, mPlayerList.get(position).getAccountId());
                    }
                }
            });
        }

        public void bind(PlayerSearchReponse player) {
            mTvPlayerName.setText(player.getPersonaname());
            mTvPlayerId.setText("ID: " + String.valueOf(player.getAccountId()));
            ImageUtils.setImageUrl(mIvPlayer, player.getAvatarfull());
        }
    }
}
