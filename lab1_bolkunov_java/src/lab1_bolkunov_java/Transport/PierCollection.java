package lab1_bolkunov_java.Transport;

import lab1_bolkunov_java.Transport.*;
import lab1_bolkunov_java.Transport.Extensions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.sql.Array;
import java.util.*;

public class PierCollection {
    protected final char separator = ':';

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
        } else {
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

    public ITransport getTransport(String name, int index) {
        Pier pier = getPier(name);
        if (pier != null)
            return pier.getTransport(index);
        else
            return null;
    }

    public boolean saveData(String filename, String pierName) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
        try {
            if(piers.containsKey(pierName)){
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write("PierCollection");
                bw.newLine();
                bw.write("Pier" + separator + pierName);
                bw.newLine();
                ITransport s = piers.get(pierName).getElementAt(0);
                for (int i = 0; s != null; s = piers.get(pierName).getElementAt(++i)) {
                    bw.write(s.getClass().getSimpleName() + separator + s.toString());
                    bw.newLine();
                }
                bw.flush();
                return true;
            }else{
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean saveAllData(String fileName){
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("PierCollection");
            bw.newLine();
            for(String pierName : piers.keySet()) {
                bw.write("Pier" + separator + pierName);
                bw.newLine();
                ITransport s = piers.get(pierName).getElementAt(0);
                for (int i = 0; s != null; s = piers.get(pierName).getElementAt(++i)) {
                    bw.write(s.getClass().getSimpleName() + separator + s.toString());
                    bw.newLine();
                }
                bw.flush();
            }
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean loadData(String fileName){
        File file = new File(fileName);
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                boolean containsHeader = false;
                String line;
                Pier<Vehicle, IExtension> currentPier = null;
                boolean loadingCurrentPier = false;
                while (br.ready()) {
                    line = br.readLine();
                    if (containsHeader || line.contains("PierCollection")) {
                        if (!containsHeader) {
                            containsHeader = true;
                            continue;
                        }
                        if (line.contains("Pier")) {
                            String currentPierName = line.split(Character.toString(separator))[1];
                            if(!loadingCurrentPier) {
                                if (piers.containsKey(currentPierName)) {
                                    currentPier = piers.get(currentPierName);
                                    currentPier.clear();
                                }else {
                                    currentPier = new Pier<Vehicle, IExtension>(pictureWidth, pictureHeight);
                                    piers.put(currentPierName, currentPier);
                                }
                                loadingCurrentPier = true;
                            }else if(loadingCurrentPier){
                                break;
                            }
                            continue;
                        }
                        if (line.contains("MotorShip") && currentPier != null) {
                            currentPier.add(new MotorShip(line.split(Character.toString(separator))[1]));
                            continue;
                        }
                        if (line.contains("Ship") && currentPier != null) {
                            currentPier.add(new Ship(line.split(Character.toString(separator))[1]));
                            continue;
                        }
                    } else {
                        return false;
                    }
                }
                if (currentPier == null) {
                    return false;
                } else {
                    return true;
                }
            } catch (Exception ex) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean loadAllData(String fileName) {
        File file = new File(fileName);
        piers.clear();
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                boolean containsHeader = false;
                String line;
                Pier<Vehicle, IExtension> currentPier = null;
                while (br.ready()) {
                    line = br.readLine();
                    if (containsHeader || line.contains("PierCollection")) {
                        if (!containsHeader) {
                            containsHeader = true;
                            continue;
                        }
                        if (line.contains("Pier")) {
                            String currentPierName = line.split(Character.toString(separator))[1];
                            currentPier = new Pier<Vehicle, IExtension>(pictureWidth, pictureHeight);
                            piers.put(currentPierName, currentPier);
                            continue;
                        }
                        if (line.contains("MotorShip") && currentPier != null) {
                            currentPier.add(new MotorShip(line.split(Character.toString(separator))[1]));
                            continue;
                        }
                        if (line.contains("Ship") && currentPier != null) {
                            currentPier.add(new Ship(line.split(Character.toString(separator))[1]));
                            continue;
                        }
                    } else {
                        return false;
                    }
                }
                if (currentPier == null) {
                    return false;
                } else {
                    return true;
                }
            } catch (Exception ex) {
                return false;
            }
        } else {
            return false;
        }
    }
}
