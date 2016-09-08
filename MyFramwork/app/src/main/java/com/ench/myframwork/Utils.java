package com.ench.myframwork;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

	public static String getTextFromStream(InputStream is){
		
		byte[] b = new byte[1024];
		int len = 0;
		//�����ֽ��������������ȡ���������ı����ʱ��ͬ�������д�����������
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			while((len = is.read(b)) != -1){
				bos.write(b, 0, len);
			}
			//���ֽ����������������ת�����ֽ�����
			String text = new String(bos.toByteArray());
			return text;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
