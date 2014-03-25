package ctrip.viewcache.vacation;

import ctrip.business.viewmodel.CityModel;
import ctrip.viewcache.PageTagConstant;
import ctrip.viewdata.PageCacheBean;

/**
 *	version 5.4
 *
 */
public class <%= key %>CacheBean extends PageCacheBean  {

	public <%= key %>CacheBean() {
	}
	<%
		value.each do |k,v|
		
		propName = k
		propType = v.split(',')[0]
		propDesc = v.split(',')[1]
	%>
	/** <%= propDesc %> */
	public <%= propType %> <%= propName %> = ;	<% end%>

  public void saveViewData(String tag, PageCacheBean nextCacheBean) {
      super.saveViewData(tag, nextCacheBean);
      // TODO 
 }
    
}
