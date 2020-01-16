
package hopfield;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public final class YSA {

    BufferedImage resimDosya;    

    resim(double[][] harf, String dosyaYolu) throws IOException
    {
        resimDosya = ImageIO.read(ResimOlustur(harf, dosyaYolu));
    }
    resim(String dosyaAdi) throws IOException{
        resimDosya = ImageIO.read(getClass().getResource(dosyaAdi));
    }
    resim(double[] harfUclu) throws IOException{
        int indeks = 0;
        double[][] harf = new double[5][5];
        for (int i = 0; i < 5; i+=2) 
        {
            for (int j = 0; j < 5; j+=2)
            {
                harf[i][j] = harfUclu[indeks];
                if(j != 4 && harfUclu[indeks] == 1 && harfUclu[indeks+1] == 1)
                    harf[i][j+1] = harfUclu[indeks];
                if(i != 4 && harfUclu[indeks] == 1 && harfUclu[indeks+3] == 1)
                    harf[i+1][j] = harfUclu[indeks];
                indeks++;
            }
        }
        resimDosya = ImageIO.read(ResimOlustur(harf, "test.bmp"));
    }
    
    
    public File ResimOlustur(double[][] harf , String dosyaYolu) throws IOException
    {
        BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < 500; y++) 
        {
            for (int x = 0; x < 500; x++) {
                image.setRGB(x, y, Color.WHITE.getRGB());
            }
        }
        
        for (int i = 0; i < 5; i++) 
        {
            for (int j = 0; j < 5; j++) 
            {
                if(j != 4 && harf[i][j] == 1 && harf[i][j+1] == 1 || j == 4 && harf[i][j-1] == 1 && harf[i][j] == 1)
                {
                    for (int x=100*j ; x < 100*(j+1); x++) 
                    {
                        for (int a = 0; a < 100; a++) {
                            image.setRGB(x, (100*i)+a, Color.BLACK.getRGB());
                        }
                    }
                }
                if(i != 4 && harf[i][j] == 1 && harf[i+1][j] == 1 || i == 4 && harf[i-1][j] == 1 && harf[i][j] == 1)
                {
                    for (int y = 100; y < 100*(i+1); y++) 
                    {
                        for (int a = 0; a < 100; a++) 
                        {
                            image.setRGB((100*j)+a, y, Color.BLACK.getRGB());
                        }
                    }
                }
            }
        }
        File outPutFile = new File(dosyaYolu);
        ImageIO.write(image, "bmp", outPutFile);
        return outPutFile;
    }
    public int totalPixelRGB(int pixel){
        int red = (pixel >> 16) &0xff;
        int green = (pixel >> 8) &0xff;
        int blue = (pixel) &0xff;
        return red+green+blue;
    }
    public double[] pixelGetir(){
        double[] pixelDouble = new double[25];
        int indeks = 0;
        for (int y = 0; y < 5; y++)
        {
            for (int x = 0; x < 5; x++)
            {
                pixelDouble[indeks] = totalPixelRGB(resimDosya.getRGB(x, y));
                indeks++;
            }
        }
        return pixelDouble;
    }
        public double[] pixelGetirUclu(){
        double[] pixelDouble = new double[25];
        int indeks = 0;
        for (int y = 0; y < 5; y++)
        {
            for (int x = 0; x < 5; x++)
            {
                pixelDouble[indeks] = totalPixelRGB(resimDosya.getRGB(x, y));
                indeks++;
            }
        }
        return pixelDouble;
    }
    
    public static void Ciz(double[] pixel){
        int indeks = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(pixel[indeks] == 1) System.out.print("*");
                else System.out.println("");
            }
        }
    }
}
