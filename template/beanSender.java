


public class <%= key %>Sender extends Sender{

	private static <%= key %>Sender instance;

	protected <%= key %>Sender() {}

	public static <%= key %>Sender getInstance() {
		if (instance == null) {
			instance = new <%= key %>Sender();
		}
		return instance;
	}

	/**
	 * <br>
	 * serverCode | serverDesc<br>
	 * 
	 * @param <%= key[0].downcase + key[1..-1] %>CacheBean
	 * 		@{link <%= key %>CacheBean}	xxx cacheBean
	 * 
	 * 
	 * @return
	 */
	public SenderResultModel send<%= key %>(<%= key %>CacheBean <%= key[0].downcase + key[1..-1] %>CacheBean) {
		SenderResultModel senderResultModel = createResult4<%= key %>("send<%= key %>", <%= key[0].downcase + key[1..-1] %>CacheBean);
		if (!senderResultModel.isCanSender()) {
			return senderResultModel;
		}
		<%= key %>Request request = create<%= key %>Request(<%= key[0].downcase + key[1..-1] %>CacheBean);
		BusinessRequestEntity requestEntity = BusinessRequestEntity.getInstance();  
		requestEntity.setRequestBean(request);
		SenderCallBack senderCallBack = createCallBack4<%= key %>(<%= key[0].downcase + key[1..-1] %>CacheBean);
		
		this.senderService(senderResultModel, senderCallBack, requestEntity);
		return senderResultModel;
	}
	
	public <%= key %>Request create<%= key %>Request(
			final <%= key %>CacheBean <%= key %>CacheBean) {
			<%= key %>Request request = new <%= key %>Request();
		/**订单信息*/
		request.orderInfoModel = createOrerInforModel(<%= key %>CacheBean);
		return request;
	}


	private SenderCallBack createCallBack4<%= key %>(
			final <%= key %>CacheBean <%=  key[0].downcase + key[1..-1] %>CacheBean) {
		return new SenderCallBack() {
			
			@Override
			public boolean senderSuccess(SenderTask task, int index) {
				// 
				BusinessResponseEntity responseEntity = task.getResponseEntityArr()[index];
				if (responseEntity != null) {
					<%= key %>Response response = (<%= key %>Response)responseEntity.getResponseBean(); 
					/**执行状态:0=成功;1=失败*/
					if (0 == response.result) {
						// TODO

					}
					
				}
				return true;
			}
			
			@Override
			public boolean senderFail(SenderTask task, int index) {
				return false;
			}
		};
	}


	private SenderResultModel createResult4<%= key %>(
			String methodName, final <%= key %>CacheBean <%= key %>CacheBean) {
		return this.checkValueAndGetSenderResul(new CheckValueInterface() {
			
			@Override
			public boolean checkValue(String token, StringBuilder errorInfo) {
				// TODO 
				return returnCheckValue(errorInfo);
			}
		}, methodName);
	}

	protected boolean returnCheckValue(StringBuilder errorInfo) {
		if (errorInfo.length() > 0) {
			return false;
		} else {
			return true;
		}
	}



}