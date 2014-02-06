package org.jboss.windup.engine.visitor.reporter;

import javax.inject.Inject;

import org.jboss.windup.engine.visitor.base.EmptyGraphVisitor;
import org.jboss.windup.graph.dao.JavaClassDaoBean;
import org.jboss.windup.graph.model.resource.JavaClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Display Java Classes not found but referenced by another class.
 *  
 * @author bradsdavis
 *
 */
public class ClassNotFoundReporter extends EmptyGraphVisitor {

	private static final Logger LOG = LoggerFactory.getLogger(ClassNotFoundReporter.class);
	
	@Inject
	private JavaClassDaoBean javaClassDao;
	
	@Override
	public void visit() {
		for(JavaClass clz : javaClassDao.getAllClassNotFound()) {
			LOG.info("Class not found: "+clz.getQualifiedName());
		}
	}
}