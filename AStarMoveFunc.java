import java.util.Arrays;
import java.util.Comparator;
import java.util.*;
public class AStarMoveFunc implements IMoveFunc{


    private FloorMap m_targetMap;

    private int[][] m_data;
    private Stack<Vector> m_pointStack=new Stack<>();
    public AStarMoveFunc(FloorMap m,MapParty p){
        m_targetMap=m;
        m_data=new int[m_targetMap.getHeight()][];

        for(int i=0;i < m_data.length;++i){
            m_data[i]=new int[m_targetMap.getWidth()];
            for(int j=0;j < m_data[i].length;++j){
                m_data[i][j]=-1;
            }
        }

        calcRoute((int)p.getPos().getX(),(int) p.getPos().getY());
        //m_pointStack.pop();
    }
    public void move(MapParty p){
        if(!m_pointStack.empty()){
            p.setPos(m_pointStack.pop());
        }
    }

    private int calcRoute(int x,int y){
        if(!m_targetMap.isMovable(x, y) ||
            0 > x ||
            x>=m_targetMap.getWidth() ||
            0>y ||
            y>=m_targetMap.getHeight()||
           -1!=m_data[y][x]){
            return -1;
        }
        


        int dist=m_data[y][x]=getGoalDistance(x, y);
        if(0==dist){
            m_pointStack.push(new Vector(x,y,0));
            return 0;
        }
        Object[] points=new Object[]{
            new Pair<>(new Vector(x+1,y,0),getGoalDistance(x+1, y)),
            new Pair<>(new Vector(x-1,y,0),getGoalDistance(x-1, y)),
            new Pair<>(new Vector(x,y+1,0),getGoalDistance(x, y+1)),
            new Pair<>(new Vector(x,y-1,0),getGoalDistance(x, y-1)),
        };
        Arrays.sort(points,new Comparator<Object>() {
            public int compare(Object o1,Object o2){
                return ((Pair<Vector,Integer>)o1).second-((Pair<Vector,Integer>)o2).second;
            }
        });

        for(Object o:points){
            Pair<Vector,Integer> po=(Pair<Vector,Integer>)o;
            if(-1<calcRoute((int)po.first.getX(), (int)po.first.getY())){
                m_pointStack.push(new Vector(x,y,0));
                return dist;
            }
        }
        return -1;
    }
    private int getMoveCost(int x,int y){
        if(0 >x || x>=m_targetMap.getWidth() || 0> y || y>=m_targetMap.getHeight()){
            return -1;
        }
        return m_data[y][x];
    }

    private int getGoalDistance(int x,int y){
        int gx=(int)m_targetMap.getGoalPos().getX();
        int gy=(int)m_targetMap.getGoalPos().getY();

        int xdist=Math.abs(gx-x);
        int ydist=Math.abs(gy-y);
        return (xdist+ydist);
    }
}