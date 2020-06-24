import javax.swing.*;
import java.awt.*;
public class GridLayoutPanel extends JPanel{
    private GridBagConstraints m_constraints=new GridBagConstraints();
    
    public GridLayoutPanel(){
        super();
        setLayout(new GridBagLayout());
    }

    public void add(Component c,int x,int y){
        GridBagLayout l=(GridBagLayout)getLayout();

        m_constraints.gridx=x;
        m_constraints.gridy=y;
        l.setConstraints(c, m_constraints);
        add(c);
    }
}