package models.props;

import com.jme3.asset.AssetManager;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Node;
import models.ModelLoader;
import java.util.logging.Logger;

public class TallGrassFactory {
    
    private static final Logger logger = Logger.getLogger(ModelLoader.class.getName());
    
    public static Node create(AssetManager assetManager) {
        logger.fine("Loading model \"TallGrass\" with specific factory");
        Node model = (Node)assetManager.loadModel("Models/props/TallGrass.j3o");
        model.setQueueBucket(RenderQueue.Bucket.Transparent);
        
        return model;
    }
}
