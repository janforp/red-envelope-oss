package com.envolope.oss.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成二维码(QRCode)图片的util
 *
 * @author li-ningning
 */
public class QrcodeUtil {
    public static final String FORMAT_JPG = "jpg";
    public static final String FORMAT_PNG = "png";
    public static final Color LOGO_BORDER_DEFAULT_COLOR = new Color(160, 160, 160);

    /**
     * @param content 内容
     * @param format  图片文件格式：png/jpg
     * @param stream  存储的目标stream
     * @param pxSize  图片大小（整张图片的大小）
     */
    public static void encoderQRCode(String content, String format, OutputStream stream, int pxSize) {
        encoderQRCode(content, format, stream, pxSize, 2);
    }

    /**
     * @param content 内容
     * @param format  图片文件格式：png/jpg
     * @param stream  存储的目标stream
     * @param pxSize  图片大小（整张图片的大小）
     * @param margin  二维码里边界的宽度（四个方向）一般取2就好了（取2就和微信二维码的边界宽度一样了）
     */
    public static void encoderQRCode(String content, String format, OutputStream stream, int pxSize, int margin) {
        try {
            int width = pxSize;
            int height = pxSize;
            if ((!FORMAT_JPG.equals(format)) && (!FORMAT_PNG.equals(format))) {
                format = FORMAT_JPG;
            }
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.MARGIN, margin);
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            MatrixToImageConfig imgConfig = new MatrixToImageConfig();
            MatrixToImageWriter.writeToStream(bitMatrix, format, stream, imgConfig);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) throws Exception {
//        String qrcodeFile = "/home/wuqiang/下载/wulidashi-qrcode.png";
//        FileOutputStream outputStream = new FileOutputStream(qrcodeFile);
//        encoderQRCode("http://weixin.qq.com/r/e0RqcqnEhvT5re0R9xFp", "png", outputStream, 1024);
//        outputStream.close();
//    }

    /**
     * 给二维码图片添加logo图片
     *
     * @param qrcodeInputStream 二维码图片的输入流；使用完后，内部会关闭输入流
     * @param logoInputStream   logo图片的输入流；使用完后，内部会关闭输入流
     * @param logoFormatName    logo文件的后缀名（不含“.”）
     * @param targetLogoWidth   期望绘制在二维码上的logo宽度
     * @param targetLogoHeight  期望绘制在二维码上的logo高度
     * @param logoMarginColor   logo图片外部包围的颜色（就像一条粗线条）
     * @param logoMarginWidth   logo图片外部包围区域宽度（就像一条粗线条的borderColor）
     * @param logoBorderColor   靠在logo图片上的borderColor（可空）
     * @param logoBorderRadius  logo图片的圆角值（如果小于等于0，则绘制直角图）
     * @param outputStream      合成的结果图片的输出流；需要外部自己关闭输出流
     * @param targetFormatName  合成的结果图片的格式（png/jpg）
     * @throws IOException
     */
    public static void addLogoToQrcode(InputStream qrcodeInputStream,
                                       InputStream logoInputStream, String logoFormatName, int targetLogoWidth, int targetLogoHeight,
                                       Color logoMarginColor, int logoMarginWidth, Color logoBorderColor, int logoBorderRadius,
                                       OutputStream outputStream, String targetFormatName) throws IOException {
        try {
            BufferedImage qrcodeImage = ImageIO.read(qrcodeInputStream);
            BufferedImage logo = ImageIO.read(logoInputStream);
            int logoOldWidth = logo.getWidth(), logoOldHeight = logo.getHeight();
            if (logoOldWidth != targetLogoWidth || logoOldHeight != targetLogoHeight) {
                // 压缩
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                logoFormatName = logoFormatName != null ? logoFormatName.toLowerCase() : logoFormatName;
                if (FORMAT_JPG.equals(logoFormatName) || "jpeg".equals(logoFormatName)) {
                    ByteArrayOutputStream tmpOutputStream = new ByteArrayOutputStream();
                    ImageIO.write(logo, logoFormatName, tmpOutputStream);
                    disposeJPEGImageByJPEGImageEncoder(new ByteArrayInputStream(tmpOutputStream.toByteArray()),
                            byteArrayOutputStream, targetLogoWidth, targetLogoHeight);
                } else {
                    disposeImage(logo, FORMAT_PNG, byteArrayOutputStream, targetLogoWidth, targetLogoHeight);
                }
                logo = ImageIO.read(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            }
            Graphics2D g = qrcodeImage.createGraphics();
            int x = (qrcodeImage.getWidth() - targetLogoWidth) / 2;
            int y = (qrcodeImage.getHeight() - targetLogoHeight) / 2;
            if (logoMarginColor != null && logoMarginWidth > 0) {
                g.setColor(logoMarginColor);
                if (logoBorderRadius > 0) {
                    // 用白色填充一个圆角矩形区域，白色填充区域的圆角要比图片圆角多5像素
                    int borderRadius = logoBorderRadius + 5;
                    g.fillRoundRect(x - logoMarginWidth, y - logoMarginWidth, targetLogoWidth + logoMarginWidth * 2, targetLogoHeight + logoMarginWidth * 2, borderRadius, borderRadius);
                } else {
                    g.fillRect(x - logoMarginWidth, y - logoMarginWidth, targetLogoWidth + logoMarginWidth * 2, targetLogoHeight + logoMarginWidth * 2);
                }
            }
            // 把logo画上去
            g.drawImage(logo, x, y, targetLogoWidth, targetLogoHeight, null);
            if (logoMarginColor != null && logoMarginWidth > 0) {
                g.setStroke(new BasicStroke(logoMarginWidth));
                g.setColor(logoMarginColor);
                int i = logoMarginWidth / 2;
                // 用白色画一个线宽为logoMarginWidth一半的矩形框
                if (logoBorderRadius > 0) {
                    g.drawRoundRect(x - i, y - i, targetLogoWidth + logoMarginWidth, targetLogoHeight + logoMarginWidth, logoBorderRadius, logoBorderRadius);
                } else {
                    g.drawRect(x - i, y - i, targetLogoWidth + logoMarginWidth, targetLogoHeight + logoMarginWidth);
                }
            }

            if (logoBorderColor != null) {
                g.setStroke(new BasicStroke(1));
                g.setColor(logoBorderColor);
                // 在图片旁边画一条细线
                if (logoBorderRadius > 0) {
                    g.drawRoundRect(x, y, targetLogoWidth, targetLogoHeight, logoBorderRadius, logoBorderRadius);
                } else {
                    g.drawRect(x, y, targetLogoWidth, targetLogoHeight);
                }
            }
            g.dispose();
            ImageIO.write(qrcodeImage, targetFormatName, outputStream);
        } finally {
            if (qrcodeInputStream != null) {
                try {
                    qrcodeInputStream.close();
                } catch (IOException e) {
                }
            }
            if (logoInputStream != null) {
                try {
                    logoInputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 使用JPEGImageEncoder缩放JPG图片（会比disposeImage缩放的结果更清晰）
     *
     * @param srcInputStream 使用完后，内部会关闭输入流
     * @param stream         需要外部自己关闭输出流
     * @param targetWidth    目标宽度
     * @param targetHeight   目标高度
     * @throws IOException
     */
    private static void disposeJPEGImageByJPEGImageEncoder(InputStream srcInputStream, OutputStream stream, int targetWidth, int targetHeight) throws IOException {
        try {
            Image src = ImageIO.read(srcInputStream);
            if (src != null) {
                // 构造Image对象
                BufferedImage tag = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
                // 绘制缩小后的图
                tag.getGraphics().drawImage(src.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH), 0, 0, null);
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(stream);
                JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
                jep.setQuality((float) 1.0, true);
                encoder.encode(tag, jep);
            }
        } finally {
            if (srcInputStream != null) {
                try {
                    srcInputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 使用BufferedImage缩放图片
     *
     * @param src
     * @param targetFormat 目标文件格式
     * @param stream       需要外部自己关闭输出流
     * @param targetWidth
     * @param targetHeight
     */
    private static void disposeImage(BufferedImage src, String targetFormat, OutputStream stream, int targetWidth, int targetHeight) throws IOException {
        BufferedImage newImg = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = newImg.createGraphics();
        // 从原图上取颜色绘制新图
        g.drawImage(src, 0, 0, src.getWidth(), src.getHeight(), null);
        g.dispose();
        // 根据图片尺寸压缩比得到新图的尺寸
        newImg.getGraphics().drawImage(src.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH), 0, 0, null);
        ImageIO.write(newImg, targetFormat, stream);
    }
}




