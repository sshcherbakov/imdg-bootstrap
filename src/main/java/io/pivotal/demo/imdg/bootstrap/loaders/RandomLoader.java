/**
 * 
 */
package io.pivotal.demo.imdg.bootstrap.loaders;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gemstone.gemfire.cache.CacheLoader;
import com.gemstone.gemfire.cache.CacheLoaderException;
import com.gemstone.gemfire.cache.LoaderHelper;

/**
 * @author sshcherbakov
 *
 */
@Component
public class RandomLoader implements CacheLoader<String, String> {
	private static Logger log = LoggerFactory.getLogger(RandomLoader.class);

	@Override
	public void close() {
		
	}

	@Override
	public String load(LoaderHelper<String, String> paramLoaderHelper) throws CacheLoaderException {
		String d = new Date().toString();
		log.info("Loading {} into {}",
				new Object[]{ d, paramLoaderHelper.getRegion().getName() });
		return d;
	}

}
