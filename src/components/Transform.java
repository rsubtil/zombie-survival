package components;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.simsilica.es.EntityComponent;

public class Transform implements EntityComponent {
    
    private final Vector3f position;
    private final Quaternion rotation;
    private final Vector3f scale;
    
    public Transform() {
        this(new Vector3f(0, 0, 0), new Quaternion(), new Vector3f(1, 1, 1));
    }
    
    public Transform(Vector3f position) {
        this(position, new Quaternion(), new Vector3f(1, 1, 1));
    }
    
    public Transform(Vector3f position, Quaternion rotation) {
        this(position, rotation, new Vector3f(1, 1, 1));
    }
    
    public Transform(Vector3f position, Vector3f scale) {
        this(position, new Quaternion(), scale);
    }
    
    public Transform(Vector3f position, Quaternion rotation, Vector3f scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }
    
    public Vector3f getPosition() {
        return position;
    }
    
    public Quaternion getRotation() {
        return rotation;
    }
    
    public Vector3f getScale() {
        return scale;
    }
    
    public void move(Vector3f v) {
        this.position.addLocal(v);
    }
    
    public void move(float x, float y, float z) {
        this.position.add(x, y, z);
    }
    
    public void setPosition(Vector3f pos) {
        this.position.set(pos);
    }
    
    public void setPosition(float x, float y, float z) {
        this.position.set(x, y, z);
    }
    
    public void rotate(Quaternion q) {
        this.rotation.addLocal(q);
    }
    
    public void rotate(float x, float y, float z) {
        this.rotation.fromAngles(FastMath.DEG_TO_RAD * x, FastMath.DEG_TO_RAD * y, FastMath.DEG_TO_RAD * z);
    }
    
    public void setRotation(Quaternion q) {
        this.rotation.set(q);
    }
    
    public void setRotation(float x, float y, float z) {
        this.rotation.set(new Quaternion());
        this.rotate(x, y, z);
    }
    
    public void setRotation(Vector3f rot) {
        this.setRotation(rot.x, rot.y, rot.z);
    }
    
    public void scale(float scalar) {
        this.scale.multLocal(scalar);
    }
    
    public void scale(float x, float y, float z) {
        this.scale.multLocal(x, y, z);
    }
    
    public void scale(Vector3f scale) {
        this.scale.multLocal(scale);
    }
    
    public void setScale(float scale) {
        this.scale.set(scale, scale, scale);
    }
    
    public void setScale(float x, float y, float z) {
        this.scale.set(x, y, z);
    }
    
    public void setScale(Vector3f scale) {
        this.scale.set(scale);
    }
}
