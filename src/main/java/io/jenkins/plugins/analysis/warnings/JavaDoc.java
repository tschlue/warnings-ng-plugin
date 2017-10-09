package io.jenkins.plugins.analysis.warnings;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.plugins.analysis.util.model.FileAnnotation;
import hudson.plugins.warnings.parser.AbstractWarningsParser;
import hudson.plugins.warnings.parser.FileWarningsParser;
import hudson.plugins.warnings.parser.Messages;
import hudson.plugins.warnings.parser.ParserRegistry;

/**
 * Provides customized messages for the JavaDoc parser.
 *
 * @author Ullrich Hafner
 */
public class JavaDoc extends Java {
    @DataBoundConstructor
    public JavaDoc() {
        super("javadoc");
    }

    @Override
    public Collection<FileAnnotation> parse(final File file, final String moduleName) throws InvocationTargetException {
        List<AbstractWarningsParser> parsers = ParserRegistry.getParsers("JavaDoc");

        return new FileWarningsParser(parsers, getDefaultEncoding()).parse(file, moduleName);
    }

    @Override
    protected String getName() {
        return "Java Compiler";
    }

    @Override
    public String getLinkName() {
        return Messages.Warnings_JavaDoc_LinkName();
    }

    @Override
    public String getTrendName() {
        return Messages.Warnings_JavaDoc_TrendName();
    }

    @Extension
    public static final StaticAnalysisToolDescriptor D = new StaticAnalysisToolDescriptor(JavaDoc.class);
}