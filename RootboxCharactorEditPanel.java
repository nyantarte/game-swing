import java.awt.event.*;
import javax.swing.*;
import java.io.*;
public class RootboxCharactorEditPanel extends JPanel{

    private JTextField m_nameField;
    private JComboBox m_typeField;
    private JComboBox m_lifeField;
    private JComboBox m_atkField;
    private JComboBox m_airAtkField;
    private JComboBox m_avoidField;
    private JComboBox m_airDefField;
    private JTextField m_imageField;
    private RootboxCharactor m_target;
    private ActionListener m_closeAction;
    public RootboxCharactorEditPanel(RootboxCharactor target){
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
        p.add(new JLabel("Type"));
        m_typeField=new JComboBox(RootboxCharactor.TYPE.values());
        if(null!=m_target && null!=m_target.getType()){
            m_typeField.setSelectedItem(m_target.getType());
        }
        p.add(m_typeField);
        add(p);
        p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        p.add(new JLabel("Life"));
        m_lifeField=new JComboBox(RootboxCharactor.SPEC_RANK.values());
        if(null!=m_target && null!=m_target.getLifeRank()){
            m_lifeField.setSelectedItem(m_target.getLifeRank());
        }
        p.add(m_lifeField);
        add(p);


        p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        p.add(new JLabel("Atk"));
        m_atkField=new JComboBox(RootboxCharactor.SPEC_RANK.values());
        if(null!=m_target && null!=m_target.getAtkRank()){
            m_atkField.setSelectedItem(m_target.getAtkRank());
        }
        p.add(m_atkField);
        add(p);

        p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        p.add(new JLabel("Air atk"));
        m_airAtkField=new JComboBox(RootboxCharactor.SPEC_RANK.values());
        if(null!=m_target && null!=m_target.getAirAtk()){
            m_airAtkField.setSelectedItem(m_target.getAirAtk());
        }
        p.add(m_airAtkField);
        add(p);
        
        p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        p.add(new JLabel("Air def"));
        m_airDefField=new JComboBox(RootboxCharactor.SPEC_RANK.values());
        if(null!=m_target && null!=m_target.getAirDefRank()){
            m_airDefField.setSelectedItem(m_target.getAirDefRank());
        }
        p.add(m_airDefField);
        add(p);

        p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        p.add(new JLabel("Avoid"));
        m_avoidField=new JComboBox(RootboxCharactor.SPEC_RANK.values());
        if(null!=m_target && null!=m_target.getAvoidRank()){
            m_avoidField.setSelectedItem(m_target.getAvoidRank());
        }
        p.add(m_avoidField);
        add(p);
        p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        p.add(new JLabel("Image"));
        m_imageField=new JTextField();
        if(null!=m_target && null!=m_target.getImageFile()){
            m_imageField.setText(m_target.getImageFile().getPath());            
        }
        p.add(m_imageField);
        add(p);

        p=new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        JButton b=new JButton("OK");
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(null==m_target){
                    m_target=new RootboxCharactor();
                    Main.s_charaList.add(m_target);
                }
                m_target.setName(m_nameField.getText());
                m_target.setType((RootboxCharactor.TYPE)m_typeField.getSelectedItem());
                m_target.setLifeRank((RootboxCharactor.SPEC_RANK)m_lifeField.getSelectedItem());
                m_target.setAtkRank((RootboxCharactor.SPEC_RANK)m_atkField.getSelectedItem());
                m_target.setAirAtk((RootboxCharactor.SPEC_RANK)m_airAtkField.getSelectedItem());
                m_target.setAirDefRank((RootboxCharactor.SPEC_RANK)m_airDefField.getSelectedItem());
                m_target.setAvoidRank((RootboxCharactor.SPEC_RANK)m_avoidField.getSelectedItem());
                m_target.setImageFile(new File(m_imageField.getText()));
                File oFile=new File(String.format("assets/charactors/%s.lsp",m_target.getName()));
                try{
                    BufferedWriter w=new BufferedWriter(new FileWriter(oFile));
                    String outStr="(chara ";
                    outStr+=String.format("(pair \"name\" \"%s\")",m_target.getName());
                    outStr+=String.format("(pair \"type\" \"%s\")", m_target.getType().toString());
                    outStr+=String.format("(pair \"life\" \"%s\")", m_target.getLifeRank().toString());
                    outStr+=String.format("(pair \"atk\" \"%s\")", m_target.getAtkRank().toString());
                    outStr+=String.format("(pair \"airAtk\" \"%s\")", m_target.getAirAtk().toString());
                    outStr+=String.format("(pair \"airDef\" \"%s\")", m_target.getAirDefRank().toString());
                    outStr+=String.format("(pair \"avoid\" \"%s\")", m_target.getAvoidRank().toString());
                    outStr+=String.format("(pair \"image\" \"%s\")", m_target.getImageFile().getPath());
                    outStr+=")";
                    w.write(outStr);
                    w.close();
                }catch(Exception err){
                    err.printStackTrace();
                }
                if(null!=m_closeAction){
                    m_closeAction.actionPerformed(e);
                }
            }
        });
        p.add(b);
        b=new JButton("CANCEL");
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(null!=m_closeAction){
                    m_closeAction.actionPerformed(e);
                }
            }
        });
        p.add(b);
        add(p);



    }

    public void setCloseAction(ActionListener l){
        m_closeAction=l;
    }

}