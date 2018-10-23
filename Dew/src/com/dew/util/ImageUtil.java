package com.dew.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dew.db.ImageInfo;

public class ImageUtil {
	final public static String allow_suffix_List="jpg,gif,png,jpeg";  
	
	private static boolean checkFileSuffix(String suffix){  
        return allow_suffix_List.contains(suffix.trim().toLowerCase());
	}  
	
	 public static boolean delete(String fileName) {
	        File file = new File(fileName);
	        if (!file.exists()) {
	            System.out.println("删除文件失败:" + fileName + "不存在！");
	            return false;
	        } else {
	            if (file.isFile())
	                return deleteFile(fileName);
	            else
	                return deleteDirectory(fileName);
	        }
	    }
	
	 /**
	     * 删除单个文件
	     *
	     * @param fileName
	     *            要删除的文件的文件名
	     * @return 单个文件删除成功返回true，否则返回false
	     */
	    public static boolean deleteFile(String fileName) {
	        File file = new File(fileName);
	        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
	        if (file.exists() && file.isFile()) {
	            if (file.delete()) {
	                System.out.println("删除单个文件" + fileName + "成功！");
	                return true;
	            } else {
	                System.out.println("删除单个文件" + fileName + "失败！");
	                return false;
	            }
	        } else {
	            System.out.println("删除单个文件失败：" + fileName + "不存在！");
	            return false;
	        }
	    }

	    /**
	     * 删除目录及目录下的文件
	     *
	     * @param dir
	     *            要删除的目录的文件路径
	     * @return 目录删除成功返回true，否则返回false
	     */
	    public static boolean deleteDirectory(String dir) {
	        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
	        if (!dir.endsWith(File.separator))
	            dir = dir + File.separator;
	        File dirFile = new File(dir);
	        // 如果dir对应的文件不存在，或者不是一个目录，则退出
	        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
	            System.out.println("删除目录失败：" + dir + "不存在！");
	            return false;
	        }
	        boolean flag = true;
	        // 删除文件夹中的所有文件包括子目录
	        File[] files = dirFile.listFiles();
	        for (int i = 0; i < files.length; i++) {
	            // 删除子文件
	            if (files[i].isFile()) {
	                flag = deleteFile(files[i].getAbsolutePath());
	                if (!flag)
	                    break;
	            }
	            // 删除子目录
	            else if (files[i].isDirectory()) {
	                flag = deleteDirectory(files[i].getAbsolutePath());
	                if (!flag)
	                    break;
	            }
	        }
	        if (!flag) {
	            System.out.println("删除目录失败！");
	            return false;
	        }
	        // 删除当前目录
	        if (dirFile.delete()) {
	            System.out.println("删除目录" + dir + "成功！");
	            return true;
	        } else {
	            return false;
	        }
	    }
	    
	public static void showByImageIO(String filePath, OutputStream ops) throws IOException{
		File file = new File(filePath);
		BufferedImage image = ImageIO.read(file);
		ImageIO.write(image, "png", ops);
	}
	
	public static void showByIOStream(String filePath, OutputStream ops) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
        int count = 0;
        byte[] buffer = new byte[1024 * 1024];
        while ((count = fis.read(buffer)) != -1) {
	        	ops.write(buffer, 0, count);
	        	ops.flush();
        }
	}
	
	public static List<ImageInfo> uploadImageByThumbnails(MultipartHttpServletRequest request, String uploadPath) throws Exception {
		return uploadImage(request, uploadPath, new UploadMethod() {
			@Override
			public void upload(MultipartFile multipartFile, String uploadPath, String newFileName) throws IOException {
				Thumbnails.of(multipartFile.getInputStream()).scale(1.0).toFile(uploadPath+newFileName);
            } 
		});
	}

	public static List<ImageInfo> uploadImageByImageIO(MultipartHttpServletRequest request, String uploadPath) throws Exception {
		return uploadImage(request, uploadPath, new UploadMethod() {
			@Override
			public void upload(MultipartFile multipartFile, String uploadPath, String newFileName) throws IOException {
                BufferedImage img = ImageIO.read(multipartFile.getInputStream());
                BufferedImage inputbig = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_BGR);
                Graphics2D g = (Graphics2D) inputbig.getGraphics();
                g.drawImage(img, 0, 0,img.getWidth(),img.getHeight(),null); //画图
                g.dispose();
                inputbig.flush();
                ImageIO.write(inputbig, "JPEG", new File(uploadPath+newFileName));
            }
		});
	}
	
	@SuppressWarnings("resource")
	public static List<ImageInfo> uploadImageByIOStream(MultipartHttpServletRequest request, String uploadPath) throws Exception {
		return uploadImage(request, uploadPath, new UploadMethod() {
			@Override
			public void upload(MultipartFile multipartFile, String uploadPath, String newFileName) throws IOException {
				InputStream is = null;  
	            OutputStream os = null;  
	            is = multipartFile.getInputStream();  
	            os = new FileOutputStream(uploadPath+newFileName);  
	            byte[] buffer = new byte[1024];  
	            int len = 0;  
	                
	            while((len = is.read(buffer))>0){  
	              os.write(buffer, 0, len);  
	              os.flush();
	            } 
			}
		});
	}
	
	private static List<ImageInfo> uploadImage(MultipartHttpServletRequest request, String uploadPath, UploadMethod method) throws Exception {
		List<ImageInfo> imageInfos = new ArrayList<ImageInfo>();
		List<MultipartFile> multipartFiles = request.getFiles("file"); 
	    	for(MultipartFile multipartFile : multipartFiles) { 
	        if (StringUtils.isNotEmpty(multipartFile.getOriginalFilename())) {  
	            String filename = multipartFile.getOriginalFilename();  
	            String suffix = filename.substring(filename.lastIndexOf(".")+1, filename.length());
	            if(checkFileSuffix(suffix)) {
	            		String dateStr = DateUtils.fmtyyyyMMddHHmmss(new Date());
	                String newFileName = dateStr + "_"+filename;
	                newFileName.replace(" ", "");
	                File directory = new File(uploadPath);
		            if ((!directory.exists()) || (!directory.isDirectory()))
		            		directory.mkdirs();
	                method.upload(multipartFile, uploadPath, newFileName);
	                ImageInfo imageInfo = assemblePictureInfo(null, filename, newFileName, null, null);
	                imageInfos.add(imageInfo);
	            } else 
	            		throw new Exception("图片格式必须为“jpg,gif,png,jpeg”");
	        } else 
	        		throw new Exception("图片名字不能为空");
	    	}
	    	return imageInfos;
	}
	
	public static ImageInfo assemblePictureInfo(Integer projectId, String nameOld, String nameNew, Integer cover, Integer index) {
		ImageInfo imageInfo = new ImageInfo();
		if(projectId != null)
			imageInfo.setProductid(projectId);
		if(StringUtils.isNotEmpty(nameOld))
			imageInfo.setOldName(nameOld);
		if(StringUtils.isNotEmpty(nameNew))
			imageInfo.setNewName(nameNew);
		if(cover != null)
			imageInfo.setCover(cover);
		if(index != null)
			imageInfo.setIndex(index);
		return imageInfo;
	}
	
	private interface UploadMethod {
		void upload(MultipartFile multipartFile, String uploadPath, String newFileName) throws IOException;
	}
}
