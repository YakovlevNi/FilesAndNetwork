import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class MainQ {
    public static void main(String[] args) {
        Document doc = null;


        try {
            doc = Jsoup.connect("https://static.wikia.nocookie.net/gulman/images/0/01/GDogU7yp1C4.jpg/revision/latest/scale-to-width-down/215?cb=20150410140749&path-prefix=ru").get();
            Elements picLink = doc.select("img");
            System.out.println(picLink);

            for (Element element : picLink) {

                if (element.hasAttr("src")) {
                    String imagine = element.absUrl("src");
                    System.out.println(imagine);
                   // download(imagine);
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void download(String path) throws IOException {
        String PATH_FOR_IMG = "C:\\Users\\Yakov\\Desktop\\Новая папка (4)\\";
        URL url = new URL(path);
        String[] fragments = path.split("\\/");
        String fileName = fragments[fragments.length - 1].replace(":", "").replace("?", "");
        InputStream in = url.openStream();
        OutputStream out = new BufferedOutputStream(new FileOutputStream(PATH_FOR_IMG + fileName));
        for (int b; (b = in.read()) != -1; ) {
            out.write(b);
        }
        out.close();
        in.close();
    }


}

