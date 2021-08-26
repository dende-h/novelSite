package filter;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

import io.post.novel.config.RedisConfig;

public class Initializer extends AbstractHttpSessionApplicationInitializer{

	public Initializer() {
		super(RedisConfig.class); 
	}
	
}
