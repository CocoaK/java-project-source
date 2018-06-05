package com.biencloud.smarthome.web.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class QrcodeUtil {

	public static final String DOMAIN = "120.25.126.228";

	/**
	 * 二维码文本内容
	 * 
	 * @param roomNo
	 *            完整房号
	 * @param sipUid
	 *            sip用户名
	 * @param destUid
	 *            目标sip用户名
	 * @return 二维码文本内容
	 */
	public static String qrcodeContent(String roomNo, String sipUid,String pwd,
			String destUid) {
		if(roomNo==null || "".equals(roomNo)){
			return null;
		}
		if(sipUid==null || "".equals(sipUid)){
			return null;
		}
		if(destUid==null || "".equals(destUid)){
			return null;
		}
		if(pwd==null || "".equals(pwd)){
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		String key = roomNo.substring(roomNo.length() - 8, roomNo.length());
		String myKey = getActactlyCrc(key);
		sb.append("{\n");
		sb.append("\"ver\": \"1\",\n");
		sb.append("\"type\": 1,\n");
		sb.append("\"domain\": \"" + DOMAIN + "\",\n");
		sb.append("\"bbs_svr\": \"data.ipstar.org\",\n");
		sb.append("\"room\": \"" + roomNo + "\",\n");
		sb.append("\"sipuid\": \"" + sipUid + "\",\n");
		sb.append("\"pwd\": \"" + pwd + "\",\n");
		sb.append("\"destuid\": \"" + destUid + "\",\n");
		sb.append("\"crc\": " + myKey + "\n");
		//sb.append("\"wifispot\": \"i88-ipstar\",\n");
		//sb.append("\"pwdspot\": \"ipstar.org\",\n");
		//sb.append("\"phone_type\":0,\n");
		//sb.append("\"token\":\"\"\n");
		sb.append("}\n\n");
		return sb.toString();
	}

	public static String getActactlyCrc(String key) {
		String ret = "";
		final String FACTORY_ID = "12345678";
		byte[] result = new byte[9];

		DecimalFormat df_room = new DecimalFormat("00000000");
		key = df_room.format(Integer.parseInt(key));
		for (int i = 0; i < key.length(); i++) {

			result[i] = (byte) (key.getBytes()[i] ^ FACTORY_ID.getBytes()[i]);
			ret += result[i];
		}

		return MakeMD5(ret, "" + FACTORY_ID);
	}

	// MakeMD5为空函数直接返回
	public static String MakeMD5(String src, String password) {
		return src;
	}

	public static OutputStream getQrcodeOutputStream(String roomNo,
			String sipUid, String pwd, String destUid, OutputStream os) {
		String text = qrcodeContent(roomNo, sipUid, pwd, destUid);
		if(text==null){
			return null;
		}
		int width = 300;
		int height = 300;
		String format = "png";

		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text,
					BarcodeFormat.QR_CODE, width, height, hints);
			int w = bitMatrix.getWidth();
			int h = bitMatrix.getHeight();
			BufferedImage image = new BufferedImage(w, h,
					BufferedImage.TYPE_INT_RGB);

			for (int x = 0; x < w; x++) {
				for (int y = 0; y < h; y++) {
					image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
							: 0xFFCCDDEE);
				}
			}
			//操作image
			Graphics gs = image.getGraphics();
			//字体
			Font font = new Font("Arial",Font.PLAIN,20);
			int roomNoWidth = gs.getFontMetrics(font).stringWidth(roomNo);
			int sipUidWidth = gs.getFontMetrics(font).stringWidth(sipUid);
			gs.setColor(Color.BLACK);
			gs.setFont(font);
			gs.drawString(roomNo, (w-roomNoWidth)/2, 20);
			gs.drawString(sipUid, (w-sipUidWidth)/2, h-10);
			gs.dispose();
			ImageIO.write(image, format, os);
			os.flush();
			os.close();

		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return os;
	}

	public static void getQrcodeFile(String roomNo,
			String sipUid, String pwd, String destUid) {
		String text = qrcodeContent(roomNo, sipUid, pwd, destUid);
		if(text==null){
			return ;
		}
		int width = 180;
		int height = 180;
		String format = "png";

		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(text,
					BarcodeFormat.QR_CODE, width, height, hints);
			int w = bitMatrix.getWidth();
			int h = bitMatrix.getHeight();
			BufferedImage image = new BufferedImage(w, h,
					BufferedImage.TYPE_INT_RGB);

			for (int x = 0; x < w; x++) {
				for (int y = 0; y < h; y++) {
					image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000
							: 0xFFCCDDEE);
				}
			}
			//操作image
			Graphics gs = image.getGraphics();
			//字体
			Font font = new Font("Arial",Font.PLAIN,12);
			int roomNoWidth = gs.getFontMetrics(font).stringWidth(roomNo);
			int sipUidWidth = gs.getFontMetrics(font).stringWidth(sipUid);
			gs.setColor(Color.BLACK);
			gs.setFont(font);
			gs.drawString(roomNo, (w-roomNoWidth)/2, 12);
			gs.drawString(sipUid, (w-sipUidWidth)/2, h-5);
			gs.dispose();
			OutputStream os = new FileOutputStream(new File("F:/qrcode/"+roomNo+"_"+sipUid)+"."+format);
			ImageIO.write(image, format, os);
			os.flush();
			os.close();

		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
