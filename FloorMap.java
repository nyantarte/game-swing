import java.util.Random;


import java.awt.*;

public class FloorMap {

    static final Random s_rand=new Random(System.currentTimeMillis());
    static final int MAX_ROOM_NUM=3;
    private FieldMap m_data;
    private int[][] m_blocks;
    private int m_roomNum=0;
    private Rectangle[] m_roomRects=new Rectangle[MAX_ROOM_NUM];
    private static final int MIN_BLOCKS_NUM=1;
    
    public FloorMap(FieldMap data){
        m_data=data;

        resetFloor();

    }

    public void resetFloor(){
        m_roomNum=0;
        m_blocks=new int[m_data.getHeight()][];
        for(int i=0;i < m_blocks.length;++i){
            m_blocks[i]=new int[m_data.getWidth()];

        }
        splitFloor(0, 0, m_data.getWidth(), m_data.getHeight());
        initRoute();
//        System.out.println(String.format("%d %d %d %d %d %d",lastRoom.x,lastRoom.y,lastRoom.x+lastRoom.width,lastRoom.y+lastRoom.height,cx, cy));
        Vector v=getGoalPos();
        m_blocks[(int)v.getY()][(int)v.getX()]=2;
    }

    private void splitFloor(int x,int y,int w,int h){
        if(MAX_ROOM_NUM<=m_roomNum)
            return;
        if(MIN_BLOCKS_NUM >= (w/2) || MIN_BLOCKS_NUM >=(h/2) ||
            s_rand.nextBoolean()){
            int nW=MIN_BLOCKS_NUM+ s_rand.nextInt(w-MIN_BLOCKS_NUM);
            int nH=MIN_BLOCKS_NUM+s_rand.nextInt(h-MIN_BLOCKS_NUM);
            initBlocks(x,y,nW,nH);
            m_roomRects[m_roomNum]=new Rectangle(x,y,nW,nH);
            m_roomNum++;
     
            System.out.println(String.format("x=%d,y=%d,w=%d,h=%d", x,y,nW,nH));
            return;
        }

        if(s_rand.nextBoolean()){
            int nW=(w-1)/2;
            splitFloor(x,y,nW,h);
            splitFloor(x+nW+1, y, nW, h);
        }else{
            int nH=(h-1)/2;
            splitFloor(x,y,w,nH);
            splitFloor(x, y+nH+1, w, nH);
        }
    }


    private void initBlocks(int x,int y,int w,int h){
        for(int i=y;i < (y+h);++i){
            for(int j=x;j < (x+w);++j){
                m_blocks[i][j]=1;
            }
        }
    }

    private void initRoute(){
        for(int i=1;i < m_roomNum;++i){
            Rectangle r1=m_roomRects[i];
            Rectangle r0=m_roomRects[i-1];
            int x0=(int)r0.getCenterX();
            int y0=(int)r0.getCenterY();
            int x1=(int)r1.getCenterX();
            int y1=(int)r1.getCenterY();
            drawLine(x0,y0,x1,y1);
        }
    }
    private void drawLine(int x0,int y0,int x1,int y1){
        int dx=Math.abs(x1-x0);
        int dy=Math.abs(y1-y0);

        int sx=0;
        int sy=0;
        if(x0<x1){
            sx=1;
        }else{
            sx=-1;
        }

        if(y0 <y1){
            sy=1;
        }else{
            sy=-1;
        }

        int err=dx-dy;
        while(x0!=x1 || y0!=y1){
            m_blocks[y0][x0]=1;
            int e2=2*err;
            if(e2 >-dy){
                err=err-dy;
                x0=x0+sx;
            }else{
                err=err+dx;
                y0=y0+sy;
            }
        }
    }


    public int getWidth(){
        return m_data.getWidth();
    }
    public int getHeight(){
        return m_data.getHeight();
    }
    public Tile getTile(int x,int y){
        return TileSet.s_default.getTiles().get(m_blocks[y][x]);
    }
 
    public boolean isMovable(int x,int y){
        int t=m_blocks[y][x];

        return 0!=t;
    }
    public Rectangle[] getRoomsRect(){
        return m_roomRects;
    }
    public int getRoomNum(){
        return m_roomNum;
    }

    public Vector getGoalPos(){
        Rectangle lastRoom= m_roomRects[m_roomNum-1];
        int cx=(int)lastRoom.getCenterX();
        int cy=(int)lastRoom.getCenterY();
        return new Vector(cx,cy,0);

    }

    
}