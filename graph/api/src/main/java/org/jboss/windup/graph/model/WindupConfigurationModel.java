package org.jboss.windup.graph.model;

import java.util.ArrayList;

import org.jboss.windup.graph.model.resource.FileModel;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.frames.Adjacency;
import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.modules.javahandler.JavaHandler;
import com.tinkerpop.frames.modules.javahandler.JavaHandlerContext;
import com.tinkerpop.frames.modules.typedgraph.TypeValue;

@TypeValue("WindupConfiguration")
public interface WindupConfigurationModel extends WindupVertexFrame
{
    public static final String PROPERTY_SOURCE_MODE = "sourceMode";
    public static final String PROPERTY_FETCH_REMOTE_RESOURCES = "fetchRemoteResources";
    public static final String PROPERTY_EXCLUDE_JAVA_PACKAGES = "excludeJavaPackages";
    public static final String PROPERTY_SCAN_JAVA_PACKAGES = "scanJavaPackages";

    /**
     * The application to scan.
     */
    @JavaHandler
    void setInputPath(String inputPath);

    @Adjacency(label = "inputPath", direction = Direction.OUT)
    FileModel getInputPath();

    @Adjacency(label = "inputPath", direction = Direction.OUT)
    void setInputPath(FileModel inputPath);

    /**
     * Where to put the report.
     */
    @JavaHandler
    void setOutputPath(String outputPath);

    @Adjacency(label = "outputPath", direction = Direction.OUT)
    FileModel getOutputPath();

    @Adjacency(label = "outputPath", direction = Direction.OUT)
    void setOutputPath(FileModel outputPath);


    /**
     * This is for scanJavaPackageList, see Impl.
     */
    @Adjacency(label = PROPERTY_SCAN_JAVA_PACKAGES, direction = Direction.OUT)
    Iterable<PackageModel> getScanJavaPackages();

    @Adjacency(label = PROPERTY_SCAN_JAVA_PACKAGES, direction = Direction.OUT)
    void addScanJavaPackages(PackageModel scanJavaPackage);

    @Adjacency(label = PROPERTY_SCAN_JAVA_PACKAGES, direction = Direction.OUT)
    void setScanJavaPackages(Iterable<PackageModel> scanJavaPackage);


    /**
     * What Java packages to exclude during scanning of archives (blacklist).
     */
    @Adjacency(label = PROPERTY_EXCLUDE_JAVA_PACKAGES, direction = Direction.OUT)
    Iterable<PackageModel> getExcludeJavaPackages();

    @Adjacency(label = PROPERTY_EXCLUDE_JAVA_PACKAGES, direction = Direction.OUT)
    void addExcludeJavaPackage(PackageModel scanJavaPackage);

    @Adjacency(label = PROPERTY_EXCLUDE_JAVA_PACKAGES, direction = Direction.OUT)
    void setExcludeJavaPackages(Iterable<PackageModel> scanJavaPackage);

    /**
     * ???  I guess a whitelist?
     */
    @JavaHandler
    void setScanJavaPackageList(Iterable<String> pkgs);

    @JavaHandler
    void setExcludeJavaPackageList(Iterable<String> pkgs);

    
    /**
     * Not used.
     */
    @Property(PROPERTY_FETCH_REMOTE_RESOURCES)
    boolean isFetchRemoteResources();

    @Property(PROPERTY_FETCH_REMOTE_RESOURCES)
    void setFetchRemoteResources(boolean fetchRemoteResources);

    
    
    @Property(PROPERTY_SOURCE_MODE)
    boolean isSourceMode();

    @Property(PROPERTY_SOURCE_MODE)
    void setSourceMode(boolean sourceMode);

    
    // Implementation to be used by Frames.
    abstract class Impl implements WindupConfigurationModel, JavaHandlerContext<Vertex>
    {
        /**
         *  Converts the String into a FileModel.
         */
        public void setInputPath(String inputPath)
        {
            FileModel fileModel = this.g().addVertex(null, FileModel.class);
            fileModel.setFilePath(inputPath);
            setInputPath(fileModel);
        }

        /**
         *  Converts the String into a FileModel.
         */
        public void setOutputPath(String outputPath)
        {
            FileModel fileModel = this.g().addVertex(null, FileModel.class);
            fileModel.setFilePath(outputPath);
            setOutputPath(fileModel);
        }

        
        /**
         *  Converts the String's into a PackageModel's.
         */
        public void setScanJavaPackageList(Iterable<String> pkgs)
        {
            setScanJavaPackages(new ArrayList<PackageModel>());
            if (pkgs != null)
            {
                for (String pkg : pkgs)
                {
                    PackageModel m = g().addVertex(null,
                                PackageModel.class);
                    m.setPackageName(pkg);
                    addScanJavaPackages(m);
                }
            }
        }

        /**
         *  Converts the String's into a PackageModel's.
         */
        public void setExcludeJavaPackageList(Iterable<String> pkgs)
        {
            setExcludeJavaPackages(new ArrayList<PackageModel>());
            if (pkgs != null)
            {
                for (String pkg : pkgs)
                {
                    PackageModel m = g().addVertex(null,
                                PackageModel.class);
                    m.setPackageName(pkg);
                    addExcludeJavaPackage(m);
                }
            }
        }
    }
}
