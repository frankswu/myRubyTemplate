//
//  CT<%= key %>ViewModel.h
//  CtripWireless_Sender
//
//  Created by frankswu
//
//

#import <Foundation/Foundation.h>
#import "CTViewModel.h"
#import "CTFlightCheckInCertViewModel.h"

@interface CT<%= key %>ViewModel : CTViewModel

#pragma mark- -----------------------------------------------------------------------------------------------------------

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
