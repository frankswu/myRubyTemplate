//
//  "<%= key %>.h"
//  
//
//  Created by gen code
//



#import <Foundation/Foundation.h>

@interface <%= key %>
	<%
		value.each do |k,v|
		
		propName = k
		propType = v.split(',')[0]
		propDesc = v.split(',')[1]
		if 'int'== propType
			propRef = 'assign'
		elsif 'String'== propType
			propType = 'NSString*'
			propRef = 'copy'
		elsif 'double'== propType
			propRef = 'assign'
		elsif 'long'== propType
			propType = 'long long'
			propRef = 'assign'
		elsif 'Date'== propType
			propType = 'NSDate*'
			propRef = 'strong'
		else
			propType = propType+'*'
			propRef = 'strong'
		end
	%>
@property(nonatomic, <%= propRef %>) <%= propType %> <%= propName %>;//<%= propDesc %> <% end %>

@end