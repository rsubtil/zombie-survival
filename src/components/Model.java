package components;

import com.simsilica.es.EntityComponent;

public class Model implements EntityComponent {
    
    public String name;
    
    public static final String I_Gauze = "I_Gauze";
    public static final String I_HealthKit = "I_HealthKit";
    public static final String I_Pills = "I_Pills";
    
    public Model(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
