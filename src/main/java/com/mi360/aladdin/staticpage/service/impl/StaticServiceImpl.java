package com.mi360.aladdin.staticpage.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.mi360.aladdin.staticpage.service.IStaticService;
import com.mi360.aladdin.staticpage.util.LogUtil;

public class StaticServiceImpl implements IStaticService{

	public static final String serviceName = "静态化微服务";
	
	private String targetPath;
	
	private String sourceDomain;
	
	private String qiniuDomain;
	
	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	public String getSourceDomain() {
		return sourceDomain;
	}

	public void setSourceDomain(String sourceDomain) {
		this.sourceDomain = sourceDomain;
	}

	public String getQiniuDomain() {
		return qiniuDomain;
	}

	public void setQiniuDomain(String qiniuDomain) {
		this.qiniuDomain = qiniuDomain;
	}

	@Override
	public void staticPageToPath(String requestId, String sourceUrl, String fileName) {
	
		LogUtil.logInput(serviceName, "staticPageToPath", requestId, sourceUrl);
		
		HttpClient client = new DefaultHttpClient();
		
		System.out.println(sourceDomain+sourceUrl);
		HttpGet get = new HttpGet(sourceDomain+sourceUrl);
		String html = "";
		try{
			HttpResponse resp = client.execute(get);
			html = IOUtils.toString(resp.getEntity().getContent());
			if(fileName!=null){
				String logo = "<div class='logo'><div class='logo-main'><div class='logo-home logo-style'><a href='javascript:' class='car indexbtn'><div class='car-1'><img class='img1' src='"+qiniuDomain+"/images/img/index/home_p.png'><img class='img2' src='"+qiniuDomain+"/images/img/index/home_p.png'></div></a><div class='car-2'><a href='javascript:' class='car indexbtn'><span style='font-size:12px;'>首页</span></a></div></div><div class='logo-category logo-style'><a href='/productCategory/index' class='car indexbtn'><div class='car-1'><img class='img1' src='"+qiniuDomain+"/images/img/index/category_n.png'><img class='img2' src='"+qiniuDomain+"/images/img/index/category_p.png'></div></a><div class='car-2'><a href='/productCategory/index' class='car indexbtn'><span style='font-size:12px;'>分类</span></a></div></div><div class='logo-car logo-style'><a href='/shop_car' class='car indexbtn'><div class='car-1'><img class='img1' src='"+qiniuDomain+"/images/img/index/shopping_cart_n.png'><img class='img2' src='"+qiniuDomain+"/images/img/index/shopping_cart_p.png'><span class='num'>3</span></div></a><div class='car-2'><a href='/shop_car' class='car indexbtn'><span style='font-size:12px;'>购物车</span></a></div></div><div class='logo-wealth logo-style'><a href='/user/index' class='wealth indexbtn'><div class='wealth-1'><img class='img1' src='"+qiniuDomain+"/images/img/index/treasure_n.png'><img class='img2' src='"+qiniuDomain+"/images/img/index/treasure_p.png'></div><div class='wealth-2'><a href='/user/index' class='car indexbtn'><span style='font-size:12px;'>个人中心</span></a></div></div></div></div>";
				String js = "<script type='text/javascript'>$.get('/shop_car/shopcar_count',function(ret){if(ret && ret.errcode==0){$('.num').text(ret.count);}else{$('.num').text(0);}});$('.indexbtn').click(function(){if($(this).find('.img1').is(':visible')){$(this).find('.img1').hide();$(this).find('.img2').show();}else{$(this).find('.img2').hide();$(this).find('.img1').show();}});</script>";
				html = html.replace("</body>", logo+js+"</body>").replace("</head>", "<style>.container{padding-bottom:43px;}.logo-home span{color:#E71959;}.logo a{text-decoration:none;color:#666;}.logo img{vertical-align:top;}.logo .img2{display:none;}span.num{background-color: #E81959;color:#fff;font-family:Verdana;font-weight:normal;font-size:15px;padding:2px 2px;border-radius:15px;position:absolute;margin-top:-7px;margin-left:-10px;line-height:15px;width:15px;height:15px;}.logo{width:100%;height:45px;border:1px solid #cecece;box-shadow:0 0 5px #cecece;background-color:#fff;position:fixed;bottom:0;left:0;z-index:9999}.logo-main{width:100%;height:50px;}.logo-circle{display:block;width:60px;height:60px;border-radius:50%;position:absolute;background-color:#fff; border:1px solid #cecece;box-shadow:0 0 5px #cecece;z-index:99;top:-25px;left:40%;text-align:center;color:#b1b1b1}.logo-style{width:25%;height:30px;line-height:40px;text-align:center;float:left;}.logo-1{wdith:100%;height:50%;padding-top:5px;}.logo-2{wdith:100%;height:50%;line-height:35px;}.logo-1 img{width:35px;height:35px;}.car-1,.wealth-1{width:60%;height:50%;margin-left:20%;}.car-2,.wealth-2{width:60%;height:50%;margin-left:20%;}.car-1 img,.wealth-1 img{width:30px;height:30px;}</style></head>");
			}
			
			System.out.println(html);
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("targetPath:"+targetPath);
		File f = new File(targetPath);
		if(!f.exists()){
			f.mkdirs();
		}
		String file_name = f.getAbsolutePath()+"/"+(fileName==null?FilenameUtils.getName(sourceUrl):(fileName+".html"));
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(file_name));
			fos.write(html.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		LogUtil.logOutput(serviceName, "staticPageToPath", requestId);
		
	}

}
