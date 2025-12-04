import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Properties;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class Utils {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY = "XdWbtMSwJuwKZA1";

    public static Color black, darkGray, gray, darkBlue, blue;
    public static String dir, pDir;

    public static void initializeUtils() {
        black = new Color(33,33,33);
        darkGray = new Color(38,38,38);
        gray = new Color(45,45,42);
        darkBlue = new Color(9,163,153);
        blue = new Color(6,186,199);
        System.setProperty("sun.java2d.uiScale", "1.0");

        dir = System.getenv("APPDATA") + "\\.gatecraft";
        pDir = System.getProperty("user.home") + "\\CraftProjects";
    }

    public static void newConfig(String property, String value) {
        try {
            Properties properties = new Properties();
            properties.setProperty(property, value);
            properties.store(new FileOutputStream(Utils.class.getResource("mainDir/config.properties").getFile()), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getConfig(String property) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(Utils.class.getResource("mainDir/config.properties").getFile()));
            return properties.getProperty(property);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveProperty(String key, String value) throws Exception {
        Properties properties = loadPropertiesSecurely();
        properties.setProperty(key, value);
        savePropertiesSecurely(properties);
    }

    public static String loadProperty(String key) throws Exception {
        Properties properties = loadPropertiesSecurely();
        return properties.getProperty(key);
    }

    private static void savePropertiesSecurely(Properties properties) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(properties.toString().getBytes());

        try (FileOutputStream outputStream = new FileOutputStream(Utils.class.getResource("mainDir/data.properties").getPath())) {
            outputStream.write(encryptedData);
        }
    }

    private static Properties loadPropertiesSecurely() throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedData;
        try (FileInputStream inputStream = new FileInputStream(Utils.class.getResource("mainDir/data.properties").getPath())) {
            encryptedData = inputStream.readAllBytes();
        }

        byte[] decryptedData = cipher.doFinal(encryptedData);
        Properties properties = new Properties();
        properties.load(new ByteArrayInputStream(decryptedData));
        return properties;
    }

    public static Image scaledImage(String name, int width, int height) {
        try {
            return ImageIO.read(Utils.class.getResource("/mainDir/img/" + name)).getScaledInstance(width,height,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Icon scaledIcon(String name, int width, int height) {
        return new NoScalingIcon(new ImageIcon(scaledImage(name, width, height)));
    }

    public static Image scaledComponent(String name, int width, int height) {
        try {
            return ImageIO.read(Utils.class.getResource("/mainDir/comps/" + name)).getScaledInstance(width,height,Image.SCALE_SMOOTH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Icon scaledComponentIcon(String name, int width, int height) {
        return new NoScalingIcon(new ImageIcon(scaledComponent(name, width, height)));
    }

    public static boolean contains(File file, File[] array) {
        if (file == null || array == null) return false;
        String nombre = file.getName();
        String[] nombresArray = fileNames(array);

        for (String s : nombresArray) {
            if (s.equals(nombre)) return true;
        } return false;
    }

    public static boolean containsAll(File[] array1, File[] array2) {
        if (array1 == null || array2 == null) return false;
        String[] nombresArray1 = fileNames(array1);
        String[] nombresArray2 = fileNames(array2);

        Arrays.sort(nombresArray1);
        Arrays.sort(nombresArray2);

        return Arrays.equals(nombresArray1, nombresArray2);
    }

    private static String[] fileNames(File[] array) {
        String[] nombres = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            nombres[i] = array[i].getName();
        }
        return nombres;
    }
}
