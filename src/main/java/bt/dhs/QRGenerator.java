package bt.dhs;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class QRGenerator {
    public static void generateQrCode(String data, Integer width, Integer height, String[] topText, String[] bottomText, String outputDir) throws IOException {
        try{
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE,width,height);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix,"PNG",pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();

            if(bottomText.length > 0 && topText.length > 0){
                InputStream in = new ByteArrayInputStream(pngData);
                BufferedImage image = ImageIO.read(in);
                int heightImage = image.getHeight();
                BufferedImage outputImage = new BufferedImage(heightImage,heightImage,BufferedImage.TYPE_INT_ARGB);

                Graphics g = outputImage.getGraphics();
                g.setColor(Color.white);
                g.fillRect(0,0,outputImage.getWidth(),outputImage.getHeight());
                g.drawImage(image,0,0,image.getWidth(),image.getHeight(),null);
                g.setFont(new Font("Avenir",Font.PLAIN,32));
                Color textColor = Color.BLACK;
                g.setColor(textColor);
                FontMetrics fm = g.getFontMetrics();
                int topStartingYPosition = 100;
                int bottomStartingYPosition = height - 80;

                for(String displayText: topText){
                    g.drawString(displayText,(outputImage.getWidth()/2) - (fm.stringWidth(displayText)/2),topStartingYPosition);
                    topStartingYPosition += 50;
                }

                for(String displayText: bottomText){
                    g.drawString(displayText,(outputImage.getWidth()/2) - (fm.stringWidth(displayText)/2),bottomStartingYPosition);
                    bottomStartingYPosition+= 50;
                }
                ImageIO.write(outputImage,"PNG",new File(outputDir+"/"+data + ".png"));

            }
//            return pngData;

        }catch (WriterException ex){
            ex.printStackTrace();
        }
    }
}
