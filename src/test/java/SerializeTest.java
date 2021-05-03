import org.junit.jupiter.api.Test;

import java.io.*;

public class SerializeTest {
    
    @Test
    void sTest() {
        try {
            File file = new File("d:/test/s.dat");
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new T());
            oos.flush();
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void lTest() {
        try {
            File file = new File("d:/test/s.dat");
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            T t = (T)ois.readObject();
            System.out.println(t.m + " " + t.n);
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class T implements Serializable {
    int m = 10;
    transient int n = 12; //password
}
