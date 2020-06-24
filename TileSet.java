import java.awt.Color;
import java.util.*;
public class TileSet {


    static TileSet s_default=new TileSet(
        new Tile[]{
            new Tile(Color.BLACK),
            new Tile(Color.WHITE),
            new Tile(Color.GRAY)
        }

    );
    private ArrayList<Tile> m_tiles=new ArrayList<>();

    public TileSet(Tile[] ta){

        for(Tile t:ta){
            m_tiles.add(t);
        }
    }

    public List<Tile> getTiles(){
        return m_tiles;
    }




}