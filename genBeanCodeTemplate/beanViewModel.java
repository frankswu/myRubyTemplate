package ctrip.viewcache.hotel.viewmodel;


import ctrip.business.ViewModel;

/**
 *	version 5.4
 *
 */
public class <%= key %>ViewModel extends ViewModel {

	<%
		value.each do |k,v|
		
		propName = k
		propType = v.split(',')[0]
		propDesc = v.split(',')[1]
	%>
	/** <%= propDesc %> */
	public <%= propType %> <%= propName %> = ;	<% end%>

}
