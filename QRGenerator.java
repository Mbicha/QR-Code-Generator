package qrcode.generator;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class QRGenerator {

    public static final int DIMENSION = 200;

    public static void main(String[] args){
        /*
            mItem is the name of the code
            mPrice is what QR Code contains

            For this console app to work, you need to download two Jar files (zxing-1.7-javase.jar and zxing-core-2.0.jar
            Dimensions of the QR Code images is set at 200.
         */
        String mItem;
        double mPrice;
        String qrFilePath = "E:\\QRCodes";
        String charset = "UTF-8";

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter Item Name: ");
        mItem = input.nextLine();

        String fullFilePath = qrFilePath + "\\" + mItem + ".png";


        System.out.println("Please enter Item Price: ");
        mPrice = input.nextDouble();

        try{

            Map<EncodeHintType, ErrorCorrectionLevel> hintMap =
                    new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(String.valueOf(mPrice).getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, DIMENSION, DIMENSION, hintMap);

            MatrixToImageWriter.writeToFile(matrix, fullFilePath.substring(fullFilePath
                    .lastIndexOf(".") + 1), new File(fullFilePath));

            System.out.println("QR Code for " + mItem + " is Successfully Created");
            System.out.println();

        }catch (Exception e){
            System.err.println(e);
        }

    }
}
