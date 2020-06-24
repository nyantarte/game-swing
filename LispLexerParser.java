import java.util.*;
public class LispLexerParser{

    private List<String> m_tokenList=new ArrayList<>();
    private int m_pos;


    public LispLexerParser(String s)
    throws LispParsingError
    {
        parse(s);
    }
    public String peekToken(){
        return m_tokenList.get(m_pos);
    }
    public String getNextToken(){
        ++m_pos;
        if((m_pos < m_tokenList.size())){
            return m_tokenList.get(m_pos);
    
        }
        return "";
    }
    public boolean isEnd(){
        return m_pos>=m_tokenList.size();
    }

    private void parse(String s)throws LispParsingError{
        int i=0;
        while(i < s.length()){
            if(Character.isWhitespace(s.charAt(i))){
                ++i;
                continue;
            }

            char c=s.charAt(i);

            if('('==c || ')'==c){
                m_tokenList.add(Character.toString(c));
                ++i;
            }else if(Character.isDigit(c)){
                StringBuilder sb=new StringBuilder();

                do{
                    sb.append(c);
                    ++i;
                }while(i < s.length() && Character.isDigit((c=s.charAt(i))));
                m_tokenList.add(sb.toString());
            }else if(Character.isLetter(c)){
                StringBuilder sb=new StringBuilder();

                do{
                    sb.append(c);
                    ++i;
                }while(i < s.length() && Character.isLetterOrDigit((c=s.charAt(i))));
                m_tokenList.add(sb.toString());
            }else if('\"'==c){
                StringBuilder sb=new StringBuilder();
                do{
                    sb.append(c);
                    ++i;
                }while(i < s.length() && '\"'!=(c=s.charAt(i)));
                sb.append(s.charAt(i++));
                m_tokenList.add(sb.toString());

            }else{
                throw new LispParsingError(String.format("Invalid token %s", c));
            }

        }
    }
}