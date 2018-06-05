package com.biencloud.smarthome.web.template.action;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.biencloud.smarthome.web.base.action.BaseUploadAction;
import com.biencloud.smarthome.web.common.util.CacheUtil;
import com.biencloud.smarthome.web.common.util.Constants;
import com.biencloud.smarthome.web.common.util.FileUploadUtil;
import com.biencloud.smarthome.web.common.util.UniqUtil;
import com.biencloud.smarthome.web.template.VO.ModuleVO;

@SuppressWarnings("serial")
public class ModuleAction extends BaseUploadAction<ModuleVO>{
		private File file;
		private String fileName;
		private String type;
		private String imageUrl;
		private String linkUrl;
		private ModuleVO module;
		private Integer index;
		private String message;
		
		protected final Logger logger = LoggerFactory.getLogger(getClass());
		/**
		 * 
		 * 方法的描述: 上传组件
		 * @throws Exception
		 */
		public String upload() throws Exception{
//			String extName = "jpg";
//			if(StringUtils.isBlank(fileName))
//				extName = FileUploadUtil.getExtName(FileUploadUtil.getFileName(fileName));
//			String newfileName = UniqUtil.getInstance().getUniqIDHashString()+"."+extName;
//			super.uploadFile(newfileName, file);	//上传文件
//			imageUrl = super.getWebDownloadAbsoluteUrl()+newfileName;
//			if(CacheUtil.getInstance().getList().size()> index && CacheUtil.getInstance().getList().get(index) != null){
//				CacheUtil.getInstance().getList().get(index).setImageUrl(imageUrl);
//			}else{
//				for(int i=CacheUtil.getInstance().getList().size();i<index+1;i++){
//					ModuleVO m = new ModuleVO();
//					m.setModuleId(UniqUtil.getInstance().getUniqIDHashString());
//					m.setTopModuleId(CacheUtil.getInstance().getTopModuleId(i));
//					m.setLeftModuleId(CacheUtil.getInstance().getLeftModuleId(i));
//					CacheUtil.getInstance().addListObj(m);	//将实例放在缓存中
//				}
//				CacheUtil.getInstance().getList().get(index).setImageUrl(imageUrl);
//			}
//			if(CacheUtil.getInstance().getList().size() >= Constants.TEMPLATE_COLUMN_NUMBER*Constants.TEMPLATE_ROW_NUMBER){
//				message = "模板页面组件已经满，如未能完全显示，请刷新一下页面。";
//			}
			return "upload";
		}

		public String list() throws Exception{
			return "list";
		}
		
		public String updateModule() throws Exception{
			if(CacheUtil.getInstance().getList().size()> index && CacheUtil.getInstance().getList().get(index) != null){
				CacheUtil.getInstance().getList().get(index).setLinkUrl(linkUrl);
				CacheUtil.getInstance().getList().get(index).setType(type);
				CacheUtil.getInstance().getList().get(index);
				message = "success";
			}else{
				message = "fail";
			}
			return SUCCESS;
		}
		
		public String getSource() throws Exception{
				if(CacheUtil.getInstance().getList().size()>index)
					module = CacheUtil.getInstance().getList().get(index);
				return SUCCESS;
		}
		
		public File getFile() {
			return file;
		}

		public void setFile(File file) {
			this.file = file;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getLinkUrl() {
			return linkUrl;
		}

		public void setLinkUrl(String linkUrl) {
			this.linkUrl = linkUrl;
		}

		public ModuleVO getModule() {
			return module;
		}

		public void setModule(ModuleVO module) {
			this.module = module;
		}

		public Integer getIndex() {
			return index;
		}

		public void setIndex(Integer index) {
			this.index = index;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
}
