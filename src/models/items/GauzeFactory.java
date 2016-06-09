package models.items;

import com.jme3.asset.AssetManager;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Node;
import java.util.logging.Logger;
import models.ModelLoader;

public class GauzeFactory {
    
    private static final Logger logger = Logger.getLogger(ModelLoader.class.getName());
    
    public static Node create(AssetManager assetManager) {
        logger.fine("Loading model \"Gauze\" with specific factory");
        Node model = (Node)assetManager.loadModel("Models/I_Gauze.j3o");
        model.setQueueBucket(RenderQueue.Bucket.Transparent);
        
        return model;
    }
}
