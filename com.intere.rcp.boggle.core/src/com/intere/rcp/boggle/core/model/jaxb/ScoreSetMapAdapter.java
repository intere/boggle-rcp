package com.intere.rcp.boggle.core.model.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.intere.rcp.boggle.core.model.ScoreSet;

public class ScoreSetMapAdapter extends XmlAdapter<ScoreSetMapAdapter.ScoreSetAdapter, ScoreSet> {

    @Override
    public ScoreSetAdapter marshal(ScoreSet scoreSet) throws Exception {
        
        List<ScoreSetEntry> entries = new ArrayList<ScoreSetEntry>();
        
        for(Character key : scoreSet.getScoreMap().keySet())
        {
            entries.add(new ScoreSetEntry(key, scoreSet.getScoreMap().get(key)));
        }

        return new ScoreSetAdapter(entries);
    }

    @Override
    public ScoreSet unmarshal(ScoreSetAdapter adapter) throws Exception {
        
        ScoreSet ss = new ScoreSet();
        
        for(ScoreSetEntry e : adapter.getScoreSetList())
        {
            ss.getScoreMap().put(e.getCharacter(), e.getScore());
        }
        
        return ss;
    }
    
    @XmlType
    @XmlAccessorType(XmlAccessType.NONE)
    public static final class ScoreSetAdapter
    {
        @XmlElement(name="score-set")
        @XmlElementWrapper(name="score-entry")
        private List<ScoreSetEntry> scoreSetList;
        
        public ScoreSetAdapter() {
            // TODO Auto-generated constructor stub
        }
        
        public ScoreSetAdapter(List<ScoreSetEntry> scoreSetList) {
            this.scoreSetList = scoreSetList;
        }
        
        public List<ScoreSetEntry> getScoreSetList() {
            return scoreSetList;
        }
        
        public void setScoreSetList(List<ScoreSetEntry> scoreSetList) {
            this.scoreSetList = scoreSetList;
        }
    }
    
    
    @XmlType
    @XmlAccessorType(XmlAccessType.NONE)
    public static final class ScoreSetEntry {
        @XmlAttribute(name="character")
        private Character character;
        
        @XmlAttribute(name="score")
        private Integer score;
        
        public ScoreSetEntry() {
            // TODO Auto-generated constructor stub
        }

        public ScoreSetEntry(Character character, Integer score) {
            super();
            this.character = character;
            this.score = score;
        }
        
        public Character getCharacter() {
            return character;
        }
        
        public void setCharacter(Character character) {
            this.character = character;
        }
        
        public Integer getScore() {
            return score;
        }
        
        public void setScore(Integer score) {
            this.score = score;
        }
        
    }



}
