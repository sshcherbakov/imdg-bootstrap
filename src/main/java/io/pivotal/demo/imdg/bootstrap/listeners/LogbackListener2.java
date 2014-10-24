/**
 * 
 */
package io.pivotal.demo.imdg.bootstrap.listeners;

import io.pivotal.demo.imdg.bootstrap.service.TestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.LazyWiringDeclarableSupport;

import com.gemstone.gemfire.cache.CacheListener;
import com.gemstone.gemfire.cache.EntryEvent;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.RegionEvent;
import com.gemstone.gemfire.distributed.DistributedMember;

/**
 * Example of a {@link CacheListener} that can get autowired by 
 * {@link org.springframework.data.gemfire.support.SpringContextBootstrappingInitializer}
 * in case if the listener is attached to the region defined in the cache.xml file.
 * 
 * Please see example of the cache.xml file in the project's 'config' folder.
 * 
 * @author sshcherbakov
 */
public class LogbackListener2 extends LazyWiringDeclarableSupport implements CacheListener<String,Object> {
	private static Logger log = LoggerFactory.getLogger(LogbackListener2.class);
	
	@Autowired
	private TestService service;

	@Override
	public void afterCreate(EntryEvent<String, Object> paramEntryEvent) {
		logEvent("Created", paramEntryEvent);
	}
	
	@Override
	public void afterUpdate(EntryEvent<String, Object> paramEntryEvent) {
		logEvent("Updated", paramEntryEvent);
	}

	private void logEvent(String event, EntryEvent<String, Object> paramEntryEvent) {
		String key = paramEntryEvent.getKey();
		Object oldValue = paramEntryEvent.getOldValue();
		Object newValue = paramEntryEvent.getNewValue();
		
		Region<?,?> region = paramEntryEvent.getRegion();
		DistributedMember dm = paramEntryEvent.getDistributedMember();
		
		log.info("{} region={} key={} oldValue={} newValue={} on host={}; rand={}",
				new Object[]{ event, region.getName(), key, oldValue, newValue, dm.getHost(), service.getNextValue() });
	}

	@Override
	public void close() {
		log.info("Closing...");
	}

	@Override
	public void afterDestroy(EntryEvent<String, Object> paramEntryEvent) {
		log.info("afterDestroy...");		
	}

	@Override
	public void afterInvalidate(EntryEvent<String, Object> paramEntryEvent) {
		log.info("afterInvalidate...");		
	}

	@Override
	public void afterRegionClear(RegionEvent<String, Object> paramRegionEvent) {
		log.info("afterRegionClear...");		
	}

	@Override
	public void afterRegionCreate(RegionEvent<String, Object> paramRegionEvent) {
		log.info("afterRegionCreate...");		
	}

	@Override
	public void afterRegionDestroy(RegionEvent<String, Object> paramRegionEvent) {
		log.info("afterRegionDestroy...");		
	}

	@Override
	public void afterRegionInvalidate(
			RegionEvent<String, Object> paramRegionEvent) {
		log.info("afterRegionInvalidate...");		
	}

	@Override
	public void afterRegionLive(RegionEvent<String, Object> paramRegionEvent) {
		log.info("afterRegionLive...");		
	}
	
}
