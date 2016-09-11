/*
 * Copyright (c) 2016. Ivan Stuart
 *  All Rights Reserved
 */

package com.ivstuart.tmud.world;

import com.ivstuart.tmud.person.Player;
import com.ivstuart.tmud.state.Room;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 11/09/2016.
 */
public class Clan implements Serializable {

    private int clanId;
    private String name;
    private String leader;
    private boolean alignment;

    private String donateRoom;
    private String homeRoom;
    private String graveRoom;
    private List<String> applicants;
    private List<String> chat;
    private List<String> members;

    public List<String> getMembers() {
        return members;
    }

    public void addMembers(String members) {
        if (this.members == null) {
            this.members = new ArrayList<>();
        }
        this.members.add(members);
    }

    public boolean isAlignment() {
        return alignment;
    }

    public void setAlignment(boolean alignment) {
        this.alignment = alignment;
    }

    public List<String> getChat() {
        if (chat == null) {
            chat = new ArrayList<>();
        }
        return chat;
    }

    public void setChat(List<String> chat) {
        this.chat = chat;
    }

    public List<String> getApplicants() {
        return applicants;
    }

    public String getHomeRoom() {
        return homeRoom;
    }

    public void setHomeRoom(String homeRoom) {
        this.homeRoom = homeRoom;
    }

    public String getGraveRoom() {
        return graveRoom;
    }

    public void setGraveRoom(String graveRoom) {
        this.graveRoom = graveRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public int getClanId() {
        return clanId;
    }

    public void setClanId(int clanId) {
        this.clanId = clanId;
    }

    public Room getDonateRoom() {
        return World.getRoom(donateRoom);
    }

    public void setDonateRoom(String donateRoom) {
        this.donateRoom = donateRoom;
    }

    public void addApplicant(String name) {
        if (applicants == null) {
            applicants = new ArrayList<>();
        }
        applicants.add(name);
    }

    public void out(String input) {
        for (String name : members) {
            Player player = World.getPlayer(name.toLowerCase());
            if (player != null) {
                player.out("Clan:" + input);
            }
        }
    }
}
