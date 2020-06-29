import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class FieldMapEditPanel extends JPanel{

    private FieldMap m_target;
    private JTextField m_nameField;
    private JTextField m_widthField;
    private JTextField m_heightField;
    private JTextField m_floorField;
    private ActionListener m_closeAction;
    public FieldMapEditPanel(FieldMap target){
        super();
        m_target=target;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JPanel p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        p.add(new JLabel("Name"));
        m_nameField=new JTextField(null!=m_target?m_target.getName():"");
        p.add(m_nameField);
        add(p);

        p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        p.add(new JLabel("Width"));
        m_widthField=new JTextField(null!=m_target?Integer.toString(m_target.getWidth()):"");
        p.add(m_widthField);
        add(p);

        p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        p.add(new JLabel("Height"));
        m_heightField=new JTextField(null!=m_target?Integer.toString(m_target.getHeight()):"");
        p.add(m_heightField);
        add(p);

        p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        p.add(new JLabel("Floor num"));
        m_floorField=new JTextField(null!=m_target?Integer.toString(m_target.getMaxFloorNum()):"");
        p.add(m_floorField);
        add(p);


        p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        JButton b=new JButton("OK");
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(null==m_target){
                    m_target=new FieldMap();
                    Main.s_mapList.add(m_target);
                }
                try{
                    m_target.setName(m_nameField.getText());
                    m_target.setWidth(Integer.valueOf(m_widthField.getText()));
                    m_target.setHeight(Integer.valueOf(m_heightField.getText()));
                    m_target.setMaxFloorNum(Integer.valueOf(m_floorField.getText()));
                    
                    File oFile=new File(String.format("assets/map/%s.lsp",m_target.getName()));
                    String outStr="(map ";
                    outStr+=String.format("(pair \"name\" \"%s\")",m_target.getName());
                    outStr+=String.format("(pair \"width\" %d) ",m_target.getWidth());
                    outStr+=String.format("(pair \"height\" %d)", m_target.getHeight());
                    outStr+=String.format("(pair \"floor\" %d)",m_target.getMaxFloorNum());
                    outStr+=")";
                    BufferedWriter w=new BufferedWriter(new FileWriter(oFile));
                    w.write(outStr);
                    w.close();

                }catch(Exception err){
                    err.printStackTrace();
                }
                ((MainWindow)Main.s_mainWin).returnState();
            }
        });
        p.add(b);
        b=new JButton("CANCEL");
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ((MainWindow)Main.s_mainWin).returnState();

            }
        });
        p.add(b);
        add(p);
    }

}