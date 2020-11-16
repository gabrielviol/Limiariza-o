
package binarizar;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import javax.imageio.ImageIO;




public class Binarizar {

    
    public static void main(String[] args) throws IOException {
      
        Imagem obj = new Imagem("entrada.jpg");
        obj.binarizarImagen(100);
        BufferedImage img = obj.imprimirImagem();
        ImageIO.write(img, "jpg", new File("saida.jpg"));
        
        
    }
}
