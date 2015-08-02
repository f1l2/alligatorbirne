package configuration.management.rest;

import common.data.NotificationDelegation;

/**
 * Interface definition
 * 
 * @author Manuel Filz
 *
 */
public interface CMgmtNotifyEvent { 
	
	public NotificationDelegation notify(NotificationDelegation notificationDelegation);
	
}
