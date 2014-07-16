package org.jboss.windup.rules.apps.java.model;

import org.jboss.windup.graph.model.WindupVertexFrame;
import org.jboss.windup.graph.model.resource.FileModel;
import org.jboss.windup.rules.apps.java.scan.ast.ClassCandidateType;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.modules.typedgraph.TypeValue;

@TypeValue("ClassificationModel")
public interface ClassificationModel extends WindupVertexFrame
{
    public static final String PROPERTY_QUALIFIED_NAME = "qualifiedName";
    public static final String PROPERTY_RULE_ID = "ruleID";
    public static final String PROPERTY_CLASS_CANDIDATE_TYPE = "classCandidateType";
    public static final String PROPERTY_EFFORT = "effort";
    public static final String PROPERTY_LINK_DECORATOR = "linkDecorator";

    /**
     * Adds a class candidate type (e.g. IMPORT, TYPE etc.)
     * 
     * @param type
     */
    @Adjacency(label = PROPERTY_CLASS_CANDIDATE_TYPE, direction = Direction.OUT)
    public void addClassCandidateType(ClassCandidateTypeModel type);

    @Adjacency(label = PROPERTY_CLASS_CANDIDATE_TYPE, direction = Direction.OUT)
    public Iterable<ClassCandidateTypeModel> getClassCandidateType();
    
    
    /**
     * Sets the link decorators
     * 
     * @param linkDecorator
     */
    @Adjacency(label = PROPERTY_LINK_DECORATOR, direction = Direction.OUT)
    public void addLinkDecorator(LinkDecoratorModel linkDecorator);

    @Adjacency(label = PROPERTY_LINK_DECORATOR, direction = Direction.OUT)
    public Iterable<LinkDecoratorModel> getLinkDecorators();
    
    /**
     * Sets the effort needed to fix the issue
     * 
     * @param effort
     */
    @Property(PROPERTY_EFFORT)
    public void setEffort(int effort);

    @Property(PROPERTY_EFFORT)
    public int getEffort();
    
    /**
     * Sets the ID of the rule that triggered this particular blacklist entry
     * 
     * @param ruleID
     */
    @Property(PROPERTY_RULE_ID)
    public void setRuleID(String ruleID);

    @Property(PROPERTY_RULE_ID)
    public String getRuleID();

    /**
     * Sets the Qualified name of the entity being referenced (fully qualified classname in the case of a Java blacklist
     * entry)
     * 
     * @param qualifiedName
     */
    @Property(PROPERTY_QUALIFIED_NAME)
    public void setQualifiedName(String qualifiedName);

    @Property(PROPERTY_QUALIFIED_NAME)
    public String getQualifiedName();

    /**
     * Sets the JavaClassModel referenced by this entry. This could be null if none were found.
     * 
     * @param javaClassModel
     */
    @Adjacency(label = "referencedJavaClassModel", direction = Direction.OUT)
    public void setReferencedJavaClassModel(JavaClassModel javaClassModel);

    @Adjacency(label = "referencedJavaClassModel", direction = Direction.OUT)
    public JavaClassModel getReferencedJavaClassModel();

}