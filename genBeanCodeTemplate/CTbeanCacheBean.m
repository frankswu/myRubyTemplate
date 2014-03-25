//
//  <%= key %>CacheBean.h
//  Ctrip_Wireless_5.4
//
//  Created by frankswu
//

#import "CT<%= key %>CacheBean.h"
#import "PageTagConstant.h"

@implementation CT<%= key %>CacheBean


-(void) initCache {

}


-(void)save:(NSString *)tag nextCacheBean:(CTCacheBean *)nextCacheBean{
    [super save:tag nextCacheBean:nextCacheBean];
}


-(void)clean{
	[self initCache];
}

@end
