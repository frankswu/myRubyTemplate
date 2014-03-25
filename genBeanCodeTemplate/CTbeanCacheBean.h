//
//  CT<%= key %>CacheBean.h
//  Ctrip_Wireless_5.4
//
//  Created by frankswu
//

#import <Foundation/Foundation.h>
#import "CTPageCacheBean.h"

@interface CT<%= key %>CacheBean : CTPageCacheBean
	<%
		value.each do |k,v|
		
		propName = k
		propType = v.split(',')[0]
		propDesc = v.split(',')[1]
		if 'int'== propType
			propRef = 'assign'
		else
		if 'NSString'== propType
			propRef = 'copy'
		else
			propRef = 'strong'
		end
			
		
	%>
@property(nonatomic, <%= propRef %>) <%= propType %> *<%= propName %>;//<%= propDesc %> <% end%>
@end