package basic;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class URLTest02 {

	public static void main(String[] args) {
		// 자원 반납해줘야함
		FileOutputStream fos = null;
		try {
			URL url = new URL(
					"http://cafefiles.naver.net/20120209_226/pododumok_13287752996753FNE4_jpg/%C0%E5%B5%BF%B0%C7_5_pododumok.jpg%20[%EC%B6%9C%EC%B2%98]%20url%EC%97%B0%EC%8A%B5%7C%EC%9E%91%EC%84%B1%EC%9E%90%20heaves1");

			// BufferdInputStream으로 읽어서(성능향상을 위해)
			// FileOutputStream으로 copy
			BufferedInputStream bis = new BufferedInputStream(url.openStream());
			fos = new FileOutputStream("src/image/jangImg.jpg");

			while (true) {
				int data_byte = bis.read();
				if (data_byte == -1) {
					break;
				}
				fos.write(data_byte);
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (Exception e) {
			}
		}
	}

}
