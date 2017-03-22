package com.bootcamp.utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.qmx.framework.to.TransferObject;


public class JsonConvertor {
	private static JsonConvertor jsonConvertor ;
	public static JsonConvertor getInstance(){
		if(jsonConvertor==null){
			createInstance();
		}
		return jsonConvertor;
	}
	
	private JsonConvertor(){
		
	}
	
	private static synchronized JsonConvertor createInstance(){
		if(jsonConvertor==null){
			jsonConvertor = new JsonConvertor();
		}
		return jsonConvertor;
	}
	

	
	public String conver2JsonStr(Object object){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); 
		String jsonStr = gson.toJson(object);
		return jsonStr;
	}
	
	public Object convert2Object(String jsonStr , Class clazz){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); 
		return gson.fromJson(jsonStr, clazz);
	}
	

//	public static void main(String[] args) {
//		TransferObject transObject = new TransferObject();
//		
//		Product p = new Product ();
//		p.setPriceId(100);
//		
		
		
//		
//		UserProfile up =  new UserProfile();
//    	up.setUserId(1002);
//    	up.setUserBindingPhone("15950014502");
//    	//up.setUserName("ken");
//    	//up.setUserPassword("123456");
//		
//		transObject.setCurrentUser(up);
		
//		List<Tag> searchTags = new ArrayList<Tag>();
//		
//		Tag tag = new Tag (); 
//		
//		tag.setTagId(11);
//		
//		Tag tag2 = new Tag (); 
//		
//		tag2.setTagId(13);
//		
//		//searchTags.add(tag);
//		//searchTags.add(tag2);
//		
//		transObject.setSearchTags(searchTags);
//		transObject.setPageNumber(1);
		
		
//		AASubjectLogin subjectLogin = new AASubjectLogin();
//		subjectLogin.setLoginName("15850088682");
//		subjectLogin.setLoginPassword("888888");
//		transObject.setAaSubjectLogin(subjectLogin);
		
		
//		UserProfile user = new UserProfile();
//		user.setUserBindingPhone("15950014502");
//		user.setUserName("xiaoming");
//		user.setUserPassword("1234567");
//		transObject.setCurrentUser(user);
//		transObject.setActionType("UR");
//		
//		
//		Sms smsObject = new Sms();
//		smsObject.setSmsId(652);
//		smsObject.setSmsVerificationCode("125662");
//		
//		transObject.setSmsObject(smsObject);
//		
//		
		
//		System.out.println(PasswordService.getInstance().encrypt("123456"));
		
//		AASubjectLogin subjectLogin = new AASubjectLogin();
//		subjectLogin.setLoginName("15850088682");
//		subjectLogin.setLoginPassword("888888");
		//List<CommentModel> comments=new ArrayList<CommentModel>();
		//CommentModel commentModel = new CommentModel();
		//commentModel.setCommentContent("sdsdsd");
		//commentModel.setReferrenceObjectId(46);
		//comments.add(commentModel);
		//Event event = new Event();
		//event.setEventId(1);
		//transObject.setComments(comments);
//		Event event = new Event();
//		event.setEventId(41);;
//		EventModel eventModel =new EventModel(event);
//		List<EventModel> eventModels = new ArrayList<EventModel>();
//		eventModels.add(eventModel);
//		transObject.setEventModels(eventModels);
//		transObject.setBeeUnitId(48);
//		transObject.setPageNumber(0);
//		System.out.println(JsonConvertor.getInstance().conver2JsonStr(transObject));
//
//	}

	public static JsonConvertor getJsonConvertor() {
		return jsonConvertor;
	}


	

}
