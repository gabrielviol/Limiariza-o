
package binarizar;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Imagem {
    int LargMax=1024;
    int altMax=720;
    private Color img[][];
    private int larg;
    private int alt;
    public Imagem(String foto) {
        img = new Color[LargMax][LargMax];
        upImg(foto);
    }
    public void upImg(String foto){
        BufferedImage bf=null;
        try {
            bf = ImageIO.read(new File(foto));
        } catch (IOException ex) {
            Logger.getLogger(Imagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (bf.getWidth()<LargMax) {
            larg=bf.getWidth();
        }else
            larg=LargMax;
        if (bf.getHeight()<altMax) {
            alt=bf.getHeight();
        }else
            alt=altMax;
        int cont=0;
        for (int i = 0; i < alt; i++) {
            for (int j = 0; j < larg; j++) {
                cont++;
                img[i][j]= new Color(bf.getRGB(j, i));
                
            }
        }
    }
    public BufferedImage redimensionar(String foto, double porc ){
        
        BufferedImage bf = null;
        try {
            bf = ImageIO.read(new File(foto));
        } catch (IOException ex) {
            Logger.getLogger(Imagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        int larg = bf.getWidth();
        int alt = bf.getHeight();
        int escalaLarg = (int)(porc* larg);
        int escalaAlt = (int)(porc*alt);
        BufferedImage buff = new BufferedImage(escalaLarg, escalaAlt, bf.getType());
        Graphics2D g = buff.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bf, 0,0, escalaLarg,escalaAlt, 0,0,larg,alt, null);
        g.dispose();
        return buff;
}
    public void binarizarImagen(double limite){
        for (int i = 0; i < alt; i++) {
            for (int j = 0; j < larg; j++) {
                Color pix= img[i][j];
                int media =(pix.getBlue()+pix.getRed()+pix.getGreen())/3;
                if (media<limite) 
                    img[i][j]=Color.BLACK;
                else
                    img[i][j] = Color.WHITE;
            }
        }
    }
    public BufferedImage imprimirImagem(){
        BufferedImage saida = new BufferedImage(larg, alt, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < alt; i++) {
            for (int j = 0; j < larg; j++) {
                saida.setRGB(j, i, img[i][j].getRGB());
            }
        }
        return saida;
    }
}
