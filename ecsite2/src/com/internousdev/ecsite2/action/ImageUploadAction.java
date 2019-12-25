package com.internousdev.ecsite2.action;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.opensymphony.xwork2.ActionSupport;

public class ImageUploadAction extends ActionSupport {
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;

	public String execute() throws IOException {
		String result = SUCCESS;

		Path from = fileUpload.toPath();
		Path fileOut = Paths.get("C:\\Users\\internousdev\\Desktop\\workspace\\workspace-sakurai\\test\\ecsite2\\WebContent\\images\\" + fileUploadFileName);
		try {
			Files.move(from, fileOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		File fileOut = new File("C:\\Users\\internousdev\\Desktop\\workspace\\workspace-sakurai\\test\\ecsite2\\WebContent\\images\\" + fileUploadFileName);
//		try {
//			if(fileUpload.renameTo(fileOut)) {
//				System.out.println("アップロードに成功しました。");
//			} else {
//				System.out.println("アップロードに失敗しました。");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return result;
	}

	public File getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getFileUploadContentType() {
		return fileUploadContentType;
	}
	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}
	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
}
