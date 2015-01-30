package com.intere.rcp.boggle.core.model.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.intere.rcp.boggle.core.model.PairedList;
import com.intere.rcp.boggle.core.model.Player;

/**
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class PlayerPairedListAdapter extends XmlAdapter<PlayerPairedListAdapter.PlayerAdapter, PairedList<String,Player>>
{

    @Override
    public PlayerAdapter marshal(PairedList<String, Player> playerMap) throws Exception {
        ArrayList<Player> players = new ArrayList<Player>();
        
        players.addAll(playerMap.getList());        
        
        return new PlayerAdapter(players);
    }

    @Override
    public PairedList<String, Player> unmarshal(PlayerAdapter players) throws Exception {
        
        PairedList<String, Player> playerList = new PairedList<String, Player>();
        
        for(Player p : players.getPlayers())
        {
            playerList.add(p.getPlayerId(), p);
        }

        return playerList;
    }
    

    @XmlType(name="player-list")
    @XmlAccessorType(XmlAccessType.NONE)
    public final static class PlayerAdapter
    {
        @XmlElement(name="player")
        private List<Player> players;
        
        public PlayerAdapter() {
            // TODO Auto-generated constructor stub
        }
        
        public PlayerAdapter(List<Player> players) {
            this.players = players;
        }
        
        public List<Player> getPlayers() {
            return players;
        }
        
        public void setPlayers(List<Player> players) {
            this.players = players;
        }
    }
    
    
}
