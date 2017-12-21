package com.kar.dotaroid.ui.player.player_list;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kar.dotaroid.data.model.PlayerSearchReponse;
import com.kar.dotaroid.databinding.ItemPlayerListBinding;
import com.kar.dotaroid.utils.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kemal Amru Ramadhan on 12/13/17.
 */

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerListViewHolder> {

    private List<PlayerSearchReponse> mPlayerList;
    PlayerListClickListener mClickListener;

    public PlayerListAdapter () {
        mPlayerList = new ArrayList<>();
    }

    @Override
    public PlayerListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemPlayerListBinding binding = ItemPlayerListBinding.inflate(layoutInflater, parent, false);
        return new PlayerListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(PlayerListViewHolder holder, int position) {
        PlayerSearchReponse player = mPlayerList.get(position);
        holder.bind(player);
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

    public void SetOnItemClickListener(PlayerListClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    class PlayerListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemPlayerListBinding mBinding;
        PlayerSearchReponse mPlayer;

        public PlayerListViewHolder(ItemPlayerListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(PlayerSearchReponse player) {
            mPlayer = player;
            mBinding.tvAccountId.setText(mPlayer.getAccountId());
            mBinding.tvAccountName.setText(mPlayer.getPersonaname());
            ImageUtils.setImageUrl(mBinding.imagePlayer, mPlayer.getAvatarfull());
        }

        @Override
        public void onClick(View view) {
            mClickListener.onItemClick(mPlayer.getAccountId());
        }
    }

    interface PlayerListClickListener {
        void onItemClick(Integer accountId);
    }
}

//mUserSearchAdapter.SetOnItemClickListener(new UserSearchAdapter.OnItemClickListener() {
//@Override
//public void onItemClick(View v) {
//        UserInterface.hideSearchKeyboard(mSearchBar);
//
//        int itemPosition = mRecyclerView.getChildLayoutPosition(v);
//        Items items = mGithubUserList.get(itemPosition);
//        String userLogin = items.getLogin();
//
//        Intent myIntent = new Intent(mContext, UserProfile.class);
//        myIntent.putExtra("username",userLogin);
//        mContext.startActivity(myIntent);
//        }
//        });
