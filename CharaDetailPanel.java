import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
public class CharaDetailPanel extends JPanel{
    private RootboxCharactor m_data;

    public CharaDetailPanel(RootboxCharactor c){
        super();
        m_data=c;

        setLayout(new BorderLayout());
        ImageIcon ii=new ImageIcon();
        try{
            Image i=
                null!=m_data.getImageFile()?ImageIO.read(m_data.getImageFile()):
                new BufferedImage(Main.CARD_IMG_W,Main.CARD_IMG_H,BufferedImage.TYPE_INT_ARGB);       
            ii.setImage(i);
        }catch(Exception e){
            e.printStackTrace();
        }
        add(new JLabel(ii),BorderLayout.WEST);
        JPanel p=new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(new JLabel(String.format("Name %s",m_data.getName())));
        p.add(new JLabel(String.format("Life %s",
            null!=m_data.getLifeRank()?
                m_data.getLifeRank().toString():"")));
        p.add(new JLabel(String.format("Atk %s",
            null!=m_data.getAtkRank()?
                m_data.getAtkRank().toString():"")));
        p.add(new JLabel(String.format("Air atk %s",
            null!=m_data.getAirAtk()?
                m_data.getAirAtk().toString():"")));
        p.add(new JLabel(String.format("Air atk %s",
            null!=m_data.getAirDefRank()?
                m_data.getAirDefRank().toString():"")));
        p.add(new JLabel(String.format("Avoid %s",
            null!=m_data.getAvoidRank()?
                m_data.getAvoidRank().toString():"")));
        add(p,BorderLayout.CENTER);


    }

}