package com.kitware.authorization.control;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class DocRenamePolicy implements FileRenamePolicy {
	

	@Override
	public File rename(File origin) {
		String oName = origin.getName();		
		int index = oName.lastIndexOf(".");
		if( index < 0) {
			return null;
		}else {
			String fileName = 
					oName.substring(0, index);//파일이름
			String ext = oName.substring(index);//확장자
			String pattern = "yyMMddhhmmss";
			SimpleDateFormat sdf = 
					new SimpleDateFormat(pattern);
			String now = sdf.format(new Date());
			String newFileName = 
					fileName+"_"+now+ext;
			//return new File(newFileName);
			System.out.println(origin.getParent());
			//쓰여질 파일경로설정
			File newFile = 
					new File(origin.getParent(), newFileName);
			
			//원본파일이름 변경
			origin.renameTo(newFile);
			
			//파일경로를 리턴하면 해당경로의 파일을 찾아 자동 쓰기가 됨
			return newFile; //FilePart.writeTo()가 자동호출됨
		}
	}
}