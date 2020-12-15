package lab1_bolkunov_java.Transport;

import lab1_bolkunov_java.Transport.*;
import lab1_bolkunov_java.Transport.Extensions.*;

import java.sql.Array;
import java.util.*;

public class PierCollection {
    private final Map<String, Pier<Vehicle, IExtension>> piers;

    private final int pictureHeight;
    private final int pictureWidth;

    public PierCollection(int picWidth, int picHeight) {
        piers = new HashMap<String, Pier<Vehicle, IExtension>>();
        pictureHeight = picHeight;
        pictureWidth = picWidth;
    }

    public String[] getKeys() {
        return piers.keySet().toArray(new String[0]);
    }

    public boolean addPier(String name) {
        if (!piers.containsKey(name)) {
            piers.put(name, new Pier<Vehicle, IExtension>(pictureWidth, pictureHeight));
            return true;
        }else{
            return false;
        }
    }

    public void removePier(String name) {
        if (piers.containsKey(name)) {
            piers.remove(name);
        }
    }

    public Pier<Vehicle, IExtension> getPier(String name) {
        if (piers.containsKey(name)) {
            return piers.get(name);
        } else {
            return null;
        }
    }

    public ITransport getTransport(String name, int index){
        Pier pier = getPier(name);
        if(pier != null)
            return pier.getTransport(index);
        else
            return null;
    }
}
