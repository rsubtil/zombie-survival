package tools;

import com.jme3.asset.AssetManager;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;

public class ModelLoader {
    
    private final AssetManager assetManager;
    
    public ModelLoader(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
    
    public Spatial load(String name) {
        // Creates a Node and loads the model
        Node visual = new Node("Visual");
        Spatial model = assetManager.loadModel("Models/" + name + ".j3o");
        visual.attachChild(model);
        
        // Change MaterialParams to MagNearest
        setBlockyTexture(model);
        
        return visual;
    }
    
    public void setBlockyTexture(Spatial spat) {
        if(spat instanceof Node) {
            Node n = (Node)spat;
            for(Spatial s : n.getChildren()) {
                setBlockyTexture(s);
            }
        } else if(spat instanceof Geometry) {
            Geometry g = (Geometry)spat;
            g.getMaterial().getTextureParam("DiffuseMap").getTextureValue().setMagFilter(Texture.MagFilter.Nearest);
        }
    }
    
    public static void setAlpha(Spatial spat) {
        if(spat instanceof Node) {
            Node n = (Node)spat;
            for(Spatial s : n.getChildren()) {
                setAlpha(spat);
            }
        } else if(spat instanceof Geometry) {
            Geometry g = (Geometry)spat;
            g.setQueueBucket(RenderQueue.Bucket.Transparent);
        }
    }
}
