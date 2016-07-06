package com.mi360.aladdin.staticpage.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;

import com.mi360.aladdin.product.category.service.ProductCategoryService;
import com.mi360.aladdin.staticpage.service.IStaticService;
import com.mi360.aladdin.staticpage.util.LogUtil;

public class StaticServiceImpl implements IStaticService{

	public static final String serviceName = "静态化微服务";
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	public ProductCategoryService getProductCategoryService() {
		return productCategoryService;
	}

	public void setProductCategoryService(
			ProductCategoryService productCategoryService) {
		this.productCategoryService = productCategoryService;
	}

	private String targetPath;
	
	private String sourceDomain;
	
	private String qiniuDomain;
	
	private String oneBuyDomain;
	
	private String twoDimensionCodeImg;
	
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

	public String getOneBuyDomain() {
		return oneBuyDomain;
	}

	public void setOneBuyDomain(String oneBuyDomain) {
		this.oneBuyDomain = oneBuyDomain;
	}

	public void setQiniuDomain(String qiniuDomain) {
		this.qiniuDomain = qiniuDomain;
	}

	public String getTwoDimensionCodeImg() {
		return twoDimensionCodeImg;
	}

	public void setTwoDimensionCodeImg(String twoDimensionCodeImg) {
		this.twoDimensionCodeImg = twoDimensionCodeImg;
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
				String js = "<script type='text/javascript'>$.get('/shop_car/shopcar_count',function(ret){if(ret && ret.errcode==0){$('.num').text(ret.count);}else{$('.num').text(0);}});$('.indexbtn').click(function(){if($(this).find('.img1').is(':visible')){$(this).find('.img1').hide();$(this).find('.img2').show();}else{$(this).find('.img2').hide();$(this).find('.img1').show();}});$('#attention').click(function(){$('.showCode').fadeIn();});$('.closeCode').click(function(){$('.showCode').fadeOut();});$.ajax({url:'/subscribe',dataType:'json',success:function(ret){if(ret.subscribe==0){$('body').prepend('<style>.detail-p-top{margin:0;padding:0;top:0;left:0;width:100%;height:35px;line-height:35px;background:rgba(0,0,0,0.67);z-index:999;}.detail-p-top span{font-size:14px;color:#eee;margin-left:10px;}.detail-p-top a{display:block;font-size:12px;width:40px;height:25px;line-height:25px;border-radius:5px;background-color:#E81959;color:#fff;position:absolute;right:15px;top:5px;text-align: center;}.showCode{width:100%;height:100%;position:fixed;top:0;left:0;background:rgba(0,0,0,0.78);z-index:99999;display: none;text-align: center;color:#fff;}.showCode img{width:50%;margin-left:6%;margin-top:10px;}.showCode p{margin-top:50px;}.titleName{overflow:hidden;white-space: nowrap;text-overflow: ellipsis;font-size:14px;}.closeCode{width:20%;margin-left:40%;display:block;padding:5px;background-color:#E81959;color:#fff;font-size:14px;margin-top:20px;border-radius:5px;}</style><p class=\"detail-p-top\"><span class=\"titleName\">您尚未关注公众号,点击这里关注</span><a href=\"javascript:\" id=\"attention\">关注</a></p><div class=\"showCode\"><p>长按二维码关注</p><img src=\"/"+twoDimensionCodeImg+"\"><a href=\"javascript:\" class=\"closeCode\">我知道了</a></div>');}}});</script>";
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

	@Override
	public void staticPagePC(String requestId, String sourceUrl, String fileName) {
		
		LogUtil.logInput(serviceName, "staticPagePC", requestId, sourceUrl, fileName);
		
		HttpClient client = new DefaultHttpClient();
		
		System.out.println(sourceDomain+sourceUrl);
		HttpGet get = new HttpGet(sourceDomain+sourceUrl);
		
		String categoryList = "";
		
		String js = "<script type=\"text/javascript\">$.ajax({url:\"/login/islogin\",type:\"post\",dataType:\"json\",success:function(ret){if(ret.errcode==0){$(\".login_or_nickname\").find(\"a\").attr(\"href\",\"/user\").text(ret.nickname);$(\".register_or_logout\").find(\"a\").attr(\"href\",\"javascript:logout();\").text(\"退出\");$(\"body\").prepend(\"<input type='hidden' name='isLogin' value='1'/>\")}else{$(\"body\").prepend(\"<input type='hidden' name='isLogin' value='0'/>\")}}});function logout(){$.ajax({url:\"/user/logout\",type:\"post\",success:function(ret){location.href=\"/\"},error:function(){console.log(\"退出失败\")}})}</script>";
		
		String html = "<!DOCTYPE html><html><head><meta charset=\"UTF-8\" /><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" /><meta name=\"description\" content=\"\" /><meta name=\"author\" content=\"\" /><link rel=\"stylesheet\" href=\"/css/bootstrap.min.css\" /><link rel=\"stylesheet\" href=\"/css/reset.css\" /><link rel=\"stylesheet\" href=\"/css/theme2.css\" /><script src=\"/js/jquery.min.js\"></script><script src=\"/js/bootstrap.min.js\"></script><script src=\"/js/theme.js\"></script><title>首页</title></head><body><header class=\"header\"><div class=\"navbar navbar-static-top\"><div class=\"container\"><div class=\"row\"><ul class=\"nav navbar-nav navbar-left\"><li class=\"login_or_nickname\"><a href=\"/login\">登录</a></li><li class=\"register_or_logout\"><a href=\"/register\">免费注册</a></li><li><a href=\"/store\">我的店铺</a></li></ul><ul class=\"nav navbar-nav navbar-right\"><li><a href=\"/user/info\">个人中心</a></li><li><a href=\"/shop_car\">购物车</a></li><li><a href=\"/order/order-index\">我的订单</a></li><li><a href=\"/user/collects\">我的收藏</a></li><li><a href=\"javascript:\">联系客服</a></li></ul></div></div></div><div class=\"searchbar\"><div class=\"search\"><div class=\"container\"><div class=\"row\"><div class=\"pull-left\"><a href=\"index.html\" class=\"logo\"><img src=\"/images/logo/logo.png\"></a></div><div class=\"pull-right\"><div class=\"search-box\"><form class=\"search-form\"><div class=\"input-group\"><input type=\"text\" class=\"form-control\" placeholder=\"\" /><span class=\"input-group-addon\"><i class=\"icon icon-search\"></i></span></div></form></div><a href=\"javascript:;\"><img src=\"/images/code.png\" /></a></div></div><div class=\"row\"><div class=\"col-md-2 has-subcategory\"><a href=\"javascript:;\" class=\"category\">分类导航</a></div><div class=\"col-md-10\"><ul class=\"menu-list\"><li><a href=\"/\">首页</a></li><li><a href=\"{oneBuyDomain}\">一元购</a></li></ul></div></div></div></div></div></header><section class=\"banner-bar\"><div class=\"banner\"><div class=\"container\"><div class=\"row\"><div class=\"col-md-2\"><ul class=\"category-list jq-category\">                            {categoryLists}</ul></div><div class=\"col-md-8\"><div id=\"banner-carousel\" class=\"carousel slide\" data-ride=\"carousel\"><ol class=\"carousel-indicators\"><li data-target=\"#banner-carousel\" data-slide-to=\"0\" class=\"active\"></li><li data-target=\"#banner-carousel\" data-slide-to=\"1\"></li><li data-target=\"#banner-carousel\" data-slide-to=\"2\"></li></ol><div class=\"carousel-inner\" role=\"listbox\"><div class=\"item active\"><img src=\"/images/banner/banner-01.png\"/></div><div class=\"item\"><img src=\"/images/banner/banner-01.png\" /></div><div class=\"item\"><img src=\"/images/banner/banner-01.png\" /></div></div><a class=\"left carousel-control\" href=\"#banner-carousel\" role=\"button\" data-slide=\"prev\"><span class=\"glyphicon glyphicon-chevron-left\" aria-hidden=\"true\"></span></a><a class=\"right carousel-control\" href=\"#banner-carousel\" role=\"button\" data-slide=\"next\"><span class=\"glyphicon glyphicon-chevron-right\" aria-hidden=\"true\"></span></a></div></div><div class=\"col-md-2\"><ul class=\"side-area\"><li><a href=\"javascript:;\" class=\"pull-left\"><img src=\"/images/side/side-01.png\" /></a><div class=\"name pull-right\"><p>免费开店</p><p>该分类下的主商城会</p></div></li><li><div class=\"name pull-left\"><p>一元购</p><p>该分类下的主商城会</p></div><a href=\"javascript:;\" class=\"pull-right\"><img src=\"/images/side/side-03.png\" /></a></li><li><a href=\"javascript:;\" class=\"pull-left\"><img src=\"/images/side/side-02.png\" /></a><div class=\"name pull-right\"><p>团购</p><p>该分类下的主商城会</p></div></li><li><div class=\"name pull-left\"><p>砍价</p><p>该分类下的主商城会</p></div><a href=\"javascript:;\" class=\"pull-right\"><img src=\"/images/side/side-03.png\" /></a></li></ul></div></div></div></div></section>	{otherSection}</body></html>";
		
		html = html.replace("</body>", js+"</body>");
		html = html.replace("{oneBuyDomain}", oneBuyDomain);
		
		productCategoryService.flush(requestId);
		
		Map<String,Object> categoryMap = productCategoryService.findList(requestId);
		System.out.println("categoryMap:"+categoryMap);
		List<Map<String,Object>> categoryTree = (List<Map<String, Object>>) categoryMap.get("result");
		
		try{
			
			
			for(int i=0;i<categoryTree.size();i++){
				
				Map<String,Object> parentMap = categoryTree.get(i);
				String parentName = (String)((Map<String,Object>)parentMap.get("parent")).get("className");
				categoryList += "<li><a href='javascript:;'>"+parentName+"</a><div class='category-menu'><dl class='fore'><dd>";
				
				List<Map<String,Object>> leafList = (List<Map<String, Object>>) parentMap.get("leafs");
				if(leafList!=null){
					for(int j=0;j<leafList.size();j++){
						Map<String,Object> leafMap = leafList.get(j);
						categoryList+="<a href='"+leafMap.get("classUrl")+"' target='_blank'>"+leafMap.get("className")+"</a>";
					}
				}
				
				categoryList+="</dd></dl></div></li>";
				
			}
			
			html = html.replace("{categoryLists}",categoryList);
			
			HttpResponse resp = client.execute(get);
			System.out.println("content:"+IOUtils.toString(resp.getEntity().getContent()));
			
			
			html = html.replace("</body>", IOUtils.toString(resp.getEntity().getContent())+"</body>");
			
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
		
		
	}

}
