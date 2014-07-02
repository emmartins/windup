package org.jboss.windup.rules.apps.javadecompiler;

import java.util.List;

import org.jboss.windup.config.RulePhase;
import org.jboss.windup.config.WindupRuleProvider;
import org.jboss.windup.config.graphsearch.GraphSearchConditionBuilder;
import org.jboss.windup.config.operation.Iteration;
import org.jboss.windup.graph.GraphContext;
import org.jboss.windup.graph.model.ArchiveModel;
import org.jboss.windup.rules.apps.java.scan.provider.DiscoverJavaFilesRuleProvider;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;

public class DecompileArchivesRuleProvider extends WindupRuleProvider
{
    @Override
    public RulePhase getPhase()
    {
        return RulePhase.DISCOVERY;
    }

    @Override
    public List<Class<? extends WindupRuleProvider>> getClassDependencies()
    {
        return generateDependencies(DiscoverJavaFilesRuleProvider.class);
    }

    @Override
    public Configuration getConfiguration(GraphContext context)
    {
        return ConfigurationBuilder.begin()
                    .addRule()
                    .when(
                                GraphSearchConditionBuilder.create("allUnzippedArchives").ofType(ArchiveModel.class)
                    ).perform(
                                Iteration.over("allUnzippedArchives").var(ArchiveModel.class, "archive")
                                            .perform(new ProcyonDecompilerOperation("archive"))
                                            .endIteration()
                    );

    }

}
