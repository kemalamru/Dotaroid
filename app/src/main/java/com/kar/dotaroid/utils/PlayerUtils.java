package com.kar.dotaroid.utils;

import com.kar.dotaroid.data.model.MmrEstimate;
import com.kar.dotaroid.data.model.Player;
import com.kar.dotaroid.data.model.PlayerMatch;
import com.kar.dotaroid.data.model.PlayerProfileDetail;
import com.kar.dotaroid.data.model.PlayerWinLose;

import java.util.List;

/**
 * Created by Kemal Amru Ramadhan on 12/19/17.
 */

public class PlayerUtils {

    public static PlayerProfileDetail getProfileDetail(Player player, PlayerWinLose playerWinLose, List<PlayerMatch> playerMatchList) {
        return new PlayerProfileDetail(
                player.getProfile().getPersonaname(),
                player.getMmrEstimate().getEstimate(),
                playerWinLose.getWin(),
                playerWinLose.getLose(),
                playerMatchList);
    }
}
