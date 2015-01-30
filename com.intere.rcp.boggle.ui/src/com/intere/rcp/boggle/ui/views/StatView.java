package com.intere.rcp.boggle.ui.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import com.intere.rcp.boggle.core.model.Game;
import com.intere.rcp.boggle.core.model.PairedList;
import com.intere.rcp.boggle.core.model.Player;
import com.intere.rcp.boggle.core.model.Stat;
import com.intere.rcp.boggle.ui.controllers.GameManager;
import com.intere.rcp.boggle.ui.providers.PlayerLabelProvider;
import com.intere.rcp.boggle.ui.providers.WordListLabelProvider;
import com.intere.rcp.boggle.ui.providers.WordTableContentProvider;
import com.intere.rcp.boggle.ui.providers.WordTableViewSorter;

/**
 * View for the stats.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class StatView extends ViewPart {
    
    /** The ID of this view.  */
    public static final String ID = "com.intere.rcp.boggle.ui.views.StatView";
    
    /** The Game ID.  */
    @SuppressWarnings("unused")
    private String gameId;
    
    /** The Tab Folder.  */
    private CTabFolder folder;
    
    /** The list of children.  */
    private List<StatTab> kids  = new ArrayList<StatTab>();

    /** The game.  */
    private Game game;
    
    /** The Game Stats.  */
    private Stat stats;
    
    /** The Parent object.  */
    @SuppressWarnings("unused")
    private Composite parent;

    @Override
    public void createPartControl(Composite parent) {
        parent.setLayout(new GridLayout());
        folder = new CTabFolder(parent, SWT.NONE);
        folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        folder.setLayout(new GridLayout());        
    }
    
    /**
     * Gives you a sorted list of players (sorted by score).
     * @return
     */
    private Player[] sortByScore()
    {
        PairedList<String, Player> players = new PairedList<String, Player>();
        for(Player p : game.getPlayerList()) {
            if(!players.containsKey(p.getPlayerId())) {
                players.add(p.getPlayerId(), p);
            }
        }
        
        Player[] sorted = players.getList().toArray(new Player[players.getList().size()]);
        Arrays.sort(sorted, new Comparator<Player>() {
            public int compare(Player o1, Player o2) {
                return  game.getScore(o2.getPlayerId()).getScore() - game.getScore(o1.getPlayerId()).getScore();
            }
        });
        
        
        return sorted;
    }

    @Override
    public void setFocus() {
        // TODO Auto-generated method stub
    }
    
    /**
     * The Tab that shows stats.
     */
    private class StatTab extends CTabItem {

        public StatTab(CTabFolder parent, int style, Player player) {
            super(parent, style);
            setText(player.getUsername());
            Composite c = new Composite(parent, SWT.NONE);
            c.setLayout(new GridLayout());
            
            Label scoreLabel = new Label(c, SWT.NONE);
            scoreLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
            scoreLabel.setText("Total Score: " + game.getScore(player.getPlayerId()).getScore() );
            
            TableViewer words = new TableViewer(c);
            words.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
            
            TableColumn col = new TableColumn(words.getTable(), SWT.NONE);
            col.setText("Word");
            col.setWidth(100);
            
            col = new TableColumn(words.getTable(), SWT.NONE);
            col.setText("Score");
            col.setWidth(50);
            
            words.setContentProvider(new WordTableContentProvider());
            words.setLabelProvider(new WordListLabelProvider(game.getScoringSet()));
            words.setSorter(new WordTableViewSorter());
            words.setInput(stats.getPlayerWords().get(player.getPlayerId()));
            
            if(player.isComputer()) {
                setImage(PlayerLabelProvider.COMP_IMAGE);
            } else {
                setImage(PlayerLabelProvider.USER_IMAGE);
            }
                
            setControl(c);
        }   
    }

    /**
     * The Game ID.
     * @param gameId
     */
    public void setGameId(String gameId) {
        this.gameId = gameId;
        
        game = GameManager.getInstance().getGame(gameId);
        if(game!=null) {
            stats = game.getStats();
        }
        
        for(StatTab tab : kids)
        {
            tab.dispose();
        }        
        
        folder.redraw();
        
        if(stats!=null) {
            
            Player[] sorted = sortByScore();
            for(Player p : sorted) {
                kids.add(new StatTab(folder, SWT.NONE, p));
            }
            
            folder.setSelection(0);
        }
        folder.redraw();
        
        folder.update();

    }
    
}
