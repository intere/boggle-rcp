package com.intere.rcp.boggle.ui.test.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.intere.rcp.boggle.core.model.Player;
import com.intere.rcp.boggle.core.model.UIPlayer;
import com.intere.rcp.boggle.ui.controllers.PlayerManager;

/**
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class AddPlayerHandler extends AbstractHandler {

    public static final String PARAM_COUNT = "com.intere.rcp.boggle.ui.test.handlers.AddPlayerHandlerCommand.count";
    
    public static final String[] USERNAMES = {

    "a1exfinik", "a1exi", "a1exl", "a1exl23", "a1exxandra", "a1ex_serdyuk", "a1e_k", "a1girl", "a1gor", "a1greek1", "a1head", "a1hindes", "a1i2r3", "a1ias_007", "a1ic3", "a1ice_caroii", "a1icja", "a1ija", "a1ina7", "a1isaaa", "a1issa", "a1izta", "a1ke", "a1king", "a1kon",
            "a1l1i1c1e1", "a1legro", "a1len", "a1m", "a1m0stxfam0us", "a1max", "a1msta", "a1o4", "a1om", "a1one2gether", "a1phaomega", "a1poohgirl4ever", "a1q3", "a1rab3", "a1rplanes", "a1s1s1h1o1l1e", "a1so", "a1step", "a1ter3go", "a1terego", "a1tissimo", "a1u2b3r4e5y6",
            "a1way5andn3v3r", "a1waysinmyheart", "a1wayzsmi1ing", "a1wayzsmilin4u", "a1wingedangel", "a1xangel", "a1yonis4e", "a1ys5a", "a1ysha", "a1yssa", "a1z26b2y25", "a1za", "a1_b2_c3", "a1_ex", "a1_gp", "a1_sarah", "a2005ie", "a2006w18", "a2042", "a2046", "a21countrygal",
            "a222", "a2244freak", "a232007", "a232111", "a233e9", "a234hrg", "a23d", "a23_thnupumees", "a246", "a2487db72k", "a24hrbutterfly", "a24z", "a257", "a2704q", "a29105", "a29374223423423", "a2a258", "a2akhmetov", "a2a_code", "a2a_relations", "a2a_staff",
            "a2a_transcripts", "a2b2_c2", "a2buka", "a2c4d6", "a2cg05", "a2childcarecoop", "a2daizo", "a2id", "a2jward", "a2ling89", "a2m14", "a2macnei", "a2ndseason", "a2nsuhweetgrl", "a2plug", "a2re", "a2sasin", "a2sirius", "a2sittingco_op", "a2streetwear", "a2s_icons",
            "a2t8r", "a2thelex_xox", "a2the_aron", "a2united11", "a2yta", "a2zdeals", "a2zit", "a2z_simstories", "a2_1kuik", "a2_46bc", "a2_d2", "a2_jase", "a311renegade", "a32167", "a32s", "a332078", "a332_sdasdgg", "a333777g", "a33f44", "a33y", "a34134", "a354174", "a36",
            "a36yka", "a38fjal3h", "a3amat", "a3butt", "a3hin", "a3ibg4", "a3lilim", "a3pauji", "a3piecesuit", "a3rdperson", "a3ris", "a3umi", "a40in", "a442", "a494", "a4aja", "a4anomie", "a4c_cha1lenge", "a4c_challenge", "a4erni", "a4kin", "a4letterlie_xx", "a4orce97",
            "a4paran", "a4r0n", "a4sakenartist", "a4secondsleeper", "a4seduction", "a4serzh", "a4thandfinalkey", "a4ur", "a4white", "a50646m", "a517dogg", "a5204", "a52771", "a529", "a53", "a531", "a5579", "a55hole", "a56352q", "a567765", "a56c108m768o896", "a5a5a5a", "a5ant",
            "a5foot7tenor2", "a5g", "a5hh___", "a5hl3yx3", "a5hl3y__x", "a5ho3", "a5ka5", "a5love", "a5phyxia", "a5toic", "a5tr0", "a5tral", "a5_kiev", "a610", "a6124b53", "a617", "a641616", "a65", "a666ya", "a6bip", "a6ceht", "a6ent", "a6hana", "a6k", "a6m2_zero",
            "a6pa_kaga6pa", "a710is", "a716545", "a75241096", "a757", "a75f", "a7777777", "a78", "a78b", "a7998h", "a7ahmed", "a7i8b", "a7l15m", "a7memories", "a7r3yu", "a7thgradememory", "a7typ7i7cal", "a7u1", "a7ure", "a7x100", "a7xchallenge", "a7xchallengearc",
            "a7xchickpearl", "a7xcityofevil47", "a7xdaily", "a7xers", "a7xforever", "a7xfreak4life", "a7xkitty", "a7xpepito", "a7xscapegoatkid", "a7xsecrets", "a7xvans", "a7xwhore", "a7x_challenge", "a7x_chapter4", "a7x_chickk", "a7x_damien", "a7x_ffchallenge", "a7x_goddess",
            "a7x_hottie", "a7x_is_life", "a7x_is_sexxy", "a7x_manson666", "a7x_mcr", "a7x_promo", "a7x_reqs", "a7x_secret", "a7x_selena", "a7x_slash", "a7x_slash_rp", "a80", "a81_2007", "a82", "a89214", "a8o", "a8s9d0f", "a8_2007", "a8_20077", "a90hoursleep", "a911", "a9181acg",
            "a92387348734892", "a925photography", "a936bus1", "a94jarhead", "a99921", "a9c", "a9cssin", "a9d1d8", "a9ftw_blog", "a9hiroto", "a9p", "a9p1", "a9rating", "a9_brogparodies", "a9_claims", "a9_diz", "a9_fanclub", "a9_graphics", "a9_irisu_shou", "a9__icy", "aa009",
            "aa1", "aa10a", "aa122", "aa15", "aa1973", "aa31nkcc", "aa3mani", "aa40love", "aa60003", "aa8", "aa8aa", "aa921203", "aa9zt", "aaa00", "aaa000111aaa", "aaa117", "aaa1311", "aaa1973", "aaa4948", "aaa9_9", "aaaa12345", "aaaa7608", "aaaaa15", "aaaaa77777", "aaaaa88",
            "aaaaaaaaaaaaaa", "aaaaaaaaaaaaamy", "aaaaaaaaaasdf", "aaaaaaaaaa_feta", "aaaaaaaaapple", "aaaaaaaaf", "aaaaaaaah", "aaaaaaaann", "aaaaaaaasdf", "aaaaaaac", "aaaaaaa_hotgirl", "aaaaaace", "aaaaaaiieeeeeee", "aaaaaalice17", "aaaaaann", "aaaaaapples", "aaaaagh",
            "aaaaagh1", "aaaaahhh", "aaaaalbert", "aaaaandy", "aaaaangex3", "aaaaanna", "aaaaannie", "aaaaany_animals", "aaaaapple", "aaaaappley", "aaaaauius", "aaaaa__________", "aaaachase10", "aaaadddd", "aaaahhh", "aaaailikeitaaaa", "aaaalexxxx", "aaaalliicceeee",
            "aaaandrea_g", "aaaannnnggggiie", "aaaartstar", "aaaasdg", "aaaax", "aaaaxc", "aaaazot", "aaaazzzz", "aaaa_a", "aaabbbccc01", "aaabbbccc3", "aaabbbcccz", "aaabbcc", "aaablog", "aaac", "aaach", "aaach2u", "aaadams", "aaaddfgg", "aaadge", "aaaeo", "aaahbach",
            "aaahfiction", "aaahhhhhh", "aaahhphil", "aaahren", "aaahyes", "aaah_motherland", "aaaiaihg", "aaaibiza", "aaaida", "aaaiiieee", "aaaileeen", "aaalee_pshaa", "aaalello", "aaalisaa", "aaallchanged", "aaallmyheart", "aaalz", "aaambulaance", "aaamelyn", "aaament",
            "aaamirah", "aaamurrr", "aaanastazy", "aaandrewbmth", "aaandscene", "aaangie", "aaanne", "aaannie", "aaannieee", "aaanniiiee" };

    public Object execute(ExecutionEvent event) throws ExecutionException {
        
        int count = 1;
        
        if(event.getParameter(PARAM_COUNT)!=null) {
            try {
                count = Integer.parseInt(event.getParameter(PARAM_COUNT));
            } catch(NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
        
        for(int i=0;i<count;i++)
        {
            int index = (int)(Math.random() * USERNAMES.length);
            Player p = new UIPlayer(USERNAMES[index]);
            PlayerManager.getInstance().addPlayer(p);
        }
        
        return null;
    }

}
