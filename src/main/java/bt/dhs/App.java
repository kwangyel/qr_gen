package bt.dhs;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        QRSequenceGenerator qr = new QRSequenceGenerator();

        String[] topText = new String[1];
        String[] bottomText = new String[2];

        topText[0] = "www.zhichar.bt | Building and Unit Information";
//        bottomText[0] = "Know more about this unit";
        bottomText[0] = "Know more about this building";
        bottomText[1] = "Visit www.zhichar.bt & scan for details";

        List<String> qrCodeList = new ArrayList<>();

        for (Iterator iter = qr.getIterator(); iter.hasNext(); ) {
//            String data = itt.next("B");
//            qrCodeList.add(iter.next("U"));
            qrCodeList.add(iter.next("B"));
        }

        qrCodeList.parallelStream().forEach(data ->
                {
                    try {
                        System.out.println(data);
                        QRGenerator.generateQrCode(data, 1000, 1000, topText, bottomText, "output_qr/pling/buildings_10000");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

//        for(Iterator itt = qr.getIterator(); itt.hasNext();){
//            String data = itt.next("U");
////            String data = itt.next("B");
//            System.out.println("Generating "+data);
////            generateQrCode(data,1000,1000,topText, bottomText,"output_qr/building");
//            generateQrCode(data,1000,1000,topText, bottomText,"output_qr/unit");
//        }
    }
}
